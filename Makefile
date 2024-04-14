run:
	cd cinema-reservation-app/ && \
    mvn clean install && \
    docker build -t cinema-reservation . && \
    docker run -p 7777:7777 cinema-reservation --name app
clean:
	cd cinema-reservation-app/ && \
    mvn clean