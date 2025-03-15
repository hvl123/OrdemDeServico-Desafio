#!/bin/bash
./mvnw clean package
docker build -t ordem-servico .