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
  settings(){
   libsvm_path = "../libsvm-3.21/windows";
   directory = "../data";
   number_folds = 7;
   gamma = 0.00012207512;
   cost = 512;
   file_offset = 1;
  }
};
#endif
