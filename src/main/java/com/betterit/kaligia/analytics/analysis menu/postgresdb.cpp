#include "postgresdb.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <cmath>
#include <pqxx/pqxx>

using namespace std;
using namespace pqxx;

void postgresdb::load_db(settings &s){
  try{
   connection C("dbname=kaligia user=postgres password=postgres hostaddr=192.168.1.135 port=5432");
   if(C.is_open()){
    cout << "Opened database: " << C.dbname() << endl;
   }else{
    cout << "Didn't open database" << endl;
    return;
   }
   //string query = "select distinct run_id from kaligia.runsegment order by run_id;";
   nontransaction N(C);
   vector<int> run_ids;
   vector<int> order_nos = {419077,419078,419079};
   //for(int i = 419050; i < 419065; ++i){
   // order_nos.push_back(i);
   //}
   ostringstream oss;
   /*oss << "select * from kaligia.runorder where order_id in (select order_id from kaligia.testorder where order_no='" << order_nos[0] << "'";
   for(int i = 1; i < (int)order_nos.size(); ++i){
    oss << " or order_no='" << order_nos[i] << "'";
   }
   oss << ") order by order_id";
   */
   oss << "select * from kaligia.runorder where procedure_id=1000010 and result_notes is not null and run_id > 1000100 order by run_id;";
   //result ids(N.exec("select * from kaligia.runorder where order_id in (select order_id from kaligia.testorder where order_no='419013' or order_no='419014' or order_no='419015') order by order_id;"));
   result ids(N.exec(oss.str().c_str()));
   oss.str("");
   for(int i = 0; i < (int)ids.size(); ++i){
    run_ids.push_back(ids[i][0].as<int>());
   }
   
   ofstream ofs;
   Test test;
   for(int i = 0; i < (int)run_ids.size(); ++i){
    //ostringstream oss;
    oss << "select run_segment_id from kaligia.runsegment where run_id = " << run_ids[i] << " order by run_segment_id;";
    result R(N.exec(oss.str().c_str()));
    oss.str("");
    cout << "id " << run_ids[i] << endl;
    db_sample sample;
    
    for(int j = 0; j < (int)R.size(); ++j){
     oss << "select wavelength, photon_count from kaligia.flremovedlog where run_segment_id = " << R[j][0] << " order by wavelength;";
     result R2(N.exec(oss.str().c_str()));
     oss.str("");
     vector<pair<int,double> > spectrum;
     Repeat repeat;
     for(int k = 0; k < (int)R2.size(); ++k){
      Wavenumber wavenumber;
      int wavelength = (int)(12048-(1e7/R2[k][0].as<double>()));
      wavenumber.wavenum = wavelength;
      wavenumber.photon_count = R2[k][1].as<double>();
      repeat.wavenumbers.push_back(wavenumber);
     }
     
     sample.repeats.push_back(repeat);
    }
    oss << "select run_notes, result_notes from kaligia.runorder where run_id = " << run_ids[i] << ";";
    result BG_Result(N.exec(oss.str().c_str()));
    oss.str("");
    sample.bgl = BG_Result[0][0].as<double>()/2 + BG_Result[0][1].as<double>()/2;
    test.samples.push_back(sample);
   }
   oss << s.directory << s.database_dump_filename;
   ofs.open(oss.str().c_str());
   ofs << "BGL";
   for(int i = 0; i < (int)test.samples[0].repeats[0].wavenumbers.size(); ++i){
    ofs << "," << test.samples[0].repeats[0].wavenumbers[i].wavenum;
   }
   ofs << endl;
   for(int i = 0; i < (int)test.samples.size(); ++i){
    ofs << test.samples[i].bgl;
    Repeat avg = test.samples[i].avg_repeat();
    for(int j = 0; j < (int)avg.wavenumbers.size(); ++j){
     ofs << "," << avg.wavenumbers[j].photon_count;
    }
    ofs << endl;
   }
   ofs.close();
   C.disconnect();
  }catch (const exception &e){
    cerr << e.what() << endl;
    return;
  }
}
