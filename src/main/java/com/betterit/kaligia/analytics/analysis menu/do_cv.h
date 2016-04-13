#ifndef __DO_CV_H__
#define __DO_CV_H__
#include <cstdlib>
#include <iostream>
#include <fstream>
#include <sstream>
#include <cmath>
#include <vector>
#include "settings.h"
class do_cv{
 public:
  static double run(settings &s, bool tuning = false);
};
#endif
