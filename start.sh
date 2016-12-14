#!/bin/sh
DEPLOY_FOLDER="$CINEMA_HOME/deploy"
if ! [ -d "$DEPLOY_FOLDER" ]; then
	echo "Creating $DEPLOY_FOLDER"
	mkdir -p "$DEPLOY_FOLDER"
fi
if [ -e "$DEPLOY_FOLDER/pid" ]; then
    OLD_PID=`cat "$DEPLOY_FOLDER/pid"`
    echo "Killing old process with pid $OLD_PID"
	kill -9 $OLD_PID
fi
echo "Removing old deploy files"
rm $DEPLOY_FOLDER/cinema.jar
cp "build/libs/cinema.jar" "$DEPLOY_FOLDER"
java -jar build/libs/*.jar &
PID=$!
echo "Saving pid $PID to $DEPLOY_FOLDER/pid"
echo $PID > $DEPLOY_FOLDER/pid
