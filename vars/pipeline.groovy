def call() {
  pipeline {
    agent any

    stages {

      stage('Prepare Artifacts') {
        steps {
          sh '''
          cd static
          zip -r ../frontend.zip *
        '''
        }
      }

      stage('Upload Artifacts') {
        steps {
          sh '''
          curl -f -v -u admin:admin123 --upload-file frontend.zip http://172.31.14.124:8081/repository/frontend/frontend.zip
        '''
        }
      }

    }

  }
}