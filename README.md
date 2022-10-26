# Deploy procedure

1. Build and run tests `./gradlew build`
2. Packaged docker images `./gradlew dockerBuild`
3. Startup cloud `docker compose up`
4. servers will be available at:
    - [echo1](http://127.0.0.1:8081/dbTime)
    - [echo2](http://127.0.0.1:8082/dbTime)