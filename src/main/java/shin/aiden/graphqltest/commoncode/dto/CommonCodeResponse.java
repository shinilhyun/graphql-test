package shin.aiden.graphqltest.commoncode.dto;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(description = "CommonCode 응답객체")
public class CommonCodeResponse {

    private String commonCode;
    private String groupCode;
    private String codeName;
    private String orderNo;

    private String active = "Y";

    private String remark;
    private String etc1;
    private String etc2;
    private String etc4;
    private String etc5;
}
