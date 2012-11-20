#! /bin/bash
mvn -Dmaven.test.skip=true clean install && \
scp /home/formation03/workspace/indicators/indicators-web-springmvc-servlet/target/indicators.war grouper@tournesol:/opt/webapps/indicators
