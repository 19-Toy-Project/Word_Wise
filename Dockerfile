FROM ubuntu:20.04

RUN apt-get update && apt-get install -y \
    ffmpeg \
    openjdk-17-jdk \
    && apt-get clean

WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]