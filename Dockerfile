FROM ubuntu:focal
RUN apt-get update && apt-get install -y ffmpeg telnet openjdk-17-jdk && rm -rf /var/lib/apt/lists/*
WORKDIR /app

# Gradle 설치
RUN curl -s "https://get.sdkman.io" | bash \
    && source "$HOME/.sdkman/bin/sdkman-init.sh" \
    && sdk install gradle 8.2

COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]