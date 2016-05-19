@ECHO ON

:: Set Java variable
setx JAVA_HOME "c:\Progra~1\Java\jdk1.8.0_73" /m

:: Set Kaligia home variable
setx KALIGIA_HOME "c:\Kaligia" /m

:: Change working directory
:: cd $KALIGIA_HOME

:: Spring Parameters
set SPRING_CONFIG_LOC=file:C:/Kaligia/config/application.yml
set SPRING_ACTIVE_PROFILE=local

:: Time Stamp
set CurrentDate=%date:~-4,4%%date:~-7,2%%date:~-10,2%
set CurrentTime=%time:~-11,2%%time:~-8,2%

:: Start App
start /b "kaligia" "%JAVA_HOME%\bin\java" -jar C:\Kaligia\bin\kaligia-1.0.jar -Dspring.active.profile=%SPRING_ACTIVE_PROFILE% -Dspring.config.location=%SPRING_CONFIG_LOC%  > %KALIGIA_HOME%\logs\console_%CurrentDate%_%CurrentTime%.out 2>&1

::exit