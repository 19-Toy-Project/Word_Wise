FROM ubuntu:20.04

# 필수 패키지 설치
RUN apt-get update && apt-get install -y \
    ffmpeg \
    openjdk-17-jdk \
    wget \
    unzip \
    && apt-get clean

WORKDIR /app
COPY . .

# 실행 권한 추가
RUN chmod +x gradlew

# Gradle 빌드 (JAR 파일 생성)
RUN ./gradlew assemble -x test
RUN ls -al build/libs/  # 디버깅: JAR 파일 확인

# JAR 파일을 app.jar로 이동
RUN mv build/libs/wordwise-0.0.1-SNAPSHOT.jar app.jar

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]