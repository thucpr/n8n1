#!/bin/bash

# Build script for Docker image
set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}Building Food Order Application Docker Image...${NC}"

# Build the Docker image
docker build -t food-order-app:latest .

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Docker image built successfully!${NC}"
    echo -e "${YELLOW}Image name: food-order-app:latest${NC}"
    echo ""
    echo -e "${YELLOW}To run the container:${NC}"
    echo "docker run -p 8080:8080 food-order-app:latest"
    echo ""
    echo -e "${YELLOW}Or use docker-compose:${NC}"
    echo "docker-compose up"
    echo ""
    echo -e "${YELLOW}To view running containers:${NC}"
    echo "docker ps"
    echo ""
    echo -e "${YELLOW}To stop the container:${NC}"
    echo "docker stop \$(docker ps -q --filter ancestor=food-order-app:latest)"
else
    echo -e "${RED}❌ Docker build failed!${NC}"
    exit 1
fi
