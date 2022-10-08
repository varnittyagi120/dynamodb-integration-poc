Steps to create a quarkus application, integrate dynamodb and deploy it on amazon ec2
1. Create a quarkus application
2. Install supported java(11+) and maven(3.8. 1+) version
3. Import dependency related to Java SDK in your quarkus project
4. Use amazon java interface functions to perform CRUD operation with dynamodb
5. Build project with maven command and create a jar file
6. Create an Ec2 instance on aws and define inbound rules / subnet
7. Install java 11 in ec2
8. Take ssh for ec2 and copy jar file from local to ec2
9. run java -jar jarfilename.jar
10. you can access api's with public context url of ec2 
