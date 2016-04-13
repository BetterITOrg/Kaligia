#include "make_cv_files.h"
using namespace std;


void make_cv::run(settings &s){
 
 string rd_base;
 int sample_start, sample_end, reading_start, reading_end;
 int sample_count, readings_per_sample;
 cout << "Base name (filename before set number): ";
 cin >> rd_base;
 cout << "Numeber of samples: ";
 cin >> sample_count;
 cout << "readings per sample: ";
 cin >> readings_per_sample;
 sample_start = s.file_offset;
 sample_end = sample_start + sample_count - 1;
 reading_start = s.file_offset;
 reading_end = reading_start+readings_per_sample - 1;
 vector<Sample> samples(sample_end-sample_start+1);
 /*clog << "removing flouresence" << endl;
 for(int i = sample_start; i <= sample_end; ++i){
  for(int j = reading_start; j <= reading_end; ++j){
   ostringstream oss;
   //oss << "sample_S" << i+1 << "-R" << j+1 << ".txt";
   oss << s.directory << "/" << rd_base << "S" << i << "-R" << j << ".txt";
   string clean_file = oss.str();
   oss.str("");
   oss << s.directory << "/" << rd_base << "S" << i << "-R" << j << ".poly.txt";
   string poly_file = oss.str();
   oss.str("");
   oss << "java -cp \"FLremoval;FLremoval/lib/*\" FLremovalMain " << clean_file << " " << poly_file;
   system(oss.str().c_str());
  }
 }*/
 clog << "reading raman spectra" << endl;
 for(int i = sample_start; i <= sample_end; ++i){
  samples[i-sample_start].readings.resize(reading_end-reading_start+1);
  for(int j = reading_start; j <= reading_end; ++j){
   ostringstream oss;
   //oss << "sample_S" << i+1 << "-R" << j+1 << ".txt";
   oss << s.directory << "/" << rd_base << "S" << i << "-R" << j << ".norm.txt";
   ifstream ifs(oss.str().c_str());
   if(!ifs.fail()){
    while(!ifs.eof()){
     double raman_shift;
     double intensity;
     ifs >> raman_shift >> intensity;
     if(!ifs.fail() /*and raman_shift > 800 and raman_shift < 1400*/){
      //clog << "intensity = " << intensity << " ";
      //intensity /= pow(10,(int)log10(fabs(intensity))+1);
      //clog << "=> " << intensity << endl;
      samples[i-sample_start].readings[j-reading_start].features.raw_features.push_back(intensity);
      
     }
    }
   }else{
    cerr << "unable to open " << oss.str() << endl;
   }
  }
 }
 //clog << "raw data: " << endl;
 //for(int j = 0; j <(int)samples[0].readings[0].features.raw_features.size(); ++j){
 // clog << "sample 0 feature " << j << " = " << samples[0].readings[0].features.raw_features[j] <<endl;
 //}
 vector<double> bgl_start;
 vector<double> bgl_end;
 ostringstream oss;
 oss << s.directory << "/bg_values.txt";
 ifstream ifs(oss.str().c_str());
 clog << "reading true values" << endl;
 if(!ifs.fail()){
  while(!ifs.eof()){
   double start;
   ifs >> start;
   if(!ifs.fail()){
    bgl_start.push_back(start);
    bgl_end.push_back(start);
   }
  }
 }else{
  cerr << "unable to open " << oss.str() << endl;
  return;
 }
 oss.str("");
 /*clog << "normalizing spectra" << endl;
 for(int i = 0; i < (int)samples.size(); ++i){
  for(int j = 0; j < (int)samples[i].readings.size(); ++j){
   double area = 0;
   for(int k = 0; k < (int)samples[i].readings[j].features.raw_features.size(); ++k){
    area += samples[i].readings[j].features.raw_features[k]/1389;
   }
   for(int k = 0; k < (int)samples[i].readings[j].features.raw_features.size(); ++k){
    samples[i].readings[j].features.raw_features[k]/=area;
   }
  }
 }
 clog << "normalized spectrum: " << endl;
 for(int j = 0; j <(int)samples[0].readings[0].features.raw_features.size(); ++j){
  clog << "sample 0 feature " << j << " = " << samples[0].readings[0].features.raw_features[j] <<endl;
 }*/
 //clog << "normalizing wavenumbers" << endl;
 for(int i = 0; i < (int)samples.size(); ++i){
  samples[i].start_concentration = bgl_start[i];
  samples[i].end_concentration = bgl_end[i];
  samples[i].avg_concentration = (samples[i].start_concentration + samples[i].end_concentration) / 2;
  clog << "sample " << i+1 << " start concentration: " << samples[i].start_concentration;
  clog << " end concentration: " << samples[i].end_concentration << " avg concentration: ";
  clog << samples[i].avg_concentration << endl;

  for(int j = 0; j < (int)samples[i].readings[0].features.raw_features.size(); ++j){
   samples[i].min_values.push_back(samples[i].readings[0].features.raw_features[j]);
   samples[i].max_values.push_back(samples[i].readings[0].features.raw_features[j]);
  }
  for(int j = 0; j < (int)samples[i].readings.size(); ++j){
   for(int k = 0; k < (int)samples[i].readings[j].features.raw_features.size(); ++k){
    samples[i].min_values[k] = min(samples[i].min_values[k], samples[i].readings[j].features.raw_features[k]);
    samples[i].max_values[k] = max(samples[i].max_values[k], samples[i].readings[j].features.raw_features[k]);
   }
  }
 }
 
 
 clog << "writing output files" << endl;
 for(int cv = 0; cv < s.number_folds; ++cv){
  vector<Sample> cv_training_samples;
  vector<Sample> cv_testing_samples;
  vector<Sample> cv_tuning_samples;
  clog << " CV " << cv << ": " << endl;
  for(int i = 0; i < (int)samples.size(); ++i){
   if(i < (int)(cv*samples.size()/s.number_folds) or i+1>(int)((cv+1)*samples.size()/s.number_folds)){
    if(i > (int)((s.number_folds-1)*samples.size()/s.number_folds) and cv == (int)(samples.size()-1)){
     cv_testing_samples.push_back(samples[i]);
     clog << "special testing on " << i << endl;
    }else{
     cv_training_samples.push_back(samples[i]);
     clog << "training on " << i << endl;
    }
   }else{
    cv_testing_samples.push_back(samples[i]);
    clog << "testing on " << i << endl;
   }
  }
  for(int i = 0; i < (int)samples.size()/s.number_folds; ++i){
   cv_tuning_samples.push_back(cv_training_samples.back());
   cv_training_samples.erase(cv_training_samples.end()--);
  }
  vector<double> maxs;
  vector<double> mins;
  
  double min_concentration = cv_training_samples[0].avg_concentration;
  double max_concentration = cv_training_samples[0].avg_concentration;
  
  for(int i = 0; i < (int) cv_training_samples[0].min_values.size(); ++i){
   maxs.push_back(cv_training_samples[0].max_values[i]);
   mins.push_back(cv_training_samples[0].min_values[i]);
  }
  for(int i = 0; i < (int) cv_training_samples.size(); ++i){
   min_concentration = min(min_concentration,cv_training_samples[i].avg_concentration);
   max_concentration = max(max_concentration,cv_training_samples[i].avg_concentration);
   //clog << "iteration " << i << " range: " << min_concentration << " - " << max_concentration << endl;
   for(int j = 0; j < (int) cv_training_samples[i].min_values.size(); ++j){
    maxs[j] = max(maxs[j],cv_training_samples[i].max_values[j]);
    mins[j] = min(mins[j],cv_training_samples[i].min_values[j]);
   }
  }
  clog << "cv" << cv << " concentration range: " << min_concentration << " - " << max_concentration << endl;
  
  oss << "mkdir " << s.directory << "/cv"<<cv;
  system(oss.str().c_str());
  oss.str("");
  oss << s.directory << "/cv"<<cv<<"/test.svm";
  ofstream ofs(oss.str().c_str());
  for(int i = 0; i < (int) cv_testing_samples.size(); ++i){
   cv_testing_samples[i].output_sample(ofs,min_concentration, max_concentration, maxs, mins);
  }
  ofs.close();
  oss.str("");
  oss << s.directory << "/cv"<<cv<<"/train.svm";
  ofs.open(oss.str().c_str());
  for(int i = 0; i < (int)cv_training_samples.size(); ++i){
   //if(i == cv or i == (int)((cv+1)%cv_training_samples.size())) continue;
   clog << "cv" << cv << " sample " << i << " ";
   cv_training_samples[i].output_sample(ofs,min_concentration,max_concentration,maxs,mins);
  }
  ofs.close();
  oss.str("");
  oss << s.directory << "/cv"<<cv<<"/tune.svm";
  ofs.open(oss.str().c_str());
  for(int i = 0; i < (int) cv_tuning_samples.size(); ++i){
   cv_tuning_samples[i].output_sample(ofs,min_concentration, max_concentration, maxs, mins);
  }
  ofs.close();
  oss.str("");
  oss << s.directory << "/cv"<<cv<<"/scale_factors.dat";
  ofs.open(oss.str().c_str());
  ofs << min_concentration << " " << max_concentration << endl;
  ofs.close();
  oss.str("");
  oss << s.directory << "/cv"<<cv<<"/actual.dat";
  ofs.open(oss.str().c_str());
  for(int i = 0; i < (int)cv_testing_samples.size(); ++i){
   ofs << cv_testing_samples[i].avg_concentration << endl;
  }
  ofs.close();
  oss.str("");
 }

 /*output all feature vectors to features.dat*/
 vector<double> maxs;
 vector<double> mins;
 int first = 0;
 //if(cv == 0) first = 1;
 double min_concentration = samples[first].avg_concentration;
 double max_concentration = samples[first].avg_concentration;
 
 for(int i = 0; i < (int) samples[first].min_values.size(); ++i){
  maxs.push_back(samples[first].max_values[i]);
  mins.push_back(samples[first].min_values[i]);
 }
 for(int i = 0; i < (int) samples.size(); ++i){
  min_concentration = min(min_concentration,samples[i].avg_concentration);
  max_concentration = max(max_concentration,samples[i].avg_concentration);
  for(int j = 0; j < (int) samples[i].min_values.size(); ++j){
   maxs[j] = max(maxs[j],samples[i].max_values[j]);
   mins[j] = min(mins[j],samples[i].min_values[j]);
  }
 }
 oss << s.directory << "/scale_factors.dat";
 ofstream ofs(oss.str().c_str());
 ofs << min_concentration << " " << max_concentration << endl;
 for(int i = 0; i < (int) mins.size(); ++i){
  ofs << mins[i] << " " << maxs[i] << endl;
 }
 ofs.close();
 oss.str("");
 oss << s.directory << "/features.dat";
 ofs.open(oss.str().c_str());
 for(int i = 0; i < (int)samples.size(); ++i){
  samples[i].output_sample(ofs,min_concentration,max_concentration,maxs,mins);
 }
 ofs.close();
 oss.str("");
}
