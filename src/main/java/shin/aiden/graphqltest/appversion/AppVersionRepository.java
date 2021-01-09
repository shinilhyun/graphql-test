package shin.aiden.graphqltest.appversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Integer> {
    Optional<AppVersion> findByPopupSeq(int popupSeq);

    List<AppVersion> findByPopupType(String popupType);
}
