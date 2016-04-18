@ECHO OFF 

:: Set Java variable
setx JAVA_HOME "%HOMEDRIVE%\program files\Java\jdk1.8.0_31" > nul 2>&1

:: Set Kaligia home variable
setx KALIGIA_HOME "%HOMEDRIVE%\Users\v135012\Documents\VentureE\Kaligia\App"  > nul 2>&1

set JPS=c:\progra~1\Java\jdk1.8.0_31\bin\jps > nul 2>&1

echo %DATE% %TIME% Stopping Kaligia .... >> stopKaligia.log

for /f "tokens=1" %%i in ('%JPS% -m ^| find "kaligia"') do ( taskkill /F /PID %%i ) 

echo %DATE% %TIME% Stopped Kaligia ....  >> stopKaligia.log