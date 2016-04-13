#ifndef __MAKE_CV_FILES_H__
#define __MAKE_CV_FILES_H__
#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include <cstdlib>
#include <cmath>
#include "settings.h"
class Features{
 public:
 std::vector<double> raw_features;
 void output_features(std::ostream &os, std::vector<double> &maxs, std::vector<double> &mins){
  if(raw_features.size() != mins.size() and raw_features.size() != maxs.size()) return;
  for(int i = 0; i < (int)raw_features.size(); ++i){
   if(maxs[i] == mins[i]){
    os << i << ":" << 0 << " ";
   }else{
    os << i << ":" << (raw_features[i]-mins[i])/(maxs[i]-mins[i]) << " ";
   }
  }
  os << std::endl;
 }
};
class Reading{
 public:
 Features features;
};
class Sample{
 public:
 double start_concentration;
 double end_concentration;
 double avg_concentration;
 std::vector<double> min_values;
 std::vector<double> max_values;
 std::vector<Reading> readings;
 void output_sample(std::ostream &os, double min_concentration, double max_concentration, std::vector<double> &maxs, std::vector<double> &mins){
  //for(int i = 0; i < (int)readings.size(); ++i){
  // os << (avg_concentration-min_concentration)/(max_concentration-min_concentration) << " ";
  // readings[i].features.output_features(os, maxs,mins);
  //}
  std::clog << "avg cons. = " << avg_concentration << " min = " << min_concentration << " max = " << max_concentration << " squished = " << (avg_concentration-min_concentration)/(max_concentration-min_concentration) << std::endl;
  os << (avg_concentration-min_concentration)/(max_concentration-min_concentration) << " ";
  //std::cerr << "feature count: " << readings[0].features.raw_features.size() << std::endl;

  for(int i = 0; i < (int)readings[0].features.raw_features.size(); ++i){
   double avg = 0;
   if(maxs[i] == mins[i]) {
    //std::cerr << "maxs and mins == for i " << i << std::endl;
    continue;
   }
   for(int j = 0; j < (int)readings.size(); ++j){
    avg += readings[j].features.raw_features[i]/readings.size();
   }
   os << i << ":" << (avg-mins[i])/(maxs[i]-mins[i]) << " ";
  }
  os << std::endl;
 }
};
class make_cv{
 public:
  static void run(settings &s);
};
#endif
