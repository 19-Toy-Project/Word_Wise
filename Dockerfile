# 1. 베이스 이미지 설정
FROM ubuntu:focal

# 2. 필수 패키지 설치
RUN apt-get update && apt-get install -y \
    ffmpeg telnet openjdk-17-jdk curl unzip \
    && rm -rf /var/lib/apt/lists/*

# 3. 작업 디렉토리 설정
WORKDIR /app

# 4. Gradle 설치
RUN curl -s "https://get.sdkman.io" | bash \
    && source "$HOME/.sdkman/bin/sdkman-init.sh" \
    && sdk install gradle 8.2

# 5. 프로젝트 복사
COPY . .

# 6. Gradle을 사용하여 JAR 빌드
RUN gradle clean build -x test  # 테스트 제외하고 빌드

# 7. 애플리케이션 실행
ENTRYPOINT ["java", "-Xmx512m", "-jar", "build/libs/*.jar"]
