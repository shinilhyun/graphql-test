package shin.aiden.graphqltest.appversion.dto;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = "AppversionSaveRequest", description = "App version 저장 요청")
public class AppVersionSaveRequest {

    @GraphQLQuery(name = "popupType", description = "팝업 타입")
    String popupType;
    String version;
    String osType;
}
