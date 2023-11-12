#!/bin/bash

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <CREDENTIALS FILE>"
  exit 1
fi

echo "Authenticating..."
cat $1 | docker login -u _json_key --password-stdin https://europe-north1-docker.pkg.dev

echo "Building new image..."
docker build . -t kmmk-backend
IMAGE_ID=$(docker images | awk '{print $3}' | awk 'NR==2')
echo "Built image ID: ${IMAGE_ID}"

echo "Tagging new image..."
docker tag ${IMAGE_ID} europe-north1-docker.pkg.dev/klagomurens-musikklubb/kmmk-backend-repository/kmmk-backend:latest

echo "Pushing new image..."
docker push europe-north1-docker.pkg.dev/klagomurens-musikklubb/kmmk-backend-repository/kmmk-backend:latest
