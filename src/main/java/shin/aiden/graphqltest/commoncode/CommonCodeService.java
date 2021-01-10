package shin.aiden.graphqltest.commoncode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shin.aiden.graphqltest.CursorUtil;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;

    private final CursorUtil cursorUtil;

    public List<CommonCode> getAllCommonCodePage(String cursor) {

        if (cursor != null) {
            return commonCodeRepository.findAllByCommonCodeAfter(cursor);
        }

        return commonCodeRepository.findAll();
    }
}
