#!/bin/bash
# Pfad zur API (Wichtig für Arch-Nutzer)
CP="/usr/share/java/microemulator/microemu-midp-api.jar"

# 1. Kompilieren
/usr/lib/jvm/java-8-openjdk/bin/javac -source 1.3 -target 1.3 -cp "$CP" -d bin src/NChat.java

# 2. Manifest erstellen
echo "MIDlet-Name: nChat
MIDlet-Version: 1.0.0
MIDlet-Vendor: Sonja
MIDlet-1: nChat,,NChat
MicroEdition-Profile: MIDP-2.1
MicroEdition-Configuration: CLDC-1.1" > manifest.mf

# 3. Jar packen (Die Datei, die aufs Handy kommt)
jar cvfm nChat.jar manifest.mf -C bin .

# 4. Emulator starten
microemulator nChat.jar
