#ifndef __SETTINGS_H__
#define __SETTINGS_H__
#include <string>
#include <set>
class settings{
 public:
  std::string libsvm_path;
  std::string directory;
  int number_folds;
  double gamma;
  double cost;
  int file_offset;
  std::set<int> selected_features;
  std::string database_dump_filename;
  bool read_individual_files;
  settings(){
   libsvm_path = "../libsvm-3.21/windows";
   directory = "../data/";
   number_folds = 10;
   gamma = 0.00012207512;
   cost = 512;
   file_offset = 1;
   read_individual_files = false;
   database_dump_filename = "simulated_spectra.dat";
  }
};
#endif
