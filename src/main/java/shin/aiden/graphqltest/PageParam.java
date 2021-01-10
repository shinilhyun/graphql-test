package shin.aiden.graphqltest;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@GraphQLType
public class PageParam {

    @GraphQLInputField(name = "size", defaultValue = "10")
    private int size;
    @GraphQLInputField(name = "page", defaultValue = "0")
    private int page;
    @GraphQLInputField(name = "sort", description = "ex) sort: \"id,desc\" \n" +
            " 여러개면 sort:[\"id.desc\", \"title.asc\"] \n " +
            "정렬 조건 생략 시 ASC 정렬됨 ex) sort:\"id\"")
    private String[] sort;


    public Pageable getPageable() {

        Sort sortList = getSort();

        if (sortList == null || sortList.isEmpty()) {
            return PageRequest.of(page, size);
        }

        return PageRequest.of(page, size, sortList);
    }

    private Sort getSort() {

        List<Sort.Order> orderList = new ArrayList<>();
        if (sort == null || sort[0] == null) {
            return null;
        }

        for (int i = 0; i < sort.length; i++) {

            if (sort[i] != null) {
                String[] split = sort[i].split(",");

                if (split.length == 1) {
                    orderList.add(Sort.Order.by(split[0]));
                } else {
                    orderList.add(new Sort.Order(Sort.Direction.valueOf(split[1].toUpperCase()), split[0]));
                }
            }
        }

        return Sort.by(orderList);
    }
}
