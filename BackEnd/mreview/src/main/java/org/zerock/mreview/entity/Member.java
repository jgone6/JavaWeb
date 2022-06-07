package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table
public class Member extends BaseEntity{

    @Id
    private String email;

    private String pw;

    private String nickname;

    private String sex;

    private String birthday;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){

        roleSet.add(memberRole);
    }

    public void changeNickname(String nickname){this.nickname = nickname;}

    public void changeSex(String sex){this.sex = sex;}
}
