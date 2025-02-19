FROM ubuntu:focal

# 필수 패키지 설치
RUN apt-get update && apt-get install -y ffmpeg telnet openjdk-17-jdk curl unzip && rm -rf /var/lib/apt/lists/*

# Gradle 수동 설치 (Gradle 8.2 버전)
RUN curl -LO https://services.gradle.org/distributions/gradle-8.2-bin.zip \
    && unzip gradle-8.2-bin.zip -d /opt/ \
    && rm gradle-8.2-bin.zip
ENV PATH="/opt/gradle-8.2/bin:$PATH"

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 복사 및 빌드 실행
COPY . .
RUN gradle clean build -x test  # 테스트 제외하고 빌드

# 실행
ENTRYPOINT ["java", "-Xmx512m", "-jar", "build/libs/*.jar"]
