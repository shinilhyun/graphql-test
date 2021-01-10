package shin.aiden.graphqltest.holiday;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hr_holiday")
@Getter
@Where(clause = "active = 'Y'")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer holidayId;

    private String companySeq;

    @Column(columnDefinition = "char")

    private String year;

    @Column(columnDefinition = "char")
    private String date;
    private String holidayTitle;

    private Integer orderNo;

    @Column(columnDefinition = "char")
    private String active = "Y";

    private String remark;

    @CreatedDate
    private LocalDateTime createDate;
    private String createEmpSeq;

    @LastModifiedDate
    private LocalDateTime updateDate;

    private String realDate;
}
