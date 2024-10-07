#!/bin/bash

# Step 1: Build the Maven project
echo "Starting Maven build..."
if mvn clean install; then
    echo "Maven build completed successfully."
else
    echo "Maven build failed." >&2
    exit 1
fi

# Step 2: Build Docker images for each service
echo "Building Docker image for auth-service..."
if docker build -t auth-service ./services/auth-service; then
    echo "Docker image for auth-service built successfully."
else
    echo "Failed to build Docker image for auth-service." >&2
    exit 1
fi

echo "Building Docker image for job-service..."
if docker build -t job-service ./services/job-service/job-application; then
    echo "Docker image for job-service built successfully."
else
    echo "Failed to build Docker image for job-service." >&2
    exit 1
fi

echo "Building Docker image for notification-service..."
if docker build -t notification-service ./services/notification-service; then
    echo "Docker image for notification-service built successfully."
else
    echo "Failed to build Docker image for notification-service." >&2
    exit 1
fi

echo "Building Docker image for user-service..."
if docker build -t user-service ./services/user-service/user-application; then
    echo "Docker image for user-service built successfully."
else
    echo "Failed to build Docker image for user-service." >&2
    exit 1
fi

echo "Building Docker image for product-service..."
if docker build -t product-service ./services/product-service/product-application; then
    echo "Docker image for product-service built successfully."
else
    echo "Failed to build Docker image for product-service." >&2
    exit 1
fi

echo "Building Docker image for order-service..."
if docker build -t order-service ./services/order-service/order-application; then
    echo "Docker image for order-service built successfully."
else
    echo "Failed to build Docker image for order-service." >&2
    exit 1
fi

echo "Building Docker image for gateway-server..."
if docker build -t gateway-server ./edge/gateway-server; then
    echo "Docker image for gateway-server built successfully."
else
    echo "Failed to build Docker image for gateway-server." >&2
    exit 1
fi
