package org.acme.beans;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import io.quarkus.arc.DefaultBean;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

public class AppConfig {
    @ConfigProperty(name = "quarkus.dynamodb.endpoint-override")
    String endpoint;
    @ConfigProperty(name = "quarkus.dynamodb.aws.region")
    String awsRegion;
    @ConfigProperty(name = "quarkus.dynamodb.aws.credentials.static-provider.access-key-id")
    String awsAccessKey;
    @ConfigProperty(name = "quarkus.dynamodb.aws.credentials.static-provider.secret-access-key")
    String awsSecretKey;

    @Produces
    @ApplicationScoped
    @DefaultBean
    DynamoDB loadDynamodb() {
        return new DynamoDB(getAmazonDynamodbClient());
    }

    AmazonDynamoDB getAmazonDynamodbClient() {
        return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, awsRegion)).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey))).build();
    }

    @Produces
    @ApplicationScoped
    @DefaultBean
    DynamoDBMapper loadDynamoDBMapper(){
        return new DynamoDBMapper(getAmazonDynamodbClient());
    }
}
