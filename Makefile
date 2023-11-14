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
	@echo limpiando...
	mvn clean

build_image_prod:
	@echo Borrando version de imagen antigua
	docker image rm pblgllgs/bookmark-service:latest
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.prod.yml up -d
	@echo limpiando...
	mvn clean

down_dev:
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.dev.yml down -v

down_prod:
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.prod.yml down -v