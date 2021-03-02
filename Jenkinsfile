pipeline {
  agent any
    stages {
      stage("build") {
      steps {
      echo 'building'
      }
      stage("print branch") {
      steps {
      echo "branch is ${GIT_BRANCH}"
       }
      }
    }
  }
}