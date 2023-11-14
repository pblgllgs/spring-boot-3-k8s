# SPRING BOOT KUBERNETES

## TestContainers

Run test in postgresql db

```bash
mvn verify
```

## Create secrets in github

DOCKER_USERNAME

DOCKER_PASSWORD -> TOKEN generated in docker hub on section security

## JIB-MAVEN-PLUGIN

```XML
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>3.3.2</version>
    <configuration>
        <from>
            <image>
                eclipse-temurin:17-jre-alpine@sha256:da8bbb33e7e61396625b2e47dee1e6f6c164a0321ed4a80b2054a9a398057570
            </image>
            <platforms>
                <platform>
                    <architecture>amd64</architecture>
                    <os>linux</os>
                </platform>
            </platforms>
        </from>
        <to>
            <image>docker.io/${docker.username}/${project.artifactId}:${project.version}</image>
            <tags>
                <tag>latest</tag>
            </tags>
        </to>
    </configuration>
</plugin>
```

## Maven need permissions in github actions

execute and push changes

```bash
cd bookmark-service
git update-index --chmod=+x mvnw
```

## Build image with jib

```bash
./mvnw clean package jib:dockerBuild -DskipTests
```

## Build with docker in local

```bash
docker build -t pblgllgs/bookmark-service:latest
```

## UPDATE FIELDS BEFORE COMMIT NEW CHANGES

In Dockerfile: APPLICATION

In pom.xml: version

In build.yml: env.VERSION: