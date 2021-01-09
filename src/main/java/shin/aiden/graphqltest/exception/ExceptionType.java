package shin.aiden.graphqltest.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {
    DELETE_ERROR(100, "삭제 실패");


    private int code;
    private String message;

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
