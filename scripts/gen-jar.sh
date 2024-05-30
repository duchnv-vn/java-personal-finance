#!/bin/bash

set -e # quit on error
set -u # quit if we attempt to use undefined environment variable
set -x # show commands as they're executed (remove this line if done debugging)

cd dist/class

jar cvfe ../../bin/Main.jar Main *.class */*.class
