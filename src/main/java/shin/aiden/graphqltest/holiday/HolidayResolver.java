package shin.aiden.graphqltest.holiday;

import graphql.relay.*;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import shin.aiden.graphqltest.utils.PageUtil;
import shin.aiden.graphqltest.holiday.dto.HolidayResponse;

import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@Controller
@RequiredArgsConstructor
public class HolidayResolver {

    private final HolidayRepository holidayRepository;

    private final PageUtil pageUtil;

    private final ModelMapper modelMapper;

    @GraphQLQuery(name = "holidayPage")
    public Connection<HolidayResponse> holidayPage(@GraphQLNonNull int size, String cursor) {
        List<Holiday> holidayList = getAllHolidayList(cursor);

        List<Edge<HolidayResponse>> edges = holidayList.stream()
                .limit(size)
                .map(holiday -> modelMapper.map(holiday, HolidayResponse.class))
                .map(holidayResponse -> pageUtil.edge(holidayResponse, holidayResponse.getHolidayId()))
                .collect(Collectors.toList());

        return pageUtil.connection(edges, size, cursor);

    }

    private List<Holiday> getAllHolidayList(String cursor) {
        if (cursor != null) {
            return holidayRepository.findAllByHolidayIdAfter(pageUtil.decodeCursorWith(cursor));
        }
        return holidayRepository.findAll();
    }
}
