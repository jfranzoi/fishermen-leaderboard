#!/usr/bin/env bash

echo "Checking yoke distribution"
[[ -d yoke ]] || curl -L -s https://github.com/GuccioGucci/yoke/releases/download/2.12/install.sh | bash

echo "Deploying"
./yoke/yoke install -c public-ecs01 -s fishermen-leaderboard -t latest -f values-public.yaml

echo "Done"