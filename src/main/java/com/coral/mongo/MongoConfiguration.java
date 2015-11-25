/**
 *
 */
package com.coral.mongo;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Basic configuration for mongodb.
 */
@Configuration
@EnableMongoRepositories
@PropertySource({ "classpath:mongo.properties" })
public class MongoConfiguration {

    private final static String MONGODB_HOST = "mongodb.host";

    private final static String MONGODB_DATABASE = "mongodb.database";

    @Autowired
    protected Environment env;

    @Bean
    public Mongo mongo() throws UnknownHostException {
        Mongo mongo = new Mongo(env.getProperty(MONGODB_HOST));
        return mongo;
    }

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        MongoTemplate mt = new MongoTemplate(mongo, env.getProperty(MONGODB_DATABASE));
        return mt;
    }

    @Bean
    public GridFsTemplate gridFsTemplate(Mongo mongo) {
        MongoDbFactory dbFactory = new SimpleMongoDbFactory(mongo, env.getProperty(MONGODB_DATABASE));
        MongoMappingContext mappingContext = new MongoMappingContext();
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbFactory, mappingContext);
        return new GridFsTemplate(dbFactory, mongoConverter);
    }
}
