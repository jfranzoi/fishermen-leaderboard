#!/usr/bin/env bash

PATH="$PATH:~/workspace/yoke" yoke install -c public-ecs01 -s fishermen-leaderboard -t 00-$( git rev-parse --short HEAD )