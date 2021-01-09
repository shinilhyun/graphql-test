package shin.aiden.graphqltest.appversion;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import shin.aiden.graphqltest.appversion.dto.AppVersionResponse;
import shin.aiden.graphqltest.appversion.dto.AppVersionSaveRequest;

import java.util.List;
import java.util.stream.Collectors;

@Component
@GraphQLApi
@RequiredArgsConstructor
public class AppVersionResolver {

    private final AppVersionService appVersionService;

    private final ModelMapper modelMapper;

    @GraphQLQuery(name = "appVersion")
    public AppVersionResponse getAppVersion(int popupSeq) {
        AppVersion appVersion = appVersionService.getAppVersion(popupSeq);
        return modelMapper.map(appVersion, AppVersionResponse.class);
    }

    @GraphQLQuery(name = "appVersionList")
    public List<AppVersionResponse> searchAppVersionList(@GraphQLNonNull String popupType) {

        List<AppVersion> appVersionList = appVersionService.getAppVersionList(popupType);

        return appVersionList.stream()
                .map(appVersion -> modelMapper.map(appVersion, AppVersionResponse.class))
                .collect(Collectors.toList());
    }

    @GraphQLMutation(name = "saveAppVersion")
    public AppVersionResponse saveAppVersion(AppVersionSaveRequest saveRequest) {

        AppVersion appVersion = appVersionService.save(AppVersion.builder()
                .popupType(saveRequest.getPopupType())
                .version(saveRequest.getVersion())
                .osType(saveRequest.getOsType())
                .build());
        return modelMapper.map(appVersion, AppVersionResponse.class);
    }
}
