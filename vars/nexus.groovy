def nexus() {
  command = "curl -f -v -u admin:admin123 --upload-file cart.zip http://172.31.14.124:8081/repository/cart/cart1.zip"
  def execute_state=sh(returnStdout: true, script: command)
}

def make_artifacts(APP_TYPE, COMPONENT) {
  get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}'"
  def get_branch_exec=sh(returnStdout: true, script: get_branch)
  if(APP_TYPE == "NGINX") {
    command = "cd static && zip -r ../${COMPONENT}.zip *"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "NODEJS") {
    command = "echo ${get_branch_exec} ; zip -r ${COMPONENT}.zip node_modules server.js"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "JAVA") {
    command = "cp target/*.jar ${COMPONENT}.jar && zip -r ${COMPONENT}.zip ${COMPONENT}.jar"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "PYTHON") {
    command = "zip -r ${COMPONENT}.zip payment.ini payment.py rabbitmq.py requirements.txt"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  }
}

def code_build(APP_TYPE, COMPONENT) {
  if(APP_TYPE == "NODEJS") {
    command = "npm install"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "JAVA") {
    command = "mvn clean package"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  }
}
