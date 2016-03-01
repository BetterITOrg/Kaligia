@ECHO ON

:: Set Java variable
setx JAVA_HOME "%HOMEDRIVE%\program files\Java\jdk1.8.0_31" > nul 2>&1

:: Set Kaligia home variable
setx KALIGIA_HOME "%HOMEDRIVE%\Users\v135012\Documents\VentureE\Kaligia\App"  > nul 2>&1

:: Change working directory
:: cd $KALIGIA_HOME

:: Spring Parameters
set SPRING_CONFIG_LOC=file:C:/Users/v135012/Documents/VentureE/Kaligia/config/application.yml
set SPRING_ACTIVE_PROFILE=local

:: Start App
start /b "kaligia" "%JAVA_HOME%\bin\java.exe" -jar %KALIGIA_HOME%\bin\kaligia-1.0.jar -Dspring.active.profile=%SPRING_ACTIVE_PROFILE% -Dspring.config.location=%SPRING_CONFIG_LOC%  > %KALIGIA_HOME%\logs\console.out 2>&1

exit