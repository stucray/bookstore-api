pipeline {

  agent any

  environment {

  	    DOCKER_REGISTRY = 'euler:5000'
        GIT_COMMIT_SHORT = sh(
                script: "printf \$(git rev-parse --short ${GIT_COMMIT})",
                returnStdout: true
        )

        MVN_GROUP = readMavenPom().getGroupId()
        MVN_ARTIFACT = readMavenPom().getArtifactId()
        MVN_VERSION = readMavenPom().getVersion()

        DOCKER_IMAGE = "${MVN_ARTIFACT}:${MVN_VERSION}"
        DOCKER_TAG = "${DOCKER_REGISTRY}/${DOCKER_IMAGE}-${GIT_BRANCH}-${GIT_COMMIT_SHORT}"
   }

  //Package the application without running tests.
  stages {
    stage("Build") {
            steps {
           sh 'mvn clean package -DskipTests=true'
      }
    }

    //Run unit tests.
    stage("Unit Tests") {
        steps {
            sh 'mvn test'
        }
    }



    //Deploy artifact to maven repository
    stage("Deploy Artifact") {
        steps {
            sh 'mvn deploy -DskipTests=true'
        }
    }
    
    //Build Docker image
    stage("Build Image") {
    
        steps {
            sh 'mvn spring-boot:build-image -DskipTests=true'
        }
    }

    //Deploy image to internal docker registry.
    stage("Deploy Image") {
      steps {
        echo "Tagging and pushing image: ${DOCKER_IMAGE} as ${DOCKER_TAG}"
        sh "docker image tag ${DOCKER_IMAGE} ${DOCKER_TAG}"
        sh "docker push ${DOCKER_TAG}"
      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
