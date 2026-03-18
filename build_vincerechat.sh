JAVA="/usr/lib/jvm/java-8-openjdk/bin/java"
JAVAC="/usr/lib/jvm/java-8-openjdk/bin/javac"
JAR="/usr/lib/jvm/java-8-openjdk/bin/jar"
CP="/usr/share/java/microemulator/lib/midpapi20.jar:/usr/share/java/microemulator/lib/cldcapi11.jar"

rm -rf bin vincere.jar
mkdir bin

$JAVAC -source 1.3 -target 1.3 -cp "$CP" -d bin src/*.java

echo "MIDlet-1: Vincere,,Vincere" > manifest.mf
echo "MIDlet-Name: Vincere" >> manifest.mf
echo "MIDlet-Version: 1.0" >> manifest.mf
echo "MIDlet-Vendor: Custom" >> manifest.mf
echo "MicroEdition-Configuration: CLDC-1.1" >> manifest.mf
echo "MicroEdition-Profiles: MIDP-2.0" >> manifest.mf

$JAR cvfm vincere.jar manifest.mf -C bin .

$JAVA -cp "/usr/share/java/microemulator/microemulator.jar:$CP" org.microemu.app.Main vincere.jar