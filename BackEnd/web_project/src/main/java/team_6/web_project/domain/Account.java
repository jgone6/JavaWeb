package team_6.web_project.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
//다른 패키지에서 생성자 함부로 생성 No
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @Column(name = "user_id")
    //SQL에서 자동생성되도록 돕는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String age;

    @Builder
    public Account(Long id, String username, String password, String email, String age){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }
}
