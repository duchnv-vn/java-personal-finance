#!/bin/bash

jpackage \
    --input ./ \
    --type exe \
    --main-class Main \
    --main-jar ./bin/Main.jar \
    --name MyFinance \
    --icon ./assets/favicon.ico
