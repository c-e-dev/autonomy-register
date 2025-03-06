set SPRING_ENV=prod

jdk-17.0.2\bin\java -DSPRING_ENV=prod -Dfile.encoding=UTF-8 -jar web-1.0-SNAPSHOT.jar ru.c_energies.web.Main -Dspring.config.location=application.properties