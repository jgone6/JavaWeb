package team_6.web_project.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team_6.web_project.domain.Account;
@Data
@NoArgsConstructor
public class AccountForm {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String age;

    @Builder
    public AccountForm(Long id, String username, String password, String email, String age){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .age(age)
                .build();
    }
}
