check-deps:
	./gradlew dependencyUpdates -Drevision=release

dev:
	./gradlew run

setup:
	gradle wrapper --gradle-version 8.3

clean:
	./gradlew clean

build:
	make clean
	make lint
	make test

start: dev

install:
	./gradlew installDist

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

test:
	./gradlew test

image-build:
	docker build -t hexletcomponents/java-javalin-example:latest .

image-push:
	docker push hexletcomponents/java-javalin-example:latest

report:
	./gradlew jacocoTestReport

.PHONY: build