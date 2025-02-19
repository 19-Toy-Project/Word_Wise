# 1. Base image
FROM ubuntu:focal

# 2. 필수 패키지 설치 (Gradle 빌드 포함)
RUN apt-get update && apt-get install -y \
    ffmpeg telnet openjdk-17-jdk curl unzip \
    && rm -rf /var/lib/apt/lists/*

# 3. 작업 디렉토리 설정
WORKDIR /app

# 4. 프로젝트 전체 복사 (Gradle 실행을 위해 필요)
COPY . .

# 5. Gradle 설치
RUN curl -s "https://get.sdkman.io" | bash \
    && source "$HOME/.sdkman/bin/sdkman-init.sh" \
    && sdk install gradle 8.2

# 6. Gradle 빌드 실행 (테스트 제외)
RUN gradle clean build -x test

# 7. 빌드된 JAR 파일 확인
RUN ls -lah build/libs/

# 8. JAR 파일을 실행하도록 ENTRYPOINT 설정
CMD ["sh", "-c", "java -jar build/libs/*.jar"]
