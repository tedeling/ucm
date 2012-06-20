#!/bin/sh

cd /home/cmer/bin/import_client

CLASSPATH=$(echo "lib"/*.jar | tr ' ' ':')
CLASSPATH=$CLASSPATH:./conf/

java -classpath "$CLASSPATH" nl.tecon.cme.client.boot.DataImport
