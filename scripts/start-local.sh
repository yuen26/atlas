#!/bin/bash

# Function to start backend services
start_backend() {
    echo "Starting backend services..."
    if docker-compose -f docker/docker-compose.backend.yml -p atlas up -d; then
        echo "Backend services started successfully."
    else
        echo "Failed to start backend services." >&2
        exit 1
    fi
}

# Function to start application services
start_application() {
    echo "Starting application services..."
    if docker-compose -f docker/docker-compose.application.yml -p atlas up -d "$DOCKER_COMPOSE_BUILD_OPTION"; then
        echo "Application services started successfully."
    else
        echo "Failed to start application services." >&2
        exit 1
    fi
}

# Function to start both backend and application services
start_all() {
    start_backend
    start_application
}

# Parse command-line arguments
BUILD_FLAG=false
BACKEND_FLAG=false
APPLICATION_FLAG=false
DOCKER_COMPOSE_BUILD_OPTION=""

while [[ "$#" -gt 0 ]]; do
    case $1 in
       --build)
           BUILD_FLAG=true
           DOCKER_COMPOSE_BUILD_OPTION="--build"
           ;;
       --backend) BACKEND_FLAG=true ;;
       --application) APPLICATION_FLAG=true ;;
       *) echo "Unknown parameter passed: $1"; exit 1 ;;
    esac
    shift
done

# Execute build if '--build' flag is present
if [ "$BUILD_FLAG" = true ]; then
    echo "Building the project..."
    if ./scripts/build.sh; then
        echo "Build completed successfully."
    else
        echo "Build failed." >&2
        exit 1
    fi
fi

# Start backend and application services based on flags
if [ "$BACKEND_FLAG" = false ] && [ "$APPLICATION_FLAG" = false ]; then
    start_all
else
    if [ "$BACKEND_FLAG" = true ]; then
        start_backend
    fi

    if [ "$APPLICATION_FLAG" = true ]; then
        start_application
    fi
fi
