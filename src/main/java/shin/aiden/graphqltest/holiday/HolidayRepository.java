package shin.aiden.graphqltest.holiday;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    List<Holiday> findAllByHolidayIdAfter(int id);

    Page<Holiday> findAll(Pageable pageable);
}
