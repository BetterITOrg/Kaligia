#include "feature_selection.h"
using namespace std;
//selected features: 97 98 99 102 103 104 143 144 145 146 147 148 149 150 151 152 153 154 155 156 157 158 159 160 161 162 163 164 165 166 167 168 169 170 171 172 173 174 175 176 177 178 179 180 181 182 183 184 185 186 187 188 189 190 191 192 193 194 195 196 197 198 199 200 201 202 203 204 205 206 207 208 209 210 211 212 213 214 215 216 217 218 219 220 221 222 223 224 225 226 227 228 229 230 231 232 233 234 235 236 237 238 239 240 241 242 243 244 245 246 247 248 249 250 251 252 253 254 255 256 257 258 259 260 261 262 263 264 265 269 270 271 272 273 274 275 276 277 278 279 280 281 282 283 284 285 286 287 288 289 290 291 292 293 294 295 296 297 298 299 300 301 302 303 304 305 306 307 308 309 310 311 312 313 314 315 316 317 318 319 320 321 322 323 324 325 326 327 328 329
//error for the selected feature set = 0.602658

void feature_selection::output_features(ofstream &ofs,set<int> &sample_features, vector<vector<double> > &feature_set, vector<double> &concentration){
 for(int i = 0; i < (int) feature_set.size(); ++i){
  ofs << concentration[i] << " ";
  for(set<int>::iterator it = sample_features.begin(); it != sample_features.end(); ++it){
   ofs << *it << ":" << feature_set[i][*it] << " ";
  }
  ofs << endl;
 }
}
void feature_selection::forward_selection(settings &s){
 ifstream ifs;
 ofstream ofs;
 ostringstream oss;
 vector<vector<double> > train_set(s.number_folds-2);
 vector<vector<double> > tune_set(1);
 vector<double> train_concentrations(s.number_folds-2);
 vector<double> tune_concentration(1);

 /*read in testing features*/
 oss << s.directory << "/cv0/tune.svm";
 ifs.open(oss.str().c_str());
 oss.str("");
 string line;
 getline(ifs,line);
 istringstream iss(line);
 iss >> tune_concentration[0];
 while(!iss.eof()){
  int feature_number;
  iss >> feature_number;
  if(!iss.fail()){
   char colon;
   double value;
   iss >> colon >> value;
   tune_set[0].push_back(value);
  }
 }
 ifs.close();
 
 /*read in training features */
 oss << s.directory << "/cv0/train.svm";
 ifs.open(oss.str().c_str());
 int train_vector = 0;
 if(ifs.fail()){
  cerr << "cannot open " << oss.str() << endl;
  return;
 }
 oss.str("");
 while(!ifs.eof()){
  getline(ifs,line);
  //clog << "line # " << train_vector << " = " << line << endl;
  if(ifs.fail()) break;
  istringstream iss(line);
  iss >> train_concentrations[train_vector];
  //clog << "concentration " << train_vector << " = " << train_concentrations[train_vector] << endl;
  while(!iss.eof()){
   int feature_number;
   iss >> feature_number;
   if(!iss.fail()){
    char colon;
    double value;
    iss >> colon >> value;
    train_set[train_vector].push_back(value);
   }
  }
  ++train_vector;
 }
 ifs.close();

 string train_file, tune_file, model_file, predict_file;
 oss << s.directory << "/cv0/fs_train.svm";
 train_file = oss.str();
 oss.str("");
 oss << s.directory << "/cv0/fs_tune.svm";
 tune_file = oss.str();
 oss.str("");
 oss << s.directory << "/cv0/fs_model.svm";
 model_file = oss.str();
 oss.str("");
 oss << s.directory << "/cv0/fs_predict.svm";
 predict_file = oss.str();
 oss.str("");
 ifs.open(tune_file.c_str());
 double actual;
 ifs >> actual;
 ifs.close();
 set<int> used_features,unused_features, chosen_set;
 double chosen_error = 10;
 for(int i = 0; i < (int)tune_set[0].size(); ++i){
  if(!unused_features.empty()){
   unused_features.insert(--unused_features.end(),i);
  }else{
   unused_features.insert(i);
  }
 }
 clog << "features size = " << unused_features.size() << endl;
 while(!unused_features.empty()){
  int best_feature = 0;
  double best_error = 10;
  for(set<int>::iterator it = unused_features.begin(); it != unused_features.end(); ++it){
   set<int> sample_features(used_features);
   sample_features.insert(*it);
   ofs.open(tune_file.c_str());
   output_features(ofs,sample_features,tune_set, tune_concentration);
   ofs.close();
   ofs.open(train_file.c_str());
   output_features(ofs,sample_features,train_set, train_concentrations);
   ofs.close();
   oss << s.libsvm_path << "/svm-train -s 3 -t 2 -q " << train_file << " " << model_file;
   system(oss.str().c_str());
   oss.str("");
   oss << s.libsvm_path << "/svm-predict -q " << tune_file << " " << model_file << " " << predict_file;
   //clog << oss.str() << endl;
   system(oss.str().c_str());
   oss.str("");
   ifstream ifs(predict_file.c_str());
   double prediction;
   ifs >> prediction;
   ifs.close();
   clog << "using feature " << *it << " prediction = " << prediction << " error = " << fabs(prediction - actual) << endl;
   if(fabs(prediction-actual) < best_error){
    best_error = fabs(prediction-actual);
    best_feature = *it;
   }
  }
  clog << "best feature = " << best_feature << " with error = " << best_error << endl;
  used_features.insert(best_feature);
  unused_features.erase(best_feature);
  if(best_error < chosen_error){
   chosen_error = best_error;
   chosen_set = used_features;
  }
 }
 clog << "selected features: ";
 for(set<int>::iterator it = chosen_set.begin(); it != chosen_set.end(); ++it){
  clog << *it << " ";
 }
 clog << endl << "error for the selected feature set = " << chosen_error << endl;
}
typedef struct pcc_feature{
 double pcc;
 int feature_number;
 bool operator< (const pcc_feature &other){
  return pcc < other.pcc;
 }
}pcc_features;

void feature_selection::pcc(settings &s){
 ifstream ifs;
 ofstream ofs;
 ostringstream oss;
 //istringstream iss;
 oss << s.directory << "/features.dat";
 ifs.open(oss.str().c_str());
 if(ifs.fail()){
  cerr << "unable to open " << oss.str() << endl;
  return;
 }
 vector<double> concentrations;
 vector<vector<double> > features;
 vector<double> rxy;//pcc for each feature
 while(!ifs.eof()){
  string line;
  getline(ifs,line);
  if(!ifs.fail()){
   istringstream iss(line);
   double concentration;
   iss >> concentration;
   concentrations.push_back(concentration);
   vector<double> f;
   while(!iss.eof()){
    int feature_number;
    iss >> feature_number;
    if(!iss.fail()){
     char colon;
     double value;
     iss >> colon >> value;
     f.push_back(value);
    }
   }
   features.push_back(f);
  }
 }
 ifs.close();
 rxy.resize(features[0].size());
 clog << "feature count = " << features[0].size() << endl;
 double mean_concentration = 0;
 for(int i = 0; i < (int)concentrations.size(); ++i){
  mean_concentration += 1.0*concentrations[i]/concentrations.size();
 }
 for(int i = 0; i < (int)rxy.size(); ++i){
  double feature_mean = 0;
  for(int j = 0; j < (int)features.size(); ++j){
   feature_mean += features[j][i] / features.size();
  }
  double numerator = 0;
  for(int j = 0; j < (int)concentrations.size(); ++j){
   numerator += (features[j][i] - feature_mean)*(concentrations[j]-mean_concentration);
  }
  double feature_variance = 0;
  double concentration_variance = 0;
  for(int j = 0; j < (int)concentrations.size(); ++j){
   feature_variance += (features[j][i]-feature_mean)*(features[j][i]-feature_mean);
   concentration_variance += (concentrations[j]-mean_concentration)*(concentrations[j]-mean_concentration);
  }
  double denominator = sqrt(feature_variance)*sqrt(concentration_variance);
  rxy[i] = fabs(numerator/denominator);
  //clog << "Rxy[" << i << "] = " << rxy[i] << endl;
 }
 vector<pcc_feature> pcc_features(rxy.size());
 for(int i = 0; i < (int) pcc_features.size(); ++i){
  pcc_features[i].pcc = rxy[i];
  pcc_features[i].feature_number = i;
 }
 sort(pcc_features.rbegin(), pcc_features.rend());
 //for(int i = 0; i < (int)pcc_features.size(); ++i){
 // clog << pcc_features[i].feature_number << " = " << pcc_features[i].pcc << endl;
 //}
 set<int> used_features;
 set<int> selected_features;
 double best_rmse = 100;
 for(int i = 0; i < (int)pcc_features.size(); ++i){
  used_features.insert(pcc_features[i].feature_number);
  s.selected_features = used_features;
  double rmse = do_cv::run(s,true);
  clog << 100.0 * i / pcc_features.size() << "%" << endl;
  if(rmse < best_rmse){
   best_rmse = rmse;
   selected_features = used_features;
  }else{
   used_features.erase(pcc_features[i].feature_number);
  }
 }
 s.selected_features = selected_features;
 clog << "selected features: ";
 for(set<int>::iterator it = s.selected_features.begin(); it != s.selected_features.end(); ++it){
  clog << *it << " ";
 }
 clog << endl << "RMSE = " << best_rmse << endl;
}
void feature_selection::run(settings &s){
 cout << "1. Forward Selection" << endl;
 cout << "2. Pearson's CC" << endl;
 cout << "3. Genetic Selection" << endl;
 int choice;
 cin >> choice;
 if(choice == 1){
  forward_selection(s);
 }
 if(choice == 2){
  pcc(s);
 }
 if(choice == 3){
  genetic_selection::run(s);
 }
 
}
