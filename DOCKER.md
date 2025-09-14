# Docker Setup for Food Order Application

This document explains how to build and run the Food Order Application using Docker.

## Prerequisites

- Docker installed on your system
- Docker Compose (optional, for easier management)

## Quick Start

### 1. Build the Docker Image

```bash
# Using the build script (recommended)
./build-docker.sh

# Or manually
docker build -t food-order-app:latest .
```

### 2. Run the Container

```bash
# Run with docker
docker run -p 8080:8080 food-order-app:latest

# Or using docker-compose
docker-compose up
```

### 3. Access the Application

- **Main Application**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health
- **Application Info**: http://localhost:8080/actuator/info

## Docker Configuration

### Multi-Stage Build

The Dockerfile uses a multi-stage build approach:

1. **Build Stage**: Uses Maven with OpenJDK 17 to compile the application
2. **Runtime Stage**: Uses a minimal OpenJDK 17 JRE image for running the application

### Security Features

- **Non-root User**: The application runs as a non-root user (`appuser`)
- **Minimal Base Image**: Uses `openjdk:17-jre-slim` for smaller attack surface
- **Health Checks**: Built-in health monitoring

### Environment Configuration

The application uses different configurations for Docker:

- **Development**: `application.properties` (with H2 console enabled)
- **Docker**: `application-docker.properties` (optimized for containerized deployment)

## Docker Commands

### Build Commands

```bash
# Build the image
docker build -t food-order-app:latest .

# Build with specific tag
docker build -t food-order-app:v1.0.0 .

# Build without cache
docker build --no-cache -t food-order-app:latest .
```

### Run Commands

```bash
# Run in foreground
docker run -p 8080:8080 food-order-app:latest

# Run in background
docker run -d -p 8080:8080 --name food-order-app food-order-app:latest

# Run with environment variables
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=docker food-order-app:latest

# Run with custom port
docker run -p 9090:8080 food-order-app:latest
```

### Management Commands

```bash
# List running containers
docker ps

# View container logs
docker logs food-order-app

# Stop the container
docker stop food-order-app

# Remove the container
docker rm food-order-app

# Remove the image
docker rmi food-order-app:latest
```

## Docker Compose

The `docker-compose.yml` file provides an easy way to manage the application:

```bash
# Start the application
docker-compose up

# Start in background
docker-compose up -d

# Stop the application
docker-compose down

# View logs
docker-compose logs

# Rebuild and start
docker-compose up --build
```

## Health Monitoring

The application includes health checks:

- **Health Endpoint**: `/actuator/health`
- **Info Endpoint**: `/actuator/info`
- **Docker Health Check**: Configured to check every 30 seconds

## Troubleshooting

### Common Issues

1. **Port Already in Use**
   ```bash
   # Check what's using port 8080
   lsof -i :8080
   
   # Use a different port
   docker run -p 9090:8080 food-order-app:latest
   ```

2. **Build Failures**
   ```bash
   # Clean build
   docker build --no-cache -t food-order-app:latest .
   
   # Check Docker daemon
   docker info
   ```

3. **Application Won't Start**
   ```bash
   # Check logs
   docker logs <container-name>
   
   # Check health
   curl http://localhost:8080/actuator/health
   ```

### Debug Mode

To run the application in debug mode:

```bash
docker run -p 8080:8080 -p 5005:5005 \
  -e JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" \
  food-order-app:latest
```

## Production Considerations

For production deployment:

1. **Use specific image tags** instead of `latest`
2. **Configure resource limits**:
   ```yaml
   deploy:
     resources:
       limits:
         memory: 512M
         cpus: '0.5'
   ```
3. **Use external database** instead of H2
4. **Configure logging** for production
5. **Set up monitoring** and alerting

## Image Size Optimization

The current image size can be further optimized by:

- Using Alpine-based images
- Multi-architecture builds
- Distroless images for runtime

Example optimized Dockerfile:

```dockerfile
FROM maven:3.9.6-openjdk-17-alpine AS build
# ... build steps ...

FROM gcr.io/distroless/java17-debian11
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```
