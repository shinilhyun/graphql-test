package shin.aiden.graphqltest.config;

import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.AsyncSerialExecutionStrategy;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfiguration {

    @Autowired
    GraphQLExceptionHandler graphQLExceptionHandler;

    @Bean
    public GraphQL graphQL(GraphQLSchema schema) {
        GraphQL.Builder builder = GraphQL.newGraphQL(schema)
                .queryExecutionStrategy(new AsyncExecutionStrategy(graphQLExceptionHandler))
                .mutationExecutionStrategy(new AsyncSerialExecutionStrategy(graphQLExceptionHandler));
        return builder.build();
    }
}

