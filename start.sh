#!/usr/bin/env bash

docker run -d --name dev-tools --network infra --restart=unless-stopped dev-tools
docker logs -f dev-tools