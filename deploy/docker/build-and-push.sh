#!/bin/bash
[ -f app.jar ] || echo "Please copy your jar to app.jar"
docker build . -t maxqfz/sharding:latest
docker push maxqfz/sharding:latest