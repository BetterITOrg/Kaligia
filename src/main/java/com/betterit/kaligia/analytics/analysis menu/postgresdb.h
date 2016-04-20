#ifndef __POSTGRESDB_H__
#define __POSTGRESDB_H__
#include "settings.h"
class postgresdb{
 public:
  static void load_db(settings &s);
};
#endif
