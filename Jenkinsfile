pipeline {
  agent any
  tools {
      maven 'maven-3.6.3'
  }
  stage("Build") {
      echo 'Building and installing Maven artifact.'
    sh './mvnw clean install'
  }

  post {
    always {
      cleanWs()
    }
  }
}
