#include "parameter_tuning.h"
#include "feature_selection.h"
using namespace std;

void parameter_tuning::run(settings &s){
 for(int i = 0; i < s.number_folds; ++i){
  ostringstream oss;
  ifstream ifs;
  ofstream ofs;
  string train_file, test_file, model_file, predict_file, tune_file, selected_train_file, selected_test_file;
  oss << s.directory << "/cv" << i << "/train.svm";
  train_file = oss.str();
  oss.str("");
  oss << s.directory << "/cv" << i << "/selected_train.svm";
  selected_train_file = oss.str();
  oss.str("");
  oss << s.directory << "/cv" << i << "/test.svm";
  test_file = oss.str();
  oss.str("");
  oss << s.directory << "/cv" << i << "/selected_test.svm";
  selected_test_file = oss.str();
  oss.str("");
  oss << s.directory << "/cv" << i << "/model.svm";
  model_file = oss.str();
  oss.str("");
  oss << s.directory << "/cv" << i << "/predict.svm";
  predict_file = oss.str();
  oss.str("");
  oss << s.directory << "/cv" << i << "/tune.svm";
  tune_file = oss.str();
  oss.str("");
  vector<vector<double> > train_set;
  vector<vector<double> > test_set;
  vector<double> train_concentrations;
  vector<double> test_concentration;
  double concentration;
  /*read in testing features*/
  ifs.open(test_file.c_str());
  string line;
  while(!ifs.eof()){
   getline(ifs,line);
   if(ifs.fail()) break;
   istringstream iss(line);
   double concentration;
   iss >> concentration;
   test_concentration.push_back(concentration);
   vector<double> test_vector;
   while(!iss.eof()){
    int feature_number;
    iss >> feature_number;
    if(!iss.fail()){
     char colon;
     double value;
     iss >> colon >> value;
     test_vector.push_back(value);
    }
   }
   test_set.push_back(test_vector);
  }
  ifs.close();

  
  /*read in training features */
  ifs.open(train_file.c_str());
  while(!ifs.eof()){
   getline(ifs,line);
   //clog << "line # " << train_vector << " = " << line << endl;
   if(ifs.fail()) break;
   istringstream iss(line);
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
  
  /*add tuning features to training set*/
  ifs.open(tune_file.c_str());
  while(!ifs.eof()){
   getline(ifs,line);
   //clog << "line # " << train_vector << " = " << line << endl;
   if(ifs.fail()) break;
   istringstream iss(line);
   iss >> concentration;
   train_concentrations.push_back(concentration);
   //clog << "concentration " << train_vector << " = " << train_concentrations[train_vector] << endl;
   vector<double> tune_vector;
   while(!iss.eof()){
    int feature_number;
    iss >> feature_number;
    if(!iss.fail()){
     char colon;
     double value;
     iss >> colon >> value;
     tune_vector.push_back(value);
    }
   }
   train_set.push_back(tune_vector);
  }
  ifs.close();

  if(s.selected_features.empty()){
   for(int i = 0; i < (int)test_set[0].size(); ++i){
    s.selected_features.insert(i);
   }
  }
  ofs.open(selected_test_file.c_str());
  feature_selection::output_features(ofs,s.selected_features,test_set, test_concentration);
  ofs.close();
  ofs.open(selected_train_file.c_str());
  feature_selection::output_features(ofs,s.selected_features,train_set, train_concentrations);
  ofs.close();
 }
 double best_rmse = 100;
 double best_cost = 100;
 double best_gamma = 100;
 for(double cost = .125; cost < 32769; cost*=2){
  for(double gamma = 64; gamma > 0.0001; gamma/=2){
   double mse = 0;
   double samples = 0;
   for(int i = 0; i < s.number_folds; ++i){
    ostringstream oss;
    string train_file, test_file, model_file, predict_file, actual_file, scale_factors_file;
    oss << s.directory << "/cv" << i << "/selected_train.svm";
    train_file = oss.str();
    oss.str("");
    oss << s.directory << "/cv" << i << "/selected_test.svm";
    test_file = oss.str();
    oss.str("");
    oss << s.directory << "/cv" << i << "/model.svm";
    model_file = oss.str();
    oss.str("");
    oss << s.directory << "/cv" << i << "/predict.svm";
    predict_file = oss.str();
    oss.str("");
    oss << s.directory << "/cv" << i << "/actual.dat";
    actual_file = oss.str();
    oss.str("");
    oss << s.directory << "/cv" << i << "/scale_factors.dat";
    scale_factors_file = oss.str();
    oss.str("");
    oss << s.libsvm_path << "/svm-train -s 3 -t 2 -c "<<cost<<" -g "<<gamma<<" -q " << train_file << " " << model_file;
    system(oss.str().c_str());
    oss.str("");
    oss << s.libsvm_path << "/svm-predict -q " << test_file << " " << model_file << " " << predict_file;
    system(oss.str().c_str());
    ifstream ifs;
    //ifstream ifs(predict_file.c_str());
    double min_scale_factor,max_scale_factor;
    
    ifs.open(scale_factors_file.c_str());
    ifs >> min_scale_factor >> max_scale_factor;
    ifs.close();
    ifs.open(predict_file.c_str());
    vector<double> prediction;
    while(!ifs.eof()){
     double p;
     ifs >> p;
     if(!ifs.fail()){
      p = p*(max_scale_factor-min_scale_factor)+min_scale_factor;
      prediction.push_back(p);
     }
    }
    ifs.close();
    vector<double> actual;
    
    ifs.open(actual_file.c_str());
    while(!ifs.eof()){
     double a;
     ifs >> a;
     if(!ifs.fail()){
      actual.push_back(a);
     }
    }
    for(int i = 0; i < (int)prediction.size(); ++i){
     mse += (actual[i]-prediction[i])*(actual[i]-prediction[i]);
     ++samples;
    }
   }
   double rmse = sqrt(mse/samples);
   if(rmse < best_rmse){
    best_rmse = rmse;
    best_cost = cost;
    best_gamma = gamma;
   }
   cout << cost << ", " << gamma << ": " << rmse << " (best: c " << best_cost << " g " << best_gamma << " " << best_rmse << ")" << endl;
  }
 }
 s.gamma = best_gamma;
 s.cost = best_cost;
}
