# Deploy procedure

1. Build and run tests `./gradlew build`
2. Packaged docker images `./gradlew dockerBuild`
3. Startup cloud `docker compose up`
4. Servers will be available at:
    - [nginx](http://127.0.0.1:8080)
    - [echo1](http://127.0.0.1:8081/dbTime)
    - [echo2](http://127.0.0.1:8082/dbTime)
    - [restaurant](http://127.0.0.1:8091/restaurant)
5. If you want to run only mysql run `docker-compose up -d mysql`