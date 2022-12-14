package jpabook.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Member_Temp")
@Table(name = "MEMBER_TEMP")
public class Member_Temp {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    private String fullName;

    @Access(AccessType.PROPERTY)
    public String getFullName() {
        return firstName + lastName;
    }

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;
}
