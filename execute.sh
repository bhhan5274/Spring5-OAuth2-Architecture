#! /bin/bash

set -e

./gradlew authorization-server:clean
./gradlew authorization-server:build
./gradlew authorization-server:bootRun
