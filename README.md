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

### Create cluster with kind

```bash
./create-cluster.sh
kubectl apply -f ./k8s
```

### Create deploy

```bash
kubectl apply -f ./k8s
```

### Delete deploy

```bash
kubectl delete -f ./k8s
```

## Delete cluster

```bash
./delete-cluster.sh
```

### Expose FRONTEND

```bash
k port-forward svc/bookmark-frontend-service -n default 30080:3000
```

### Expose BACKEND

```bash
k port-forward svc/bookmark-backend-service -n default 30090:8080
```

## Endpoints 

add in host files this "k8s-cluster.com" this way every request will be redirected to localhost

```bash
127.0.0.1 k8s-cluster.com
```

### Frontend

```bash
http://k8s-cluster.com
```

### Backend


```bash
http://localhost/bookmark-backend-service/api/v1/bookmarks
```

