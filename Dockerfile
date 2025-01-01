# 1단계: 빌드 단계
FROM gradle:7.6-jdk17 AS builder

# 애플리케이션 소스 코드를 작업 디렉토리로 복사
WORKDIR /home/gradle/project

# Gradle 캐시를 활용하기 위해 먼저 종속성 파일을 복사
COPY build.gradle settings.gradle ./
COPY src ./src

# 애플리케이션 빌드
RUN gradle clean build --no-daemon

# 2단계: 실행 단계
FROM openjdk:17-jdk-slim

# 애플리케이션 JAR 파일을 복사
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# 애플리케이션이 사용하는 포트 열기
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app.jar"]