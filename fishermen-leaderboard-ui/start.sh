#!/usr/bin/env bash

docker run "$@" --rm --name fishermen-leaderboard-ui \
	-p 3000:3000 \
	fishermen-leaderboard-ui:latest