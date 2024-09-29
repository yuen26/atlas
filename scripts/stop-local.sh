#!/bin/bash

echo "Stopping services..."
if docker-compose -f docker-compose/docker-compose.yml down; then
    echo "Services stopped successfully."
else
    echo "Failed to stop services." >&2
    exit 1
fi
