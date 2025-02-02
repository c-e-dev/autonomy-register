#!/bin/bash
export SPRING_ENV=prod
echo $SPRING_ENV

/opt/java/jdk-17.0.6-full/bin/java -DSPRING_ENV=prod -Dfile.encoding=UTF-8 -jar web-1.0-SNAPSHOT.jar ru.c_energies.web.Main -Dspring.config.location=application.properties