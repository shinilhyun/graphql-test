package shin.aiden.graphqltest;

import graphql.ErrorType;
import graphql.GraphQLError;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomGraphQLException extends RuntimeException implements GraphQLError {
    private final int errorCode;

    public CustomGraphQLException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", this.getMessage());
        return customAttributes;
    }

    @Override
    public List getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}
