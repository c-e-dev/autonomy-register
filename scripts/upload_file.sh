#!/bin/bash
## Upload build file to registry
## Only exists tag from main-branch

TOKEN=`echo $TOKEN_REGISTRY`
PROJECT_ID="67038642"
PACKAGE_NAME="autonomy-register"
PACKAGE_VERSION=`git fetch --tags && git tag --list | sort -V | tail -n1`
FILE="web-1.0-SNAPSHOT.jar"

curl --location --header  "PRIVATE-TOKEN: $TOKEN" \
             --upload-file "../integration_build/$FILE" \
             "https://gitlab.com/api/v4/projects/$PROJECT_ID/packages/generic/$PACKAGE_NAME/$PACKAGE_VERSION/$FILE"
echo "Uploaded: $FILE"

exit 0