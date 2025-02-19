FROM ubuntu:20.04

RUN apt-get update && apt-get install -y \
    ffmpeg \
    openjdk-17-jdk \
    && apt-get clean

WORKDIR /app
COPY . .

# 실행 권한 추가
RUN chmod +x gradlew

# Gradle 빌드 실행
RUN ./gradlew build --no-daemon

# JAR 복사 및 실행
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]