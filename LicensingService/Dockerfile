FROM openjdk:22-jdk-slim as build

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM openjdk:22-jdk-slim

ARG USER=myuser
ARG USER_GROUP=mygroup

WORKDIR ./app

RUN addgroup --system ${USER_GROUP} && adduser --system --ingroup ${USER_GROUP} ${USER}

COPY --from=build app/build/libs/*.jar ./app.jar

RUN chown -R ${USER}:${USER_GROUP} /app

USER ${USER}

ENTRYPOINT ["java", "-jar", "app.jar"]