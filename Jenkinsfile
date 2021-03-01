pipeline {
  agent any

  environment {
        GIT_COMMIT_SHORT = sh(
                script: "printf \$(git rev-parse --short ${GIT_COMMIT})",
                returnStdout: true
        )

        MVN_ARTIFACT_ID = readMavenPom().getArtifactId()
        MVN_VERSION = readMavenPom().getVersion()
   }

  //Package the application without running tests.
  stages {
    stage("Build") {
            steps {
          echo 'Building and packaging Maven artifact.'
          echo "artifact = ${MVN_ARTIFACT_ID} version = ${MVN_VERSION}"
          echo "Git commit = ${GIT_COMMIT_SHORT}"
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
    stage("Build Docker Image") {
    
        steps {
            echo 'Building Docker image'
            sh 'mvn spring-boot:build-image -DskipTests=true'
        }
    }

    //Deploy image to internal docker registry.
    stage("Deploy Docker Image") {
      steps {
        echo 'Deploying docker image to internal registry'

      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
