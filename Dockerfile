FROM ubuntu:20.04

RUN apt-get update && apt-get install -y \
    ffmpeg \
    openjdk-17-jdk \
    && apt-get clean

WORKDIR /app
COPY . .

# 실행 권한 추가
RUN chmod +x gradlew

# Gradle 빌드 (JAR 파일 생성)
RUN ./gradlew assemble -x test
RUN find build/libs -type f  # JAR 파일 확인 (디버깅용)

# JAR 파일 복사
RUN ls -al build/libs/  # JAR 존재 여부 확인
COPY build/libs/*.jar app.jar

# JAR 복사 및 실행
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]