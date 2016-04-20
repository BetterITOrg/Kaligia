#ifndef __POSTGRESDB_H__
#define __POSTGRESDB_H__
#include "settings.h"
#include <vector>
class Wavenumber{
 public:
 int wavenum;
 double photon_count;
};
class Repeat{
 public:
 std::vector<Wavenumber> wavenumbers;
};
class db_sample{
 public:
 std::vector<Repeat> repeats;
 Repeat avg_repeat(){
  Repeat avg;
  avg.wavenumbers.resize(repeats[0].wavenumbers.size());
  for(int i = 0; i < (int)avg.wavenumbers.size(); ++i){
   avg.wavenumbers[i].photon_count = 0;
   avg.wavenumbers[i].wavenum = repeats[0].wavenumbers[i].wavenum;
   for(int j = 0; j < (int)repeats.size(); ++j){
    avg.wavenumbers[i].photon_count += repeats[j].wavenumbers[i].photon_count*1.0 / repeats.size();
   }
  }
  return avg;
 }
 double bgl;
};
class Test{
 public:
 std::vector<db_sample> samples;
 /*Sample average_sample(){
  Sample avg;
  avg.repeats.resize(samples[0].repeats.size());
  for(int i = 0; i < (int)avg.repeats.size(); ++i){
   for(int j = 0; j < (int)samples[0].repeats[i].wavenumbers.size(); ++j){
    double avg_photon_count = 0;
    for(int k = 0; k < (int)samples.size(); ++k){
     avg_photon_count += samples[k].repeats[i].wavenumbers[j].photon_count*1.0/samples.size();
    }
    Wavenumber wavenum;
    wavenum.photon_count = avg_photon_count;
    wavenum.wavenum = samples[0].repeats[i].wavenumbers[j].wavenum;
    avg.repeats[i].wavenumbers.push_back(wavenum);
   }
  }
  return avg;
 }*/
};
class postgresdb{
 public:
  static void load_db(settings &s);
};
#endif
