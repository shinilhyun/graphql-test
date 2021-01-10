package shin.aiden.graphqltest.commoncode;

import graphql.relay.*;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import shin.aiden.graphqltest.utils.PageUtil;
import shin.aiden.graphqltest.commoncode.dto.CommonCodeResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@GraphQLApi
@Validated
@RequiredArgsConstructor
public class CommonCodeResolver {

    private final CommonCodeRepository commonCodeRepository;

    private final CommonCodeService commonCodeService;

    private final PageUtil pageUtil;

    private final ModelMapper modelMapper;

    @GraphQLQuery(name = "commonCodeList", description = "commonCode 모든 리스트 가져오기")
    public List<CommonCodeResponse> getCommonCodeList() {
        List<CommonCode> commonCodeList = commonCodeRepository.findAll();

        return commonCodeList.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeResponse.class))
                .collect(Collectors.toList());
    }

    @GraphQLQuery(name = "commonCodePage", description = "commonCode 모든 리스트 페이징 처리해서 가져오기")
    public Connection<CommonCodeResponse> getCommonCodePage(@GraphQLNonNull int first, String cursor) {

        List<CommonCode> commonCodeList = commonCodeService.getAllCommonCodePage(cursor);

        List<Edge<CommonCodeResponse>> edges = commonCodeList.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeResponse.class))
                .map(commonCodeResponse -> new DefaultEdge<>(commonCodeResponse, new DefaultConnectionCursor(commonCodeResponse.getCommonCode())))
                .limit(first)
                .collect(Collectors.toList());

        return pageUtil.connection(edges, first, cursor);
    }

}
