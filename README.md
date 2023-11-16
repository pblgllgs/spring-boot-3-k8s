# SPRING BOOT KUBERNETES

## FRONTEND NEXTJS

```URL
https://github.com/pblgllgs/bookmark-frontend-service
```

## TestContainers

Run test in postgresql db

```bash
mvn verify
```

## Create secrets in github

DOCKER_USERNAME

DOCKER_PASSWORD -> TOKEN generated in docker hub on section security

## Maven need permissions in github actions

execute and push changes

```bash
cd bookmark-service
git update-index --chmod=+x mvnw
```

## Build image with jib

```bash
./mvnw clean package jib:dockerBuild -DskipTests
```

## Build with docker in local

```bash
docker build -t pblgllgs/bookmark-service:latest
```

## Makefile

```makefile
dev: pack build_image_dev
	@echo Local DEV environment deployed

prod: build_image_prod
	@echo Local PROD environment deployed
pack:
	@echo Generando nuevo ejecutable
	mvn clean package -DskipTests

packt:
	@echo Generando nuevo ejecutable
	mvn clean package -DskipTests

build_image_dev:
	@echo Borrando version de imagen antigua
	docker image rm spring-boot-3-k8s-bookmark-service:latest
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.dev.yml up -d

build_image_prod:
	@echo Borrando version de imagen antigua
	docker image rm pblgllgs/bookmark-service:latest
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.prod.yml up -d

down_dev:
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.dev.yml down -v

down_prod:
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.prod.yml down -v
```

## K8S

### Expose FRONTEND

```bash
k port-forward svc/frontend-service -n default 32001:3000
```

### Expose BACKEND

```bash
k port-forward svc/backend-service -n default 32002:8080
```