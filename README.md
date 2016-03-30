Kaligia Application

RVL16.3.0
- Updated Schema to add Endpoint related Entitied and updated User attributes
-   Please create a new kaligia schema and run the resource/sql/KaligiaBiosciences.ddl
-   Insert metadata using resource/sql/insert_sample.sql
-Enabled Spring Securtity
- use defauld user kjohar/kjohar
- create new user (bcrpt passwd in user tables)
- Added new tables for Result Analysus and Procedure Parameters
- Deployed in Kaligia on Mar 24, 2016
- Merged and Tagged Master
- Branch RVL16.2.0 is deleted
- Branch RVL16.3.0 will be used for all hotfixes
- Branch RVL16.4.0 will be used for all enhancements

RVL16.4.0
- Created on Mar 25, 2016
- Integrated Webcam
- 3 external jars to bbe added
- mvn install:install-file -Dfile=src/main/resources/jars/webcam-capture-0.3.10-RC7.jar -DgroupId=com.github.sarxos -DartifactId=webcam-capture -Dversion=0.3.7 -Dpackaging=jar
mvn install:install-file -Dfile=src/main/resources/jars/slf4j-api-1.7.2.jar -DgroupId=org.slf4j -DartifactId=slf4j-api -Dversion=1.7.2 -Dpackaging=jar
mvn install:install-file -Dfile=src/main/resources/jars/bridj-0.6.3-20130316.190111-13.jar -DgroupId=com.nativelibs4java -DartifactId=bridj -Dversion=0.6.3 -Dpackaging=jar

