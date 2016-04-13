#ifndef __REMOVE_BACKGROUND_H__
#define __REMOVE_BACKGROUND_H__
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include "settings.h"
class spectrum{
 public:
  int wavelength;
  double amplitude;
};
class remove_background{
 public:
  static void clean(settings &s);
};
#endif
