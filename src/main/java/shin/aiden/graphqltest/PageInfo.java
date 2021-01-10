package shin.aiden.graphqltest;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@GraphQLType
public class PageInfo {

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

        if (sort == null) {
            return null;
        }

        Sort sortList = Sort.unsorted();

        for (int i = 0; i < sort.length; i++) {

            if (sort[i] != null) {
                String[] split = sort[i].split(",");

                if (split.length == 1) {
                    sortList.and(Sort.by(split[0]));
                } else {
                    sortList.and(Sort.by(Sort.Direction.fromString(split[1]), split[0]));
                }
            }
        }
        return sortList;
    }
}
