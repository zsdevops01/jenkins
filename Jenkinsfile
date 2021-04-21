pipeline {

  agent {
    node {
      label 'agent1'
    }
  }

  stages {

    stage('Hello') {
      steps {
        echo "Hello"
      }
    }

  }

  post {
    always {
      echo "Post Action"
    }
  }

}

