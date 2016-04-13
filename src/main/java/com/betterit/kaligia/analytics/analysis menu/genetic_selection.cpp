#include "genetic_selection.h"
#include "do_cv.h"
#include <iostream>
#include <vector>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <algorithm>

#define MIN_GAMMA -14
#define MAX_GAMMA -1
#define MIN_COST 8
#define MAX_COST 15
using namespace std;
class genotype;
class offspring{
 public:
 genotype *g1,*g2;
};
class genotype{
 public:
  int nc,ng,nf;
  vector<bool> gc,gg,gf;
  settings s;
  double fitness;
  int age;
  genotype(int nc, int ng, int nf, const settings &s){
   this->nc = nc;
   this->ng = ng;
   this->nf = nf;
   this->s = s;
   gc.resize(nc);
   gg.resize(ng);
   gf.resize(nf);
   fitness = 0;
   age = 0;
   for(int i = 0; i < nc; ++i){
    gc[i] = (bool)(rand()%2);
   }
   for(int i = 0; i < ng; ++i){
    gg[i] = (bool)(rand()%2);
   }
   if(s.selected_features.empty()){
    for(int i = 0; i < nf; ++i){
     gf[i] = (bool)(rand()%800 < 40);
     //gf[i] = false;
    }
   }else{
    //vector<int> features = {47,59,81,165,167,191,224,228,229,244,245,246,283,284,290,317,339,348,380,418,419,506,507,508,509,531,539,587,662,663,668,729,732,767,787};
    for(set<int>::iterator it = s.selected_features.begin(); it != s.selected_features.end(); ++it){
     gf[*it] = true;
    }
   }
  }
  long unsigned int vec_to_int(const vector<bool> &vec){
   long unsigned int retval = 0;
   for(int i = 0; i < (int)vec.size(); ++i){
    if(vec[i]){
     retval |= 1<<i;
    }
   }
   return retval;
  }
  bool operator< (const genotype &other){
   return fitness < other.fitness;
  }
  void evaluate(void){
   s.selected_features.clear();
   for(int i = 0; i < nf; ++i){
    if(gf[i]){
     s.selected_features.insert(i);
    }
   }
   s.gamma = pow(2,MIN_GAMMA + 1.0*(MAX_GAMMA - MIN_GAMMA)*vec_to_int(gg)/((2<<ng)-1));
   s.cost = pow(2,MIN_COST + 1.0*(MAX_COST - MIN_COST)*vec_to_int(gg)/((2<<ng)-1));
   //clog << "cost " << s.cost << " gamma " << s.gamma << " num. features " << s.selected_features.size()<< endl;
   fitness = do_cv::run(s,true);
  }
  offspring create_offspring(genotype &other){
   offspring children;
   ++age;
   ++other.age;
   children.g1 = new genotype(nc,ng,nf,s);
   children.g2 = new genotype(nc,ng,nf,s);
   //crossover and mutate for cost, gamma, and features
   //choose gamma from one parent
   if(rand()%2 == 0){
    children.g1->gc = gc;
    children.g2->gc = other.gc;
   }else{
    children.g1->gc = other.gc;
    children.g2->gc = gc;
   }
   for(int i = 0; i < nc; ++i){
    /*if(rand()%2 == 0){
     children.g1->gc[i] = gc[i];
     children.g2->gc[i] = other.gc[i];
    }else{
     children.g1->gc[i] = other.gc[i];
     children.g2->gc[i] = gc[i];
    }*/
    if(rand()%nc == 0){
     children.g1->gc[i] = !children.g1->gc[i];
    }
    if(rand()%nc == 0){
     children.g2->gc[i] = !children.g2->gc[i];
    }
   }
   if(rand()%2 == 0){
    children.g1->gg = gg;
    children.g2->gg = other.gg;
   }else{
    children.g1->gg = other.gg;
    children.g2->gg = gg;
   }
   for(int i = 0; i < ng; ++i){
    /*if(rand()%2 == 0){
     children.g1->gg[i] = gg[i];
     children.g2->gg[i] = other.gg[i];
    }else{
     children.g1->gg[i] = other.gg[i];
     children.g2->gg[i] = gg[i];
    }*/
    if(rand()%ng == 0){
     children.g1->gg[i] = !children.g1->gg[i];
    }
    if(rand()%ng == 0){
     children.g2->gg[i] = !children.g2->gg[i];
    }
   }
   for(int i = 0; i < nf; ++i){
    if(rand()%2 == 0){
     children.g1->gf[i] = gf[i];
     children.g2->gf[i] = other.gf[i];
    }else{
     children.g1->gf[i] = other.gf[i];
     children.g2->gf[i] = gf[i];
    }
    if(rand()%nf == 0){
     children.g1->gf[i] = !children.g1->gf[i];
    }
    if(rand()%nf == 0){
     children.g2->gf[i] = !children.g2->gf[i];
    }
   }
   return children;
  }
  
};
void replace(vector<genotype> &g1, vector<genotype> &g2){
 int g1_index = 0;
 int g2_index = 0;
 vector<genotype> dest;
 while (g1_index < (int)g1.size() and g2_index < (int)g2.size()){
  if(g1[g1_index].fitness <= g2[g2_index].fitness){
   dest.push_back(g1[g1_index++]);
  }else{
   dest.push_back(g2[g2_index++]);
  }
 }
 while(g1_index < (int)g1.size()){
  dest.push_back(g1[g1_index++]);
 }
 while(g2_index < (int)g2.size()){
  dest.push_back(g2[g2_index++]);
 }
 for(int i = 0 ; i < (int)g1.size(); ++i){
  g1[i] = dest[i];
 }
}
void genetic_selection::run(settings &s){
 //genotype g(12,12,800,s);
 vector<genotype> population_pool;
 for(int i = 0; i < 20; ++i){
  genotype g(12,12,800,s);
  population_pool.push_back(g);
 }
 clog << "evaluating initial population" << endl;
 for(int i = 0; i < (int)population_pool.size(); ++i){
  population_pool[i].evaluate();
 }
 while(true){
  sort(population_pool.begin(),population_pool.end());
  clog << "creating and evaluating children" << endl;
  vector<genotype> children;
  /*for(int i = 0; i < (int)population_pool.size()-1; i+=2){
   offspring offs = population_pool[i].create_offspring(population_pool[i+1]);
   offs.g1->evaluate();
   offs.g2->evaluate();
   children.push_back(*offs.g1);
   children.push_back(*offs.g2);
  }*/
  random_shuffle(population_pool.begin(),population_pool.end());
  for(int i = 0; i < (int)population_pool.size()-1; i+=2){
   offspring offs = population_pool[i].create_offspring(population_pool[i+1]);
   offs.g1->evaluate();
   offs.g2->evaluate();
   children.push_back(*offs.g1);
   children.push_back(*offs.g2);
  }
  sort(population_pool.begin(),population_pool.end());
  sort(children.begin(), children.end());
  replace(population_pool,children);
  if(population_pool[0].age > 5){
   s = population_pool[0].s;
   break;
  }else{
   clog << "best RMSE = " << population_pool[0].fitness << endl;
   clog << "age = " << population_pool[0].age << endl;
  }
 }
 for(set<int>::iterator it = s.selected_features.begin(); it != s.selected_features.end(); ++it){
  clog << *it << " ";
 }
 clog << endl << "RMSE: " << population_pool[0].fitness << endl;
 clog << "Cost: " << s.cost << endl;
 clog << "Gamma: " << s.gamma << endl;
}
