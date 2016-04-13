#include "run_model.h"
#include "settings.h"
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <cstdlib>

using namespace std;
void run_model::run(settings &s){
 string model_directory;
 cout << "specify model directory: ";
 cin >> model_directory;
 ostringstream oss;
 double min_concentration,max_concentration;
 vector<double> mins,maxs;
 ifstream ifs;
 oss << model_directory << "/scale_factors.dat";
 ifs.open(oss.str().c_str());
 
 if(!ifs.fail()){
  ifs >> min_concentration >> max_concentration;
  while(!ifs.eof()){
   double min,max;
   ifs >> min >> max;
   if(!ifs.fail()){
    //clog << "min " << min << " max " << max << endl;
    mins.push_back(min);
    maxs.push_back(max);
   }
  }
 }else{
  cerr << "unable to open " << oss.str() << endl;
  return;
 }
 oss.str("");
 ifs.close();
 string filename;
 cout << "specify file to run: ";
 cin >> filename;
 oss << s.directory << "/" << filename;
 string clean_file = oss.str();
 oss.str("");
 oss << s.directory << "/" << clean_file << ".poly";
 string poly_file = oss.str();
 oss.str("");
 oss << "java -cp \"FLremoval;FLremoval/lib/*\" FLremovalMain " << clean_file << " " << poly_file;
 system(oss.str().c_str());
 oss.str("");
 ifs.open(poly_file.c_str());
 vector<double> features;
 vector<double> wavelengths;
 if(!ifs.fail()){
  while(!ifs.eof()){
   double wavelength, intensity;
   ifs >> wavelength >> intensity;
   if(!ifs.fail()){
    wavelengths.push_back(wavelength);
    features.push_back(intensity);
   }
  }
 }
 ifs.close();
 double area = 0;
 for(int i = 0; i < (int)features.size(); ++i){
  area += features[i]/1389;
 }
 oss << s.directory << "/" << clean_file << ".snorm";
 ofstream ofs(oss.str().c_str());
 oss.str("");
 for(int i = 0; i < (int)features.size(); ++i){
  ofs << wavelengths[i] << " " << features[i]/area << endl;
 }
 ofs.close();
 for(int i = 0; i < (int)features.size(); ++i){
  //clog << "sample 0 feature " << i << " = " << features[i]/area << endl;
  features[i] = (features[i]/area-mins[i]) / (maxs[i]-mins[i]);
  //clog << "feature " << i << " min = " << mins[i] << " max = " << maxs[i] << " scaled = " << features[i] << endl; 
 }
 oss << s.directory << "/" << clean_file << ".wnorm";
 ofs.open(oss.str().c_str());
 oss.str("");
 for(int i = 0; i < (int)features.size(); ++i){
  ofs << wavelengths[i] << " " << features[i] << endl;
 }
 ofs.close();
 oss << s.directory << "/" << filename << ".features";
 ofs.open(oss.str().c_str());
 oss.str("");
 ofs << 0 << " ";
 for(int i = 0; i < (int)features.size(); ++i){
  ofs << i << ":" << features[i] << " ";
 }
 ofs << endl;
 ofs.close();
 
 oss << s.libsvm_path << "/svm-predict -q " << s.directory << "/" << filename << ".features" << " " << model_directory << "/model.svm" << " " << s.directory << "/" << filename << ".predict";
 system(oss.str().c_str());
 oss.str("");
 oss << s.directory << "/" << filename << ".predict";
 ifs.open(oss.str().c_str());
 oss.str("");
 double prediction;
 ifs >> prediction;
 cout << prediction*(max_concentration - min_concentration)+min_concentration << endl;
 ifs.close();
}
