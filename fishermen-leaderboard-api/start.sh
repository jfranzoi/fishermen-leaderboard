#!/usr/bin/env bash

docker run "$@" --rm --name fishermen-leaderboard-api \
	-p 8080:8080 \
	fishermen-leaderboard-api:latest