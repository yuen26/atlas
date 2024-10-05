#!/bin/bash

PROJECT_NAME="atlas"

# Parse command-line arguments
BUILD_FLAG=false

while [[ "$#" -gt 0 ]]; do
    case $1 in
       --build)
           BUILD_FLAG=true
           ;;
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

echo "Starting services..."
if [ "$BUILD_FLAG" = true ]; then
    # Pass the build option only if the flag is set
    if docker-compose -f docker-compose/docker-compose.yml -p "$PROJECT_NAME" up -d --build; then
        echo "Services started successfully."
    else
        echo "Failed to start services." >&2
        exit 1
    fi
else
    if docker-compose -f docker-compose/docker-compose.yml -p "$PROJECT_NAME" up -d; then
        echo "Services started successfully."
    else
        echo "Failed to start services." >&2
        exit 1
    fi
fi
