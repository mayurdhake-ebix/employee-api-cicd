pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "mayurdhake/employee-api-cicd:%BUILD_NUMBER%"
    }

    stages {

        // ❌ You can REMOVE this stage (Jenkins already does checkout)
        // Keeping it is fine, but not needed
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/mayurdhake-ebix/employee-api-cicd.git'
            }
        }

        stage('Build Application') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat '''
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    docker push %DOCKER_IMAGE%
                    '''
                }
            }
        }
    }
}