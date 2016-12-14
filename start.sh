#!/bin/sh
DEPLOY_FOLDER="$CINEMA_HOME/deploy"
if ! [ -d "$DEPLOY_FOLDER" ]; then
	echo "Creating $DEPLOY_FOLDER"
	mkdir -p "$DEPLOY_FOLDER"
fi
if [ -e "$DEPLOY_FOLDER/pid" ]; then
	kill -9 `cat "$DEPLOY_FOLDER/pid"`
fi
echo "Removing old deploy files"
rm $DEPLOY_FOLDER/cinema.jar
cp "build/libs/cinema.jar" "$DEPLOY_FOLDER"
java -jar build/libs/*.jar &
PID=$!
echo "Saving pid $PID to $DEPLOY_FOLDER/pid"
echo $PID > $DEPLOY_FOLDER/pid
