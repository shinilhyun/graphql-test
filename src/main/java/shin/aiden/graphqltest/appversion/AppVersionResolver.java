package shin.aiden.graphqltest.appversion;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import shin.aiden.graphqltest.appversion.dto.AppVersionResponse;
import shin.aiden.graphqltest.appversion.dto.AppVersionSaveRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Component
@GraphQLApi
@RequiredArgsConstructor
@Validated
@Slf4j
public class AppVersionResolver {

    private final AppVersionService appVersionService;

    private final AppVersionRepository appVersionRepository;

    private final ModelMapper modelMapper;

    @GraphQLQuery(name = "appVersion")
    public AppVersionResponse getAppVersion(@GraphQLArgument(name = "id") int popupSeq) {
        AppVersion appVersion = appVersionService.getAppVersion(popupSeq);
        return modelMapper.map(appVersion, AppVersionResponse.class);
    }

    @GraphQLQuery(name = "appVersionList")
    public List<AppVersionResponse> searchAppVersionList(@GraphQLArgument(name = "popupType") @NotBlank String popupType) {

        List<AppVersion> appVersionList = appVersionService.getAppVersionList(popupType);

        return appVersionList.stream()
               .map(appVersion -> modelMapper.map(appVersion, AppVersionResponse.class))
                .collect(Collectors.toList());
    }

    @GraphQLQuery(name = "allAppVersionList")
    public List<AppVersionResponse> allAppVersion() {

        List<AppVersion> appVersionList = appVersionRepository.findAll();

        return appVersionList.stream()
                .map(appVersion -> modelMapper.map(appVersion, AppVersionResponse.class))
                .collect(Collectors.toList());
    }

    @GraphQLMutation(name = "saveAppVersion")
    public AppVersionResponse saveAppVersion(@GraphQLArgument(name = "save") @Valid AppVersionSaveRequest saveRequest) {

        AppVersion appVersion = appVersionService.save(AppVersion.builder()
                .popupType(saveRequest.getPopupType())
                .version(saveRequest.getVersion())
                .osType(saveRequest.getOsType())
                .build());

        return modelMapper.map(appVersion, AppVersionResponse.class);
    }

    @GraphQLMutation(name = "deleteAppVersion")
    public String deleteAppVersion(@GraphQLArgument(name = "id") int popupSeq) {

        appVersionService.delete(popupSeq);

        return "삭제완료";
    }
}
