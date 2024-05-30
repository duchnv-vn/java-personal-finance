#!/bin/bash

SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"

java -jar $SCRIPT_DIR/Main.jar

exit 0
