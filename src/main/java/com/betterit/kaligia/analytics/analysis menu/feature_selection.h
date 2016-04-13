#ifndef __FEATURE_SELECTION_H__
#define __FEATURE_SELECTION_H__
#include "settings.h"
#include "do_cv.h"
#include "genetic_selection.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <set>
#include <cstdlib>
#include <cmath>
#include <algorithm>
class feature_selection{
 public:
  static void run(settings &s);
  static void forward_selection(settings &s);
  static void pcc(settings &s);
  static void output_features(std::ofstream &ofs,std::set<int> &sample_features,std::vector<std::vector<double> > &feature_set, std::vector<double> &concentration);
};
#endif
