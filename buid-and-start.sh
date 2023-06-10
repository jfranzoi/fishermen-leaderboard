#!/usr/bin/env bash

( cd fishermen-leaderboard-api ; ./buid.sh ; ./start.sh -d )
( cd fishermen-leaderboard-ui ; ./buid.sh ; ./start.sh -d )