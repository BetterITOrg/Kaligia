#include "create_model.h"
#include "feature_selection.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <cstdlib>
using namespace std;

void create_model::run(settings &s){
 ostringstream oss;
 ifstream ifs;
 ofstream ofs;
 string train_file, model_file, model_settings_file, selected_train_file;
 oss << s.directory << "/features.dat";
 train_file = oss.str();
 oss.str("");
 oss << s.directory << "/selected_features.svm";
 selected_train_file = oss.str();
 oss.str("");
 oss << s.directory << "/model.svm";
 model_file = oss.str();
 oss.str("");
 oss << s.directory << "/model_settings.dat";
 model_settings_file = oss.str();
 oss.str("");
 vector<vector<double> > train_set;
 vector<double> train_concentrations;
 string line;
 
 /*read in training features */
 ifs.open(train_file.c_str());
 while(!ifs.eof()){
  getline(ifs,line);
  //clog << "line # " << train_vector << " = " << line << endl;
  if(ifs.fail()) break;
  istringstream iss(line);
  double concentration;
  iss >> concentration;
  train_concentrations.push_back(concentration);
  //clog << "concentration " << train_vector << " = " << train_concentrations[train_vector] << endl;
  vector<double> train_vector;
  while(!iss.eof()){
   int feature_number;
   iss >> feature_number;
   if(!iss.fail()){
    char colon;
    double value;
    iss >> colon >> value;
    train_vector.push_back(value);
   }
  }
  train_set.push_back(train_vector);
 }
 ifs.close();

 if(s.selected_features.empty()){
  for(int i = 0; i < (int)train_set[0].size(); ++i){
   s.selected_features.insert(i);
  }
 }
 
 ofs.open(selected_train_file.c_str());
 feature_selection::output_features(ofs,s.selected_features,train_set, train_concentrations);
 ofs.close();
 oss << s.libsvm_path << "/svm-train -s 3 -t 2 -c "<<s.cost<<" -g "<<s.gamma<<" -q " << selected_train_file << " " << model_file;
 system(oss.str().c_str());
 oss.str("");
 

}
