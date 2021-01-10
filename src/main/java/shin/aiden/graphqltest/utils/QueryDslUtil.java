package shin.aiden.graphqltest.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;


public class QueryDslUtil {

    public static OrderSpecifier[] getOrderSpecifiers(Pageable pageable, EntityPathBase entityPathBase) {

        List<OrderSpecifier> ORDERS = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(entityPathBase.getType(), entityPathBase.getMetadata());
            ORDERS.add(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        return ORDERS.stream().toArray(OrderSpecifier[]::new);
    }
}
