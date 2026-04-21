pipeline {
    agent any

    environment {
    DOCKER_IMAGE = "mayurdhake/employee-api-cicd:${BUILD_NUMBER}"
}

    stages {

        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-creds', url: 'https://github.com/mayurdhake-ebix/employee-api-cicd.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_IMAGE
                    '''
                }
            }
        }
    }
}