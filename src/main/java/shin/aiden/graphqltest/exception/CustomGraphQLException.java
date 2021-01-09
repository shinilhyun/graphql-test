package shin.aiden.graphqltest.exception;

import graphql.ErrorType;
import graphql.GraphQLError;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomGraphQLException extends RuntimeException implements GraphQLError {
    private final int errorCode;
    private String message;

    public CustomGraphQLException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.errorCode = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

    public CustomGraphQLException(ExceptionType exceptionType, Throwable cause) {
        super(cause.getMessage(), cause);
        this.errorCode = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", message);
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
