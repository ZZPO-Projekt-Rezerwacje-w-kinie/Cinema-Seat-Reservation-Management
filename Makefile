prepare:
	docker-compose up -d
	bash wait.sh
run:
	cd cinema-reservation-app/ && \
    mvn clean install -DskipTests && \
    docker build -t cinema-reservation . && \
    docker run --rm -p 7777:7777 --network=cinema-seat-reservation-management_system --name app cinema-reservation
clean:
	docker ps -a | grep app && docker stop app && docker rm app || true
	cd cinema-reservation-app/ && \
	docker-compose down && \
    mvn clean
migrate:
	cd cinema-reservation-app && \
 	mvn spring-boot:run -Dconsole=true -Dspring-boot.run.profiles=console-application