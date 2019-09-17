#!/bin/bash -ex

SONAR_SCANNER_VERSION='4.0.0.1744'
SONAR_SCANNER_URL="https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-${SONAR_SCANNER_VERSION}-linux.zip"
SONAR_SCANNER_FILE_NAME=$(basename ${SONAR_SCANNER_URL})
SONAR_SCANNER_DIR="${HOME}/sonar-scanner-${SONAR_SCANNER_VERSION}-linux"
SONAR_PROJECT_KEY='circle-ci-test'
SONAR_HOST_URL='http://ec2-54-248-68-225.ap-northeast-1.compute.amazonaws.com:9000'
SRC_DIR='./app/src/main/java'

wget -P ${HOME} -N ${SONAR_SCANNER_URL}
unzip -d ${HOME} ${HOME}/${SONAR_SCANNER_FILE_NAME}

${SONAR_SCANNER_DIR}/bin/sonar-scanner \
  -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
  -Dsonar.sources=${SRC_DIR} \
  -Dsonar.host.url=${SONAR_HOST_URL} \
  -Dsonar.login=${SONAR_TOKEN}
