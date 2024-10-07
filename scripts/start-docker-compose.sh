#!/bin/bash

PROJECT_NAME="atlas"

echo "Stopping and removing current containers..."
if docker-compose -f docker-compose/docker-compose.yml -p "$PROJECT_NAME" down; then
    echo "Containers stopped and removed successfully."
else
    echo "Failed to stop and remove containers." >&2
    exit 1
fi

echo "Starting services..."
if docker-compose -f docker-compose/docker-compose.yml -p "$PROJECT_NAME" up -d; then
    echo "Services started successfully."
else
    echo "Failed to start services." >&2
    exit 1
fi
