pipeline {
  environment {
      JAVA_TOOL_OPTIONS = "-Duser.home=/var/maven"
  }
  //Docker agent with a volume for the .m2 directory so that artifacts are 
  //cached.
//   agent {
//       docker {
//           image "maven:3.6.3-openjdk-15"
//           args "-v /tmp/maven:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2"
//       }
//   }
  agent any
  
  //Package the application without running tests.
  stages {
    stage("Build") {
            steps {
          echo 'Building and packaging Maven artifact.'
          sh 'mvn clean package -DskipTests=true'
      }
    }

    //Run unit tests.
    stage("Unit Tests") {
        steps {
            echo 'Running tests'
            sh 'mvn test'
        }
    }

    //Deploy artifact to maven repository
    stage("Deploy Maven Artifact") {
        steps {
            echo 'Deploying to Maven repository'
            sh 'mvn deploy -DskipTests=true'
        }
    }
    
    //Build Docker image
    stage("Docker Image") {
    
        steps {
            echo 'Building Docker image'
            sh 'mvn spring-boot:build-image -DskipTests=true'
        }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
