#!/usr/bin/env bash

yarn install ; yarn build
docker build -t fishermen-leaderboard-ui:latest .

docker run "$@" --rm --name fishermen-leaderboard-ui \
	-p 3000:3000 \
	fishermen-leaderboard-ui:latest