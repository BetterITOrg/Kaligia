CC = g++
cflags = -g -Wall --std=c++0x
objects = remove_background.o make_cv_files.o parameter_tuning.o do_cv.o run_model.o create_model.o feature_selection.o genetic_selection.o
all: menu

menu: $(objects) menu.cpp settings.h FLremoval/FLremovalMain.class
	$(CC) $(objects) menu.cpp -o menu $(cflags)
remove_background.o: remove_background.h remove_background.cpp settings.h
	$(CC) remove_background.cpp -c $(cflags)
make_cv_files.o: make_cv_files.cpp make_cv_files.h settings.h
	$(CC) make_cv_files.cpp -c $(cflags)
parameter_tuning.o: parameter_tuning.h parameter_tuning.cpp settings.h
	$(CC) parameter_tuning.cpp -c $(cflags)
do_cv.o: do_cv.cpp do_cv.h settings.h
	$(CC) do_cv.cpp -c $(cflags)
feature_selection.o: feature_selection.cpp feature_selection.h settings.h genetic_selection.h
	$(CC) feature_selection.cpp -c $(cflags)
create_model.o: create_model.cpp create_model.h settings.h
	$(CC) create_model.cpp -c $(cflags)
run_model.o: run_model.cpp run_model.h settings.h
	$(CC) run_model.cpp -c $(cflags)
genetic_selection.o: genetic_selection.cpp genetic_selection.h settings.h
	$(CC) genetic_selection.cpp -c $(cflags)
FLremoval/FLremovalMain.class: FLremoval/FLremovalMain.java FLremoval/PolyVal.java
	javac -cp "FLremoval/lib/*;FLremoval/" FLremoval/FLremovalMain.java

clean:
	rm -rf $(objects) menu.exe FLremoval/*.class
