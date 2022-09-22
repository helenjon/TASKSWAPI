pipeline {
    agent any

    tools {
            maven "3.6.3"
            jdk 'Corretto JDK 11.0.13'
    }

    stages {
        stage("Build") {
            steps {
            sh "mvn -version"
            sh "mvn clean compile"
            }
        }
    }

    post {
        always {
        cleanWs()
        }
    }
}
