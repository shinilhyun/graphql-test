package shin.aiden.graphqltest.appversion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppVersionController {

    private final AppVersionRepository appVersionRepository;

    @GetMapping("/app-version")
    public ResponseEntity findAppVersion(@RequestParam("id") int id) {
        AppVersion appVersion = appVersionRepository.findByPopupSeq(id).orElseThrow(IllegalAccessError::new);
        return ResponseEntity.ok(appVersion);
    }
}
