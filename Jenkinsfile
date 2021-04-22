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

  options {
    disableConcurrentBuilds()
  }

  environment {
    PROJECT_NAME = "ROBOSHOP"
    UBUNTU_SSH_CRED = credentials('UBUNTU-SSH')
  }

  parameters {
    string(name: 'COMPONENT', defaultValue: '', description: 'Which Component?')
    text(name: 'COMMENT', defaultValue: '', description: 'Write Comment about the Job why are you running it.')
    booleanParam(name: 'FORCE_DEPLOY', defaultValue: true, description: 'Check this for Force Deployment')
    choice(name: 'ENV', choices: ['dev', 'qa', 'prod'], description: 'Pick an Environment')
    password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
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
