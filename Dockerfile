FROM ubuntu:focal
RUN apt-get update && apt-get install -y ffmpeg telnet openjdk-17-jdk && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]