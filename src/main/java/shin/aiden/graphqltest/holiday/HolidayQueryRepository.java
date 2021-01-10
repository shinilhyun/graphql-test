package shin.aiden.graphqltest.holiday;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import shin.aiden.graphqltest.holiday.dto.HolidayResponse;
import shin.aiden.graphqltest.utils.QueryDslUtil;

import javax.persistence.OrderBy;
import java.util.List;

import static shin.aiden.graphqltest.holiday.QHoliday.holiday;

@Repository
@RequiredArgsConstructor
public class HolidayQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Holiday> getAllHolidayPage(Pageable pageable) {

        QueryResults<Holiday> query = jpaQueryFactory.select(holiday)
                .from(holiday)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(QueryDslUtil.getOrderSpecifiers(pageable, holiday))
                .fetchResults();

        return PageableExecutionUtils.getPage(query.getResults(), pageable, query::getTotal);
    }
}
