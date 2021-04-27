def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP               : '172.31.14.124',
  ]
  args << params

  // End Default + Required Arguments
  pipeline {
    agent {
      label "${args.SLAVE_LABEL}"
    }

    environment {
      COMPONENT     = "${args.COMPONENT}"
      NEXUS_IP      = "${args.NEXUS_IP}"
      PROJECT_NAME  = "${args.PROJECT_NAME}"
      SLAVE_LABEL   = "${args.SLAVE_LABEL}"
    }

    stages {

      stage('Prepare Artifacts') {
        steps {
          sh '''
          echo ${COMPONENT}
          cd static
          zip -r ../frontend.zip *
        '''
        }
      }

      stage('Upload Artifacts') {
        steps {
          sh '''
          curl -f -v -u admin:admin123 --upload-file frontend.zip http://${NEXUS_IP}:8081/repository/frontend/frontend.zip
        '''
        }
      }

    }

  }
}