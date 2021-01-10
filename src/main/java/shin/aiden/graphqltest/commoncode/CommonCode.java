package shin.aiden.graphqltest.commoncode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "hr_common_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "active='Y'")
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commonCode;
    private String groupCode;
    private String codeName;
    private String orderNo;

    @Column(columnDefinition = "char")
    private String active = "Y";

    private String remark;
    private String etc1;
    private String etc2;
    private String etc4;
    private String etc5;

}
