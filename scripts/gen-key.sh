#!/bin/bash

source .env

# $KEY_TOOL_FILE_PATH \
#     -genkeypair \
#     -alias codesomething.site \
#     -keyalg RSA \
#     -keystore keystore.jks

MakeCert /n publisherName /r /h 0 /eku "1.3.6.1.5.5.7.3.3,1.3.6.1.4.1.311.10.3.13" /e
expirationDate /sv MyKey.pvk MyKey.cer
