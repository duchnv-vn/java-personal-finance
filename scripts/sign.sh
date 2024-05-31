#!/bin/bash

source .env

# $sign4j \
#     java -jar $JSIGN_2_FILE_PATH \
#     --alias codesomething.site \
#     --keystore keystore.jks \
#     --storepass $PASSWORD \
#     --storetype pkcs12 \
#     MyFinance.exe

"$signtool" sign -f keystore.jks MyFinance.exe
