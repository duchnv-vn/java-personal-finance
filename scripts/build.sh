#!/bin/bash

set -e # quit on error
set -u # quit if we attempt to use undefined environment variable
set -x # show commands as they're executed (remove this line if done debugging)

# Run this script from the project root directory
cd src

# Create directory structure
find . -type d | xargs -n 1 -I I --verbose mkdir -p ../dist/class/I

# Use javac to compile files
find . -name "*.java" | javac ./*.java ./*/*.java -d ../dist/class

# Copy assets
find . -type f -and -not -name "*.java" | xargs -n 1 -I I --verbose cp I ../dist/class/I

# Jar command
jar cvf ../dist/$(basename $(readlink -f ..)) -C ../dist/class .
