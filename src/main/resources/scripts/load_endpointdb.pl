#!/usr/bin/env perl   <-- Not needed for Windows, but tradition rules
use strict;
use warnings;
use feature qw(say);
use autodie;           # Turns file operations into exception based programming

use File::Find;        # Your friend
use File::Copy;        # For the "move" command

# You could use Getopt::Long, but let's go with this for now:

# Usage = mungestrings.pl <from> <to> [<dir>]
#         Default dir is current
#
#my $from_string = shift;
#my $to_string   = shift;
my $directory   = shift;

#$from_string = quotemeta $from_string; # If you don't want to use regular expressions

$directory = "." if not defined $directory;

#
# Find the files you want to operate on
#
my @files;
find(
    sub {
        return unless -f;        # Files only
        return unless  /\.backup$/ ; # Name must end in ".txt"
        push @files, $File::Find::name;
    },
    $directory
);


for my $file ( @files ) {
	
    open my $input_fh, "<", $file;
    open my $output_fh, ">", "$file.tmp";
    my @fields = split /_/, substr $file, 2;
	my $kbsname = $fields[0];
	my $newfname= "$file.tmp";
    for my $line ( <$input_fh> ) {
       $line =~ s/SCHEMA kaligia/SCHEMA kaligia_${kbsname}/g;
       $line =~ s/search_path = kaligia/search_path = kaligia_${kbsname}/g;
       print ${output_fh} $line;
    }

    #
    # Contents been replaced move temp file over original
    #
    close $input_fh;
    close $output_fh;
    my $dbloadcmd = "\"C:\\Program Files\\PostgreSQL\\9.5\\bin\\psql.exe\" -h localhost -p 5432 -U postgres -d kaligia -f ${newfname}";
    system($dbloadcmd);
	
	my %increment = (kbs001 => 1000000, kbs002 => 2000000, kbs003 => 3000000, kbs004 => 4000000);

    my $dbmergecmd = "\"C:\\Program Files\\PostgreSQL\\9.5\\bin\\psql.exe\" -h localhost -p 5432 -U postgres -d kaligia -t -c \"select kaligia.mergeToMaster('kaligia_${kbsname}', $increment{$kbsname});\"";
 	print $dbmergecmd;
	my $result = "";
    $result = `$dbmergecmd`;
	print "Result: [", $result, "]\n";
	if($result =~ /Success/) {
	#Drop Schema
		print "Dropping Schema: kaligia_", $kbsname, "\n";
		my $dropschema = "\"C:\\Program Files\\PostgreSQL\\9.5\\bin\\psql.exe\" -h localhost -p 5432 -U postgres -d kaligia -t -c \"drop schema kaligia_${kbsname} cascade;\"";
		system($dropschema);
		print "Moving File to processed.\n";
		my $movefile = "move $file processed";
		system($movefile);
		$movefile = "move $newfname processed";
		system($movefile);
	} else {
		print "Merge Failed : ", $result;
	}
}