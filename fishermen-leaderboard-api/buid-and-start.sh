#!/usr/bin/env bash

mvn clean package -DskipTests
docker build -t fishermen-leaderboard-api:latest .

docker run "$@" --rm --name fishermen-leaderboard-api \
	-p 8080:8080 \
	fishermen-leaderboard-api:latest