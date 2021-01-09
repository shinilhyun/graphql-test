package shin.aiden.graphqltest.appversion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shin.aiden.graphqltest.exception.CustomGraphQLException;
import shin.aiden.graphqltest.exception.ExceptionType;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AppVersionService {

    private final AppVersionRepository appVersionRepository;

    @Transactional(readOnly = true)
    public AppVersion getAppVersion(int popupSeq) {
        return appVersionRepository.findByPopupSeq(popupSeq)
                                   .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public List<AppVersion> getAppVersionList(String popupType) {
        return appVersionRepository.findByPopupType(popupType);
    }

    public AppVersion save(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    public void delete(int popupSeq) {
        try {
            appVersionRepository.deleteById(popupSeq);
        } catch (Exception e) {
            throw new CustomGraphQLException(ExceptionType.DELETE_ERROR, e);
        }
    }
}
