package shin.aiden.graphqltest.appversion;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_version")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer popupSeq;

    String popupType;

    @Column(columnDefinition = "char(8)")
    String version;

    String osType;
    String create_seq;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createDate;

    @LastModifiedDate
    LocalDateTime update_date;
    String updateSeq;

    @Builder
    public AppVersion(String popupType, String version, String osType, String create_seq, String updateSeq) {
        this.popupType = popupType;
        this.version = version;
        this.osType = osType;
        this.create_seq = create_seq;
        this.updateSeq = updateSeq;
    }

}
