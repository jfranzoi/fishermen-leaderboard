#!/usr/bin/env bash

REGISTRY="ghcr.io/jfranzoi"
VERSION="00-$( git rev-parse --short HEAD )"

echo "Building version $VERSION"
docker-compose build

docker tag fishermen-leaderboard-proxy:latest $REGISTRY/fishermen-leaderboard-proxy:latest
docker tag fishermen-leaderboard-proxy:latest $REGISTRY/fishermen-leaderboard-proxy:$VERSION

docker tag fishermen-leaderboard-api:latest $REGISTRY/fishermen-leaderboard-api:latest
docker tag fishermen-leaderboard-api:latest $REGISTRY/fishermen-leaderboard-api:$VERSION

docker tag fishermen-leaderboard-ui:latest $REGISTRY/fishermen-leaderboard-ui:latest
docker tag fishermen-leaderboard-ui:latest $REGISTRY/fishermen-leaderboard-ui:$VERSION

echo "Publishing"

docker push $REGISTRY/fishermen-leaderboard-proxy:latest
docker push $REGISTRY/fishermen-leaderboard-proxy:$VERSION

docker push $REGISTRY/fishermen-leaderboard-api:latest
docker push $REGISTRY/fishermen-leaderboard-api:$VERSION

docker push $REGISTRY/fishermen-leaderboard-ui:latest
docker push $REGISTRY/fishermen-leaderboard-ui:$VERSION

echo "Done"