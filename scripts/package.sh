#!/bin/bash

#путь, где лежат папки с jdk для упаковки с приложением
#PATH_JAVA_JDK=$1
JDK="17.0.2"
VERSION_JDK="jdk-"$JDK
VERSION_APP="1.0.0"

cd ../integration_build
########### package linux version ###########
mkdir linux
wget https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-$JDK'_linux-x64_bin.tar.gz'

cp web-1.0-SNAPSHOT.jar linux/web-1.0-SNAPSHOT.jar
cp ../application.properties linux/application.properties
cp ../log4j2.xml linux/log4j2.xml
cp ../reestr.db linux/reestr.db
cp ../README.md linux/README.md
cp ../AGREEMENT.md linux/AGREEMENT.md
cp ../scripts/start_linux.sh linux/start.sh
tar -xvzf openjdk-$JDK'_linux-x64_bin.tar.gz' -C linux
tar -czvf autonomy-register-$VERSION_APP-linux-x64.tar.gz linux

rm -rf openjdk*
rm -rf linux

########### package windows version ###########
mkdir windows
wget https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-$JDK'_windows-x64_bin.zip'

cp web-1.0-SNAPSHOT.jar windows/web-1.0-SNAPSHOT.jar
cp ../application.properties windows/application.properties
cp ../log4j2.xml windows/log4j2.xml
cp ../reestr.db windows/reestr.db
cp ../README.md windows/README.md
cp ../AGREEMENT.md windows/AGREEMENT.md
cp ../scripts/start_windows.bat windows/start.bat
unzip openjdk-$JDK'_windows-x64_bin.zip' -d windows
zip -r autonomy-register-$VERSION_APP-windows-x64.zip windows

rm -rf openjdk*
rm -rf windows

########### package macos version ###########
mkdir macos
wget https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-$JDK'_macos-x64_bin.tar.gz'

cp web-1.0-SNAPSHOT.jar macos/web-1.0-SNAPSHOT.jar
cp ../application.properties macos/application.properties
cp ../log4j2.xml macos/log4j2.xml
cp ../reestr.db macos/reestr.db
cp ../README.md macos/README.md
cp ../AGREEMENT.md macos/AGREEMENT.md
cp ../scripts/start.sh macos/start.sh
tar -xvzf openjdk-$JDK'_macos-x64_bin.tar.gz' -C macos
tar -czvf autonomy-register-$VERSION_APP-macos-x64.tar.gz macos

rm -rf openjdk*
rm -rf macos

exit 0