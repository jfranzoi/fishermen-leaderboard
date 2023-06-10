#!/usr/bin/env bash

mvn clean package -DskipTests
docker build -t fishermen-leaderboard-api:latest .