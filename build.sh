#!/usr/bin/env bash

echo "Building"
docker-compose build

docker tag fishermen-leaderboard-proxy:latest ghcr.io/jfranzoi/fishermen-leaderboard-proxy:latest
docker tag fishermen-leaderboard-proxy:latest ghcr.io/jfranzoi/fishermen-leaderboard-proxy:00-$( git rev-parse --short HEAD )

docker tag fishermen-leaderboard-api:latest ghcr.io/jfranzoi/fishermen-leaderboard-api:latest
docker tag fishermen-leaderboard-api:latest ghcr.io/jfranzoi/fishermen-leaderboard-api:00-$( git rev-parse --short HEAD )

docker tag fishermen-leaderboard-ui:latest ghcr.io/jfranzoi/fishermen-leaderboard-ui:latest
docker tag fishermen-leaderboard-ui:latest ghcr.io/jfranzoi/fishermen-leaderboard-ui:00-$( git rev-parse --short HEAD )

echo "Publishing"

docker push ghcr.io/jfranzoi/fishermen-leaderboard-proxy:latest
docker push ghcr.io/jfranzoi/fishermen-leaderboard-proxy:00-$( git rev-parse --short HEAD )

docker push ghcr.io/jfranzoi/fishermen-leaderboard-api:latest
docker push ghcr.io/jfranzoi/fishermen-leaderboard-api:00-$( git rev-parse --short HEAD )

docker push ghcr.io/jfranzoi/fishermen-leaderboard-ui:latest
docker push ghcr.io/jfranzoi/fishermen-leaderboard-ui:00-$( git rev-parse --short HEAD )

echo "Done"