def nexus() {
  command = "curl -f -v -u admin:admin123 --upload-file cart.zip http://172.31.14.124:8081/repository/cart/cart1.zip"
  def execute_state=sh(returnStdout: true, script: command)
}


