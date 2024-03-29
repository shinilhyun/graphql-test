package shin.aiden.graphqltest.holiday;

import graphql.relay.Connection;
import graphql.relay.Edge;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import shin.aiden.graphqltest.PageParam;
import shin.aiden.graphqltest.holiday.dto.HolidayResponse;
import shin.aiden.graphqltest.utils.PageUtil;

import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@Controller
@RequiredArgsConstructor
public class HolidayResolver {

    private final HolidayRepository holidayRepository;

    private final HolidayQueryRepository holidayQueryRepository;

    private final PageUtil cursorUtil;

    private final ModelMapper modelMapper;

    @GraphQLQuery(name = "holidayPage")
    public Connection<HolidayResponse> holidayPage(@GraphQLNonNull int size, String after) {
        List<Holiday> holidayList = getAllHolidayList(after);

        List<Edge<HolidayResponse>> edges = holidayList.stream()
                .limit(size)
                .map(holiday -> modelMapper.map(holiday, HolidayResponse.class))
                .map(holidayResponse -> cursorUtil.edge(holidayResponse, holidayResponse.getHolidayId()))
                .collect(Collectors.toList());

        return cursorUtil.connection(edges, size, after);

    }

    private List<Holiday> getAllHolidayList(String cursor) {
        if (cursor != null) {
            return holidayRepository.findAllByHolidayIdAfter(cursorUtil.decodeCursorWith(cursor));
        }
        return holidayRepository.findAll();
    }

    @GraphQLQuery(name = "holidayPage2")
    public Page<HolidayResponse> holidayPage(PageParam pageParam) {

        Page<HolidayResponse> map = holidayRepository.findAll(pageParam.getPageable())
                .map(holiday -> modelMapper.map(holiday, HolidayResponse.class));

        return map;
    }

    @GraphQLQuery(name = "holidayPage3")
    public Page<HolidayResponse> holidayPage3(PageParam pageParam) {

        return holidayQueryRepository.getAllHolidayPage(pageParam.getPageable())
                .map(holiday -> modelMapper.map(holiday, HolidayResponse.class));
    }

}
