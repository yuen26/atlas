#!/bin/bash

stop_backend() {
    echo "Stopping backend services..."
    if docker-compose -f docker/docker-compose.backend.yml down; then
        echo "Backend services stopped successfully."
    else
        echo "Failed to stop backend services." >&2
        exit 1
    fi
}

stop_application() {
    echo "Stopping application services..."
    if docker-compose -f docker/docker-compose.services.yml down; then
        echo "Application services stopped successfully."
    else
        echo "Failed to stop application services." >&2
        exit 1
    fi
}

stop_all() {
    stop_backend
    stop_application
}

# Stop all services
stop_all
