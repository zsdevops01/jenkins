
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

    triggers {
      pollSCM('* * * * 1-5')
    }

    environment {
      COMPONENT     = "${args.COMPONENT}"
      NEXUS_IP      = "${args.NEXUS_IP}"
      PROJECT_NAME  = "${args.PROJECT_NAME}"
      SLAVE_LABEL   = "${args.SLAVE_LABEL}"
      APP_TYPE      = "${args.APP_TYPE}"
    }

    stages {

      stage('Build Code & Install Dependencies') {
        steps {
          sh 'env'
          script {
            build = new nexus()
            build.code_build("${APP_TYPE}", "${COMPONENT}")
          }
        }
      }


      stage('Prepare Artifacts') {
        steps {
          script {
            prepare = new nexus()
            prepare.make_artifacts("${APP_TYPE}", "${COMPONENT}")
          }
        }
      }

      stage('Upload Artifacts') {
        steps {
          script {
            prepare = new nexus()
            prepare.nexus(COMPONENT)
          }
        }
      }

      stage('Deploy to Dev Env') {
        steps {
          script {
            def values = "${GIT_BRANCH}".split('/')
            print values
          }
          //build job: 'Deployment Pipeline', parameters: [string(name: 'ENV', value: 'dev'), string(name: 'COMPONENT', value: "${COMPONENT}"), string(name: 'VERSION', value: '0.0.0')]
        }
      }

    }

  }
}