#!/bin/bash
JAVA8="/usr/lib/jvm/java-8-openjdk/bin/java"
JAVAC8="/usr/lib/jvm/java-8-openjdk/bin/javac"
CP="/usr/share/java/microemulator/microemu-midp-api.jar"

rm -rf bin
mkdir -p bin

# 1. Kompilieren (Wir kompilieren jetzt Vincere.java)
$JAVAC8 -source 1.3 -target 1.3 -cp "$CP" -d bin src/vincere.java

# 2. Jar packen (Die JAR heißt jetzt vincere.jar)
jar cvfm vincere.jar manifest.mf -C bin .

# 3. Emulator starten
microemulator vincere.jar