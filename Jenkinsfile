pipeline {
    agent {
        kubernetes {
            label 'jenkins-agent'
            yamlFile 'k8s-jenkins-agent.yaml'
        }
    }
	environment {
        dockerRegistry = 'us.gcr.io/gke-istio-275114';//'docker.io' //credentials('DOCKER_REGISTRY')
		imageName = 'istiorole'
		build_version = "v${env.BUILD_NUMBER}"
		finalImage = '' 
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
				  
					/*withCredentials([usernamePassword(credentialsId: 'DOCKER_CRED', 
                                            passwordVariable: 'password',
                                            usernameVariable: 'username')]) {
											
						sh 'docker images'
						sh "docker login -u $username -p $password $dockerRegistry"
						sh "docker build -t ${imageName}:${build_version} ."
						sh 'docker images'
						sh "docker tag ${imageName}:${build_version} $username/${imageName}:${build_version}"
						sh "docker tag ${imageName}:${build_version} $username/${imageName}:latest"
						sh "docker push $username/${imageName}:${build_version}"
						sh "docker push $username/${imageName}:latest"
						
						finalImage = "$username/${imageName}" 
					}*/
					withCredentials([file(credentialsId: 'gcrdocker', variable: 'GC_KEY')]){
					    sh "cat '$GC_KEY' | docker login -u _json_key --password-stdin https://us.gcr.io"
					    //sh "gcloud auth activate-service-account --key-file='$GC_KEY'"
					    //sh "gcloud auth configure-docker"
					    /*GLOUD_AUTH = sh (
							script: 'gcloud auth print-access-token',
							returnStdout: true
						).trim()*/
					    echo "Pushing image To GCR"
						
					    // sh "docker push eu.gcr.io/${google_projectname}/${image_name}:${image-tag}"
						
						sh 'docker images'
						sh "docker build -t ${imageName}:${build_version} ."
						sh 'docker images'
						sh "docker tag ${imageName}:${build_version} $dockerRegistry/${imageName}:${build_version}"
						sh "docker tag ${imageName}:${build_version} $dockerRegistry/${imageName}:latest"
						sh "docker push $dockerRegistry/${imageName}:${build_version}"
						sh "docker push $dockerRegistry/${imageName}:latest"
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
					 
					 // Building the command
					  def helmCommand = "helm upgrade --debug istio-role charts/service --version=${build_version} --set app.version=${build_version} --set app.image=${finalImage}"
					                    					 
					  sh("${helmCommand}")
					  sh("helm ls")
                      //sh("helm upgrade --install --wait prod-my-app ./helm --namespace prod")
                    }
                }
            }
        }
    }
}
