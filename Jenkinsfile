pipeline {
  agent any
  environment {
      RELEASE = '0.0.1'
  }



    stages {
    stage ('Initialize') {
                  steps {
                      sh "mvn -version"
              }
                  }

         stage("build") {
         environment {
                 LOG_LEVEL = 'INFO'
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
          stage("deploy") {
              input {
                  message 'Deploy to AWS?'
                  ok 'deploy!!!'
                  parameters {
                      string(name: 'Target environment', defaultValue: 'PROD', description: 'Deploying to AWS')
                  }
              }
              steps {
                  echo "Deploying release ${RELEASE}"
              }
          }
  }
}