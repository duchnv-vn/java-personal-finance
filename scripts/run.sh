#!/bin/bash

set -e # quit on error
set -u # quit if we attempt to use undefined environment variable
set -x # show commands as they're executed (remove this line if done debugging)

java -cp dist/class Main
