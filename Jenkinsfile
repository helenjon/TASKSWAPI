pipline{
    agent any

    stages {
        stage("Build"){
            steps {
            sh "mvn -version"
            sh "mvn clean compile"
            }
        }
    }

    post{
        always{
        cleanWs()
        }
    }
}
