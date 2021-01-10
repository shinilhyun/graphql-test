package shin.aiden.graphqltest.holiday.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class HolidayResponse {

    private Integer holidayId;

    private String companySeq;

    private String year;

    private String date;
    private String holidayTitle;

    private Integer orderNo;
    private String active;

    private String remark;

    private LocalDateTime createDate;
    private String createEmpSeq;

    private LocalDateTime updateDate;

    private String realDate;
}
