#!/usr/bin/env bash

echo "Building"
docker-compose build
docker tag fishermen-leaderboard-api:latest ghcr.io/jfranzoi/fishermen-leaderboard-api:00-$( git rev-parse --short HEAD )
docker tag fishermen-leaderboard-ui:latest ghcr.io/jfranzoi/fishermen-leaderboard-ui:00-$( git rev-parse --short HEAD )

echo "Publishing"
docker push ghcr.io/jfranzoi/fishermen-leaderboard-api:00-$( git rev-parse --short HEAD )
docker push ghcr.io/jfranzoi/fishermen-leaderboard-ui:00-$( git rev-parse --short HEAD )

echo "Done"