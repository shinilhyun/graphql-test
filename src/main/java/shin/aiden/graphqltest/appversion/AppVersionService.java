package shin.aiden.graphqltest.appversion;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shin.aiden.graphqltest.appversion.dto.AppVersionRequest;
import shin.aiden.graphqltest.appversion.dto.AppVersionResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@GraphQLApi
@RequiredArgsConstructor
public class AppVersionService {
    private final AppVersionRepository appVersionRepository;

    @GraphQLQuery(name = "appVersion")
    public AppVersionResponse getAppVersion(int popupSeq) {
        AppVersion appVersion = appVersionRepository.findByPopupSeq(popupSeq).orElseThrow(IllegalArgumentException::new);

        AppVersionResponse appVersionResponse = new ModelMapper().map(appVersion, AppVersionResponse.class);

        return appVersionResponse;

    }

    @GraphQLQuery(name = "appVersion2")
    public List<AppVersionResponse> getAppversionFindByPopupType(AppVersionRequest request) throws Exception {

        String popupType = request.getPopupType();
        String version = request.getVersion();


        List<AppVersion> appVersionList = appVersionRepository.findByPopupType(popupType);

        List<AppVersionResponse> appVersionResponseList = appVersionList.stream()
                .map(appVersion -> new ModelMapper().map(appVersion, AppVersionResponse.class))
                .collect(Collectors.toList());

        return appVersionResponseList;

    }
}
