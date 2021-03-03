pipeline {
  agent any
  environment {
      RELEASE = 0.0.1
  }
    stages {
    environment {
        LOG_LEVEL = INFO
    }
         stage("build") {
            steps {
            echo 'building'
            }
            parallel {
                stage("Linux arm-64") {
                    steps {
                        echo "Build stage: ${STAGE_NAME} for release ${RELEASE} with log level ${LOG_LEVEL}"
                    }
                }
                stage("Linux amd-64") {
                    steps {
                       echo "Build stage: ${STAGE_NAME} for release ${RELEASE} with log level ${LOG_LEVEL}"
                    }
                }
            }

         }
         stage("print branch") {
            steps {
            echo "branch is ${GIT_BRANCH}"
            }
            post {
            success {
              echo "success"
            }
         }
      }
  }
}