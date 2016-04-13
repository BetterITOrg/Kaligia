#include "remove_background.h"
using namespace std;

void remove_background::clean(settings &s){
 ifstream ifs;
 int excitation_wavenumber = 12048; //ecitation wavelength = 830 -> 1/830 * 1e7 = 12048
 string bg_base, bg_extension, rd_base, rd_flag, rd_extension;
 int bg_start, bg_end, sample_start, sample_end, reading_start, reading_end;
 cout << "Specify Background Noise Files:" << endl;
 cout << "Format: <base><number_range><extension>" << endl;
 cout << "Base: ";
 cin >> bg_base;
 cout << "Number Range <start end>: ";
 cin >> bg_start >> bg_end;
 cout << "Extension: ";
 cin >> bg_extension;
 cout << "Specify Readings Files:" << endl;
 cout << "Format: <base><sample_range><reading_flag><reading_range><extension>" << endl;
 cout << "Base: ";
 cin >> rd_base;
 cout << "Sample number range <start end>: ";
 cin >> sample_start >> sample_end;
 cout << "reading flag: ";
 cin >> rd_flag;
 cout << "Reading number range <start end>: ";
 cin >> reading_start >> reading_end;
 cout << "Extension: ";
 cin >> rd_extension; 
 vector<vector<spectrum> > backgrounds(bg_end-bg_start+1);//vector of bg spectra
 vector<spectrum> avg_background;
 vector<vector<vector<spectrum> > > readings(bg_end-bg_start+1);//vector of raw data [# of samples][# of readings/sample]
 vector<vector<vector<spectrum> > > raman(bg_end-bg_start+1);//vector of cleaned data
 for(int i = bg_start; i <= bg_end; ++i){
  ostringstream oss;
  oss << bg_base << i << bg_extension;
  ifs.open(oss.str().c_str());
  if(!ifs.fail()){
   while(!ifs.eof()){
    int wav_len;
    double amp;
    ifs >> wav_len >> amp;
    if(!ifs.fail()){
     spectrum s;
     s.wavelength = wav_len;
     s.amplitude = amp;
     backgrounds[i-bg_start].push_back(s);
    }
   }
  }
  ifs.close();
  readings[i-bg_start].resize(reading_end-reading_start+1);
  raman[i-bg_start].resize(sample_end-sample_start+1);
 }
 for(int i = sample_start; i <= sample_end; ++i){
  for(int j = reading_start; j <= reading_end; ++j){
   ostringstream oss;
   oss << rd_base << i << rd_flag << j << rd_extension;
   ifs.open(oss.str().c_str());
   if(!ifs.fail()){
    while(!ifs.eof()){
     int wav_len;
     double amp;
     ifs >> wav_len >> amp;
     if(!ifs.fail()){
      spectrum s;
      s.wavelength = wav_len;
      s.amplitude = amp;
      readings[i-sample_start][j-reading_start].push_back(s);
     }
    }
   }else{
    cerr << "unable to open " << oss.str() << endl;
   }
   ifs.close();
  }
 }
 for(int i = 0; i < (int)backgrounds[0].size(); ++i){
  spectrum avg_intensity;
  avg_intensity.amplitude = 0;
  avg_intensity.wavelength = backgrounds[0][i].wavelength;
  for(int j = 0; j < (int)backgrounds.size(); ++j){
   avg_intensity.amplitude += backgrounds[j][i].amplitude / backgrounds.size();
  }
  avg_background.push_back(avg_intensity);
 }
 for(int i = 0; i < sample_end-sample_start+1; ++i){
  for(int j = 0; j < reading_end-reading_start+1; ++j){
   ostringstream oss;
   oss << rd_base << i+sample_start << rd_flag << j+reading_start << ".clean" << rd_extension;
   ofstream ofs;
   ofs.open(oss.str().c_str());
   for(int k = 0; k < (int) readings[i][j].size(); ++k){
    ofs << (excitation_wavenumber - readings[i][j][k].wavelength) << " " << (readings[i][j][k].amplitude - avg_background[k].amplitude) << endl;
   }
   ofs.close();  
  }
 }
}

