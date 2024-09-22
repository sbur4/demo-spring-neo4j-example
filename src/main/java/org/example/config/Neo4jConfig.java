package org.example.config;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jAuditing
@EnableNeo4jRepositories(basePackages = "org.example.repository")
@org.springframework.context.annotation.Configuration
public class Neo4jConfig {
    @Value("${spring.neo4j.uri}")
    private String dbUri;
    @Value("${spring.neo4j.authentication.username}")
    private String dbUser;
    @Value("${spring.neo4j.authentication.password}")
    private String dbPassword;

    @Value("${spring.data.neo4j.database}")
    private String db;

    @Bean
    public Configuration cypherDslConfiguration() {
        return Configuration.newConfig()
//                .withDialect(Dialect.NEO4J_5).build();
                .withDialect(Dialect.DEFAULT)
                .build();
    }

    @Bean
    public Driver driver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));

//        Driver driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));
//
//        try (Session session = driver.session()) {
//            String query = "CREATE (database:Database {name:\"" + db + "\"})";
//            session.run(query);
//            System.out.println("Database" + db + " created successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error creating database: " + e.getMessage());
//        }
//        return driver;
    }
}
