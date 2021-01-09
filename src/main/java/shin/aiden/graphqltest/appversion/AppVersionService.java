package shin.aiden.graphqltest.appversion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shin.aiden.graphqltest.CustomGraphQLException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppVersionService {

    private final AppVersionRepository appVersionRepository;

    public AppVersion getAppVersion(int popupSeq) {
        return appVersionRepository.findByPopupSeq(popupSeq)
                                   .orElseThrow(IllegalArgumentException::new);
    }

    public List<AppVersion> getAppVersionList(String popupType) {
        return appVersionRepository.findByPopupType(popupType);
    }

    public AppVersion save(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    public void delete(int popupSeq) {
        appVersionRepository.deleteById(popupSeq);
    }
}
