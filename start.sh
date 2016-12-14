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
BUILD_ID=dontKillMe nohup java -jar build/libs/*.jar &
echo $! > $DEPLOY_FOLDER/pid
