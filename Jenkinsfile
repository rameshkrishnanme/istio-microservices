pipeline {
    agent {
        kubernetes {
            label 'jenkins-agent'
            yamlFile 'k8s-jenkins-agent.yaml'
        }
    }
	environment {
        dockerRegistry = 'docker.io' //credentials('DOCKER_REGISTRY')
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
			    script {
                  container('docker') {
				  
					 withCredentials([usernamePassword(credentialsId: 'DOCKER_CRED', 
                                            passwordVariable: 'password',
                                            usernameVariable: 'username')]) {
						sh 'docker images'
						sh "docker login -u $username -p $password $dockerRegistry"
					//	sh "docker tag ${imageName} ${dockerRegistry}/${imageName}:${sem_version}"
					//	sh "docker tag ${imageName} ${dockerRegistry}/${imageName}:latest"
					//	sh "docker push ${dockerRegistry}/${imageName}:${sem_version}"
					//	sh "docker push ${dockerRegistry}/${imageName}:latest"
					}
				  
					
				  
				  }
				}  
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
					 // sh("helm del istio-role")
					 // sh("helm list")
					 // sh("helm install --debug istio-role charts/service")
					  sh("helm upgrade --debug istio-role charts/service --version=1.2")
					  sh("helm ls")
                      //sh("helm upgrade --install --wait prod-my-app ./helm --namespace prod")
                    }
                }
            }
        }
    }
}
