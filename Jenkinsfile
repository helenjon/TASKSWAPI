pipeline {
    agent any

    tools {
        maven 'Default Maven'
        jdk 'Corretto JDK 11.0.13'
    }

    stages {
        stage("Build") {
            steps {
                sh "mvn -version"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
