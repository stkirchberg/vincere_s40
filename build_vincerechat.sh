#!/bin/bash
# Ersetze den gesamten Inhalt mit diesem Code:

# Pfade zu den Java 8 Tools
JAVA="/usr/lib/jvm/java-8-openjdk/bin/java"
JAVAC="/usr/lib/jvm/java-8-openjdk/bin/javac"
JAR="/usr/lib/jvm/java-8-openjdk/bin/jar"

# Pfade zu den Nokia/Microemulator Libs (Pass diese an, falls sie woanders liegen)
CP="/usr/share/java/microemulator/lib/midpapi20.jar:/usr/share/java/microemulator/lib/cldcapi11.jar"

echo "--- Reinigung ---"
rm -rf bin *.jar
mkdir bin

echo "--- Kompilieren ---"
# Wir nutzen Vincere mit großem V
$JAVAC -source 1.3 -target 1.3 -cp "$CP" -d bin src/Vincere.java

echo "--- Verpacken ---"
# Wir erstellen die vincere.jar
$JAR cvfm vincere.jar manifest.mf -C bin .

echo "--- Starten ---"
# Wir starten den Emulator mit Java 8
$JAVA -cp "/usr/share/java/microemulator/microemulator.jar:$CP" org.microemu.app.Main vincere.jar