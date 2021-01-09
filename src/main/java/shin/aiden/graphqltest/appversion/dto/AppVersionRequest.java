package shin.aiden.graphqltest.appversion.dto;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType
public class AppVersionRequest {

    private String version;

    private String popupType;
}
