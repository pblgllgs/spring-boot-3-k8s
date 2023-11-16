dev_start: pack-back pack-front  build_image_dev clean_back
	@echo Local DEV environment deployed

prod_start: build_image_prod
	@echo Local PROD environment deployed

pack-back:
	@echo Generando nuevo ejecutable back
	cd ./bookmark-service && mvn clean package -DskipTests

pack-front:
	@echo Generando nuevo ejecutable back
	cd ./bookmark-frontend-service && yarn build


packt:
	@echo Generando nuevo ejecutable
	mvn clean package -DskipTests

build_image_dev:
	@echo Borrando version de imagen antigua
	docker image rm spring-boot-3-k8s-bookmark-service:latest
	docker image rm spring-boot-3-k8s-bookmark-frontend-service:latest
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.dev.yml up -d

build_image_prod:
	@echo Borrando versiones de imagenes antiguas
	docker image rm pblgllgs/bookmark-service:latest
	docker image rm pblgllgs/bookmark-frontend-service:latest
	@echo Descargando nuevas imagenes y arrancando docker-compose
	docker compose -f docker-compose.prod.yml up -d


dev_stop:
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.dev.yml down -v

prod_stop:
	@echo Creando nueva imagen y arrancando compose
	docker compose -f docker-compose.prod.yml down -v

clean_back:
	@echo limpiando...
	cd ./bookmark-service && mvn clean