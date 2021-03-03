pipeline {
  agent any
  environment {
      RELEASE = '0.0.1'
  }
         stage("INFO") {
             steps {
                echo "branch is ${GIT_BRANCH}"
             }
         }
         stage("build") {
            environment {
                LOG_LEVEL = 'INFO'
            }
            steps {
                bat "mvn clean test -Drun.testng.xml=basePageTest.xml"
            }
            always {
                post {
                    success {
                        echo "Build success"
                    }
                    failure {
                        echo "Build failure"
                    }
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