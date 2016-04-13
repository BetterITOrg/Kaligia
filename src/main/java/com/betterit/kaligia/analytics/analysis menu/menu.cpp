#include <iostream>
#include <cstdlib>
#include "remove_background.h"
#include "make_cv_files.h"
#include "do_cv.h"
#include "parameter_tuning.h"
#include "settings.h"
#include "feature_selection.h"
#include "create_model.h"
#include "run_model.h"
#include "genetic_selection.h"
using namespace std;
void update_settings(settings &s){
 cout << "1. libsvm path ("<< s.libsvm_path << ")" << endl; 
 cout << "2. directory (" << s.directory << ")" << endl;
 cout << "3. number of folds (" << s.number_folds << ")" << endl;
 cout << "4. gamma, cost (" << s.gamma << ", " << s.cost << ")" << endl;
 cout << "5. files start with offset (" << s.file_offset << ")" << endl;
 cout << "6. specify features (" << s.selected_features.size() << " in use (0 for all))" << endl;
 cout << "7. Go back" << endl;
 int choice;
 cin >> choice;
 if(choice == 7) return;
 cout << "New value > ";
 switch (choice){
  case 1:
   cin >> s.libsvm_path;
   break;
  case 2:
   cin >> s.directory;
   break;
  case 3:
   cin >> s.number_folds;
   break;
  case 4:
   cout << "[gamma cost] ";
   cin >> s.gamma >> s.cost;
   break;
  case 5:
   cin >> s.file_offset;
   break;
  case 6:
   string line;
   cin.ignore(); //clear new line
   getline(cin,line);
   istringstream iss(line);
   s.selected_features.clear();
   while(!iss.eof()){
    int feature_number;
    iss >> feature_number;
    if(!iss.fail()){
     s.selected_features.insert(feature_number);
    }
   }
 }
}
int main_menu(void){
 cout << "1. Remove background noise" << endl;
 cout << "2. Make cross validation files" << endl;
 cout << "3. Run cross validation" << endl;
 cout << "4. Do parameter tuning" << endl;
 cout << "5. feature selection" << endl;
 cout << "6. Create model" << endl;
 cout << "7. Run model" << endl;
 cout << "8. Settings" << endl;
 cout << "9. Exit" << endl;
 int choice;
 cin >> choice;
 if(choice == 9){
  exit(0);
 }
 return choice;
}
int main(void){
 settings s;
 while(true){
  int choice = main_menu();
  if(choice == 1){
   remove_background::clean(s);
  }
  if(choice == 2){
   make_cv::run(s);
  }
  if(choice == 3){
   do_cv::run(s);
  }
  if(choice == 4){
   parameter_tuning::run(s);
  }
  if(choice == 5){
   feature_selection::run(s);
  }
  if(choice == 6){
   create_model::run(s);
  }
  if(choice == 7){
   run_model::run(s);
  }
  if(choice == 8){
   update_settings(s);
  }
 }
}
