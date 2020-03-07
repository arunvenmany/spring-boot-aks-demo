package com.spring.handson.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.validation.constraints.NotNull;

@Configuration
@EnableMongoRepositories(basePackages = "com.spring.handson.repository")
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @NotNull
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.protocol}")
    private String protocol;

    @Value("${spring.data.mongodb.username:}")
    private String username;

    @Value("${spring.data.mongodb.password:}")
    private String password;

    @Value("${spring.data.mongodb.replicaSet:}")
    private String replicaSet;

    @Value("${spring.data.mongodb.maxConnectionIdleTimeInMs}")
    private String maxConnectionIdleTimeInMs;

    @Value("${security.azureConn:false}")
    private boolean azureConn;

    public static final String APPLICATION_NAME = "spring-boot-aks-app";


    @Override
    public MongoMappingContext mongoMappingContext()
            throws ClassNotFoundException {
        return super.mongoMappingContext();
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    public MongoClientURI mongoClientURI() {
        String uri;
        if (this.host.contains("localhost")) {
            uri = "mongodb://" + getHost();
        } else {
            uri = "mongodb+srv://"
                    + getUsername() + ":"
                    + getPassword() + "@"
                    + getHost() + "/?replicaSet="
                    + getReplicaSet();
        }
        return new MongoClientURI(uri,
                MongoClientOptions
                        .builder()
                        .applicationName(APPLICATION_NAME)
                        .sslEnabled(azureConn)
                        .maxConnectionIdleTime(Integer.parseInt(maxConnectionIdleTimeInMs)));
    }

    @Override
    public MongoClient mongoClient() {

        return new MongoClient(mongoClientURI());
    }


    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(final String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getReplicaSet() {
        return replicaSet;
    }

    public void setReplicaSet(String replicaSet) {
        this.replicaSet = replicaSet;
    }
}