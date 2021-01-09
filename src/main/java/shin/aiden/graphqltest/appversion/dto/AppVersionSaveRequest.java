package shin.aiden.graphqltest.appversion.dto;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@GraphQLType(name = "AppversionSaveRequest", description = "App version 저장 요청")
public class AppVersionSaveRequest {

    @GraphQLInputField(description = "팝업타입")
    @NotBlank
    private String popupType;
    private String version;
    private String osType;
}
