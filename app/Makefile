.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist-json:
	@./build/install/app/bin/app file1.json file2.json

run-dist-yml:
	@./build/install/app/bin/app file1.yml file2.yml

run-dist-yml-plain:
	@./build/install/app/bin/app -f plain file3.yml file4.yml

run-dist-help:
	@./build/install/app/bin/app -h

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build