package shin.aiden.graphqltest.appversion.dto;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@GraphQLType(name = "appVersionResponse", description = "앱버전조회 결")
public class AppVersionResponse {

    Integer popupSeq;
    String popupType;
    String version;
    String osType;
    String create_seq;
    LocalDateTime createDate;
    LocalDateTime update_date;
    String updateSeq;

}
