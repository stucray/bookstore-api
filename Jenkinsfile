pipeline {
  agent any
  tools {
      maven 'maven-3.6.3'
  }
  stages {
    stage("Build") {
        steps {
          echo 'Building and installing Maven artifact.'
          sh './mvnw clean install'
      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
