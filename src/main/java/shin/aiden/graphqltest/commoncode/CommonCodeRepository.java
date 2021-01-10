package shin.aiden.graphqltest.commoncode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, String> {
    List<CommonCode> findAllByCommonCodeAfter(String commonCodeSeq);
}
