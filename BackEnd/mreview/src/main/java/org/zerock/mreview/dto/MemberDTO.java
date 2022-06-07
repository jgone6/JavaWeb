package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String email;

    private String nickname;

    private String pw;

    private String sex;

    private String birthday;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
