package shin.aiden.graphqltest.appversion.dto;

import io.leangen.graphql.annotations.*;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@GraphQLType(name = "appVersionResponse", description = "앱버전조회 결과")
public class AppVersionResponse {

    @GraphQLQuery(name = "id", description = "팝업 고유번호")
    Integer popupSeq;
    String popupType;
    String version;
    String osType;
    String createSeq;
    LocalDateTime createDate;
    LocalDateTime update_date;
    String updateSeq;

}
