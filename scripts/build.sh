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
echo "Building Docker image for user-service..."
if docker build -t user-service ./business/user-service/user-application; then
    echo "Docker image for user-service built successfully."
else
    echo "Failed to build Docker image for user-service." >&2
    exit 1
fi

echo "Building Docker image for product-service..."
if docker build -t product-service ./business/product-service/product-application; then
    echo "Docker image for product-service built successfully."
else
    echo "Failed to build Docker image for product-service." >&2
    exit 1
fi

echo "Building Docker image for order-service..."
if docker build -t order-service ./business/order-service/order-application; then
    echo "Docker image for order-service built successfully."
else
    echo "Failed to build Docker image for order-service." >&2
    exit 1
fi

echo "Building Docker image for auth-server..."
if docker build -t auth-server ./edge/auth-server; then
    echo "Docker image for auth-server built successfully."
else
    echo "Failed to build Docker image for auth-server." >&2
    exit 1
fi

echo "Building Docker image for gateway-server..."
if docker build -t gateway-server ./edge/gateway-server; then
    echo "Docker image for gateway-server built successfully."
else
    echo "Failed to build Docker image for gateway-server." >&2
    exit 1
fi

echo "Building Docker image for notification-server..."
if docker build -t notification-server ./edge/notification-server; then
    echo "Docker image for notification-server built successfully."
else
    echo "Failed to build Docker image for notification-server." >&2
    exit 1
fi

echo "Building Docker image for job-server..."
if docker build -t job-server ./edge/job-server; then
    echo "Docker image for job-server built successfully."
else
    echo "Failed to build Docker image for job-server." >&2
    exit 1
fi
