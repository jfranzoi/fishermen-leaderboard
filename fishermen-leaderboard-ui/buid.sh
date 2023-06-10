#!/usr/bin/env bash

yarn install ; yarn build
docker build -t fishermen-leaderboard-ui:latest .