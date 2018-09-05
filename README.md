# Experian service - RKI Credit checks for individual and companies

## Configuration
Copy .env.dist to .env and fill in your credentials

## Development
set -a && source .env && ./gradlew bootRun

## Launch
docker-compose up

## Run tests
./gradlew test
