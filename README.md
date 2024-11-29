# App Monitoring Example
An example project demonstrating how to collect metrics from a Java application using Prometheus, aggregate logs using Promtail and Loki, and visualize both using Grafana.

## Features
- Spring Boot Backend: Exposes metrics and generates logs.
- Prometheus: Collects application metrics.
- Loki: Aggregates logs from the application.
- Promtail: Ships logs to Loki.
- Grafana: Visualizes metrics and logs with pre-configured dashboards and data sources.
- Dockerized Deployment: Easily run all components using Docker Compose.

## Architecture Overview
The project includes:

1. **Backend Application:**
- A simple Spring Boot application with:
  - Exposed metrics at /actuator/prometheus (on port 8081).
  - Rolling log files stored in /app/logs.
- Dockerized using a multi-stage Dockerfile.

2. **Prometheus:**
- Scrapes metrics from the backend application's /actuator/prometheus endpoint.
- Configured to fetch metrics every 15 seconds.

3. **Loki:**
- Stores aggregated logs from the backend application.
- Configured for local storage.

4. **Promtail:**
- Monitors application logs from /app/logs.
- Pushes logs to Loki.

5. **Grafana:**
- Pre-configured data sources for Prometheus (metrics) and Loki (logs).
- Includes a ready-to-use JVM Metrics Dashboard.

## Prerequisites
Docker and Docker Compose installed.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/cmaktas/app-monitoring-example.git
cd app-monitoring-example
```

### 2. Start the Services
Build and start all containers using Docker Compose:
```bash
docker-compose up --build -d
```

### 3. Access the Services
- Backend Application:
  - Metrics Endpoint: http://localhost:8081/actuator/prometheus
- Grafana:
  - URL: http://localhost:3000
  - Default Credentials: admin / admin
- Prometheus: http://localhost:9090

## Grafana Configuration
### Pre-configured Dashboards
- JVM Metrics: Provides an overview of JVM metrics like memory usage, garbage collection, and thread counts.
### Pre-configured Data Sources:
- Loki: Logs collected by Promtail.
- Prometheus: Metrics collected from the backend application.

## How It Works

**Backend Application:**
Generates logs in /app/logs and exposes metrics at /actuator/prometheus.

**Prometheus:**
Scrapes metrics from the backend and stores them.

**Promtail:**
Monitors /app/logs and forwards logs to Loki.

**Loki:**
Aggregates logs from Promtail for analysis.

**Grafana:**
Visualizes both metrics (from Prometheus) and logs (from Loki).


## Stop Services
To stop all services, run:
```bash
docker-compose down
```