# SETUP GUIDE - Product Catalog Service

## Prerequisites

Before running the application, make sure you have the following installed:

### 1. Java Development Kit (JDK) 17 or higher

**Check if Java is installed:**
```bash
java -version
```

**Install Java (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

**Install Java (macOS with Homebrew):**
```bash
brew install openjdk@17
```

### 2. Apache Maven 3.8+

**Check if Maven is installed:**
```bash
mvn -version
```

**Install Maven (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install maven
```

**Install Maven (macOS with Homebrew):**
```bash
brew install maven
```

**Install Maven (Manual):**
```bash
# Download Maven
wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz

# Extract
tar -xvzf apache-maven-3.9.6-bin.tar.gz

# Move to /opt
sudo mv apache-maven-3.9.6 /opt/maven

# Add to PATH (add to ~/.bashrc or ~/.zshrc)
export M2_HOME=/opt/maven
export PATH=$M2_HOME/bin:$PATH

# Reload shell
source ~/.bashrc
```

### 3. Docker and Docker Compose

**Check if Docker is installed:**
```bash
docker --version
docker-compose --version
```

**Install Docker (Ubuntu):**
```bash
# Add Docker's official GPG key
sudo apt-get update
sudo apt-get install ca-certificates curl gnupg
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

# Add repository
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install Docker
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

# Start Docker
sudo systemctl start docker
sudo systemctl enable docker

# Add user to docker group (to run without sudo)
sudo usermod -aG docker $USER
newgrp docker
```

**Install Docker (macOS):**
Download and install Docker Desktop from https://www.docker.com/products/docker-desktop

## Step-by-Step Setup

### Step 1: Clone or Navigate to Project

```bash
cd /home/nguyendat/Documents/workspace/Ecommerce-microservice/product-service
```

### Step 2: Start Infrastructure Services

```bash
# Start all services
docker-compose up -d

# Check service status
docker-compose ps

# View logs
docker-compose logs -f
```

**Services Started:**
- PostgreSQL on port 5433
- Elasticsearch on port 9200
- Kibana on port 5601
- MinIO on ports 9000 (API) and 9001 (Console)

**Wait 30-60 seconds** for all services to be ready.

### Step 3: Verify Services

```bash
# PostgreSQL
docker exec -it postgres-product pg_isready

# Elasticsearch
curl http://localhost:9200/_cluster/health

# MinIO
curl http://localhost:9000/minio/health/live

# Check all containers
docker ps
```

### Step 4: Configure MinIO (First Time Only)

**Option A: Automatic (already configured in docker-compose.yml)**
The bucket is created automatically by the minio-mc service.

**Option B: Manual (if needed)**
1. Open http://localhost:9001
2. Login: `minioadmin` / `minioadmin`
3. Create bucket: `ecommerce-products`
4. Set bucket access policy to **Public**

### Step 5: Build the Application

```bash
# Clean and build
mvn clean install -DskipTests

# Or compile only
mvn clean compile
```

**Expected output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```

### Step 6: Run the Application

```bash
# Run with Maven
mvn spring-boot:run

# Or run the JAR file
java -jar target/product-service-1.0.0.jar
```

**Expected output:**
```
Started ProductServiceApplication in X.XXX seconds
```

### Step 7: Verify Application

```bash
# Health check
curl http://localhost:8082/actuator/health

# Expected response:
# {"status":"UP"}

# Get categories
curl http://localhost:8082/api/categories

# Get products
curl http://localhost:8082/api/products
```

## Configuration

### Database Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/productdb
    username: product_user
    password: product_pass
```

### Elasticsearch Configuration

```yaml
spring:
  elasticsearch:
    uris: http://localhost:9200
```

### MinIO Configuration

```yaml
minio:
  url: http://localhost:9000
  access-key: minioadmin
  secret-key: minioadmin
  bucket-name: ecommerce-products
```

### Keycloak Configuration (Optional)

If you have Keycloak running on port 8180:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8180/realms/ecommerce
          jwk-set-uri: http://localhost:8180/realms/ecommerce/protocol/openid-connect/certs
```

**Note:** Without Keycloak, the application will still run but authentication will fail. You can disable security for testing by modifying `SecurityConfig.java`.

## Troubleshooting

### Port Already in Use

```bash
# Check what's using the port
sudo lsof -i :8082
sudo lsof -i :5433
sudo lsof -i :9200

# Kill the process
sudo kill -9 <PID>

# Or change the port in application.yml
server:
  port: 8083  # Use different port
```

### Docker Services Not Starting

```bash
# Stop all services
docker-compose down

# Remove volumes (WARNING: This deletes all data)
docker-compose down -v

# Start again
docker-compose up -d
```

### Maven Build Fails

```bash
# Clear Maven cache
rm -rf ~/.m2/repository

# Build again
mvn clean install -U
```

### Database Connection Error

```bash
# Check if PostgreSQL is running
docker ps | grep postgres-product

# Check logs
docker logs postgres-product

# Restart PostgreSQL
docker-compose restart postgres-product
```

### Elasticsearch Connection Error

```bash
# Check Elasticsearch status
curl http://localhost:9200

# View logs
docker logs elasticsearch-product

# Increase memory if needed (edit docker-compose.yml)
environment:
  - "ES_JAVA_OPTS=-Xms1g -Xmx1g"
```

## Running in Production

### 1. Build JAR

```bash
mvn clean package -DskipTests
```

### 2. Run with Production Profile

```bash
java -jar target/product-service-1.0.0.jar --spring.profiles.active=prod
```

### 3. Environment Variables

```bash
export DB_URL=jdbc:postgresql://prod-db:5432/productdb
export DB_USERNAME=prod_user
export DB_PASSWORD=secure_password
export ELASTICSEARCH_URI=http://prod-es:9200
export MINIO_URL=http://prod-minio:9000
export MINIO_ACCESS_KEY=prod_access_key
export MINIO_SECRET_KEY=secure_secret_key

java -jar target/product-service-1.0.0.jar
```

## Development Tips

### Hot Reload with Spring Boot DevTools

Add to pom.xml (already included):
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

### Debug Mode

```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

Then attach your IDE debugger to port 5005.

### View Logs

```bash
# Application logs
tail -f logs/application.log

# Docker logs
docker-compose logs -f product-service
```

## Next Steps

1. ✅ Setup complete - Application running on http://localhost:8082
2. Test APIs using the examples in `API-CONTRACT.md`
3. Import sample data or create your own products
4. Integrate with frontend application
5. Setup Keycloak for authentication
6. Configure production environment

## Support

If you encounter any issues:
1. Check the troubleshooting section above
2. Review application logs: `logs/application.log`
3. Check Docker container logs: `docker-compose logs`
4. Verify all services are running: `docker-compose ps`

---

**Last Updated:** February 22, 2026

