@echo off
setlocal enabledelayedexpansion

for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
     set dow=%%i
     set month=%%j
     set day=%%k
     set year=%%l
   )

for /f "tokens=1-3 delims=: " %%i in ("%time%") do (
     set myhr=%%i
     set mymin=%%j
     set mysec=%%k
   )
set datestr=%month%_%day%_%year%
echo datestr is %datestr%

set epn=kbs001
echo epn is %epn%
    
set BACKUP_FILE=%epn%_%datestr%_%myhr%_%mymin%.backup

if not exist "%BACKUP_FILE%" goto SkipRename

for %%f in ("%BACKUP_FILE%") do (
    set NewName=%%~nf.old%%~xf

    if exist "!NewName!" del /F "!NewName!"
 
    echo Renaming "%BACKUP_FILE%" to "!NewName!" ...
    ren "%BACKUP_FILE%" "!NewName!"
)
:SkipRename

   echo backup file name is %BACKUP_FILE%
   echo on
   "C:\Program Files\PostgreSQL\9.5\bin\pg_dump.exe" -h localhost -p 5432 -U postgres -O -F p -n kaligia -d kaligia -f %BACKUP_FILE% 
	
::"C:\Program Files\PostgreSQL\9.5\bin\psql.exe" -h localhost -p 5432 -U postgres -d kaligia -f %BACKUP_FILE%
::echo ftp -s:FileName once backup file is generated.


winscp.exe /log=ftpcmd.log /command ^
    "open ftp://kaligia:kaligia@71.251.87.162 " ^
    "put %BACKUP_FILE% " ^
    "exit"