//pipeline {
//
//  agent {
//    node {
//      label 'agent1'
//    }
//  }
//
//  stages {
//
//    stage('Hello') {
//      steps {
//        echo "Hello"
//      }
//    }
//
//  }
//
//  post {
//    always {
//      echo "Post Action"
//    }
//  }
//
//}
//

// ENVIRONMENT
pipeline {
  agent any

  environment {
    PROJECT_NAME = "ROBOSHOP"
    UBUNTU_SSH_CRED = credentials('UBUNTU-SSH')
  }

  stages {

    stage('One') {
      steps {
        sh "echo ${PROJECT_NAME}"
        sh "env"
      }
    }

    stage('Two') {

      environment {
        PROJECT_NAME = "TODO"
      }

      steps {
        sh "echo ${PROJECT_NAME}"
      }
    }

  }
}
