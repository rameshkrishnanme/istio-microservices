pipeline {
    agent {
        kubernetes {
            label 'jenkins-agent'
            yamlFile 'k8s-jenkins-agent.yaml'
        }
    }
    stages {
        stage('Run unit tests') {
            steps {
               echo "Run unit tests"
            }
        }

        stage('Build application') {
            steps {
              // Build the app
			  echo "Build the app"
            }
        }

        stage('Docker publish') {
            steps {
              // Publish a docker image for your application 
			  echo "Docker publish"
            }
        }

        stage('Deployment') {
            steps {
                script {
                  container('helm') {
					  echo "k8s Deployment"
					  // Init authentication and config for your kubernetes cluster
					  // sh("helm init --client-only --skip-refresh")
					  // sh("helm help")
					  sh("helm list")
					  sh("helm install --debug istio-role charts/service")
					  //sh("helm upgrade --debug istio-role charts/service --version=1.2")
					  sh("helm ls")
                      //sh("helm upgrade --install --wait prod-my-app ./helm --namespace prod")
                    }
                }
            }
        }
    }
}
