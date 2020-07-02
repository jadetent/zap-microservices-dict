#!/bin/sh

java -jar -Dspring.profiles.active=${PROFILES} /app/target/zap-comprovantes.jar
