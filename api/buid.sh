#!/usr/bin/env bash

mvn -q clean package -DskipTests
docker build -t fishermen-leaderboard-api:latest .