# Weather Forecast Application Technology Stack
<B>Description</B> : Weather Forecast application is hosted on the AWS EC2 instance and deployed is done through the CICD pipeline;
The tool stack used for CICD is GitHub,Jenkins,Docker, AWS ECR, AWS CLI.Please refer to architecture diagram attached CICD_AWS_Deployment.drawio.pdf.Following steps were performed on AWS cloud to achieve the same:

Step 1. Create a new EC2(Free tier machine) instance on the AWS cloud and named it Jenkins_server.The Jenkins and docker installtions are done using the AWS CLI.Following steps were done to install the Jenkins and Docker respectively on the Jenkins_server EC2 machine.

<B>A : Installation commands for Java and Jenkins as Java needed to install Jenkins on the EC2 instance</B>

sudo yum update –y

sudo yum upgrade

sudo amazon-linux-extras install java-openjdk11 -y

sudo yum install jenkins -y

sudo systemctl enable jenkins

sudo systemctl start jenkins

sudo systemctl status jenkins

<B>B : Installation steps for Docker</B>

sudo amazon-linux-extras install docker

sudo yum install docker

sudo service docker start

sudo service docker start

sudo usermod -a -G docker ec2-user (Added the ec2-user to the docker group so we can execute Docker commands without using sudo.)

<B>C : Add Jenkins users to docker group</B>

sudo usermod -a -G docker jenkins

<B>D: Restart Jenkins and Docker</B>

sudo service jenkins restart

sudo systemctl daemon-reload

sudo service docker restart

Step 2 : Jenkins Configuration 
Jenkins is  installed and running on your EC2 instance. To configure Jenkins, connected to http://<server_public_DNS>:8080 from  browser. This will propmt a screen where we need to pass the password generated at /var/lib/jenkins/secrets/initialAdminPassword. Get the password through following command :

<B>sudo cat /var/lib/jenkins/secrets/initialAdminPassword</B>

After this, other screens shows where user can create the Admin User.Following this, required plugins needs to be installed from 

<B>Manage Jenkins->Manage Plugin->Available Tab</B>

Select the plugins that you want to install. <B> I've installed Amazon EC2 plugin,Amazon ECR, Docker Pipeline,CloudBees AWS Credentials.</B>

Step 3 : Created the ECR repository (ecr-jenkins-pipeline) where the Docker images will be saved.


Step 4 : Created the IAM User with Programmatic Access. This will give you a access key and secret key which will be needed later while connecting to the ECR repo from the Jenkins Pipeline script. 

Step 5 : Integration of Jenkins and Github Repository : Integrate Github with Jenkins so that whenever there is an event on the Github Repository, it can trigger the Jenkins Job. Steps are:

<B> Setting from Repository -> WebHook Panel -> Add Webhook -> Provide Jenkins URL http://<Jenkins-IP>:8080/github-webhook/</B>.

Step 6: Created Jenkins Job.

<B>Dashboard->New Item->Pipeline->Given the name Pipeling</B> Checked the Github checkbox under the General Tab and provided the Github repository URL and also checked the Github hook trigger for GitScm polling option under the Build Trigger.

Under the Pipeline tab, selected Pipeline script from the SCM, specified the Github repository, cheked the /master branch from which the Jenkins will trigger the build on commit/push.Also  the name of Jenkinfile is provided. The pipeline script is added into this script. The file location is src/main/resources/Jenkinfile.

Step 7:  Created another EC2 instance where the docker image will be pulled from the ECR. 

Step 8:  Created the Dockerfile  src/main/resources/Dockerfile and all the required steps to run the Weather Forecast application are mentioned in that.

Step 9:  After the CI CD Docker Pipeline is successfully set up, we will push commits to our Github repository and in turn, Github Webhook will trigger the CI CD Pipeline on Jenkins Server. Jenkins Server will then pull the latest code,  build a docker image and push it to AWS ECR. After the image is pushed to AWS ECR.

Step 10:  Application is accessible at http://<EC2-Instance-IP>:8080/

<H1>Design Pattern</H1>
 Builder design pattern is used to simplify the creation of objects. It simplify the code as we donot have to call the complex constructors or other setter methods on the created object.
 
<H1>Handling of sensitive information</H1>

The given API secret stored as environment variable and it's not part of the code.

<H1>Documentation</H1>
1.Generated the Swagger UI using code. The swagger-ui link is http://server-ip/swagger-ui.

2.Created Architecture diagram for CICD pipeline on AWS cloud. Please refer to the CICD_AWS_Deployment.drawio.pdf in project zip.

3.Created Sequence diagram for backend class. Please refer to  SequenceDiagram.drawio.pdf

<H1>Fault Tolerance</H1>
1. Used Hystrix to achieve the circut breaker concept. If the public API is not available, application will not fail as it's return the response from the fallback method.Exclusions are mentioned in the Hystrix command for NotFound.class excpetion as in this case, system will not call the fallback method, instead it return the error response from the underlying API.

<H1>Call to Public API</H1>
Underlying Public API is called through Synchronous communication using Rest template.

<H1>Port</H1>
Port 8080 is used to access the application.

<H1>Other Best Practices followed</H1>
1. While implementation taken care of SOLID design principles, 12 factor concepts.

2.Hateous implementation is provided.

3.Service is accessible through POSTMAN.

4.Response type is JSON.

















