package org.zerock.mreview.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.MemberRole;
import org.zerock.mreview.repository.MemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){

        //1 - 80 USER
        //81 - 90 USER, MANAGER
        //91 - 100 USER, MANAGER, ADMIN

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .email("user" + i + "@zerock.org")
                    .nickname("사용자" + i)
                    .fromSocial(false)
                    .pw(passwordEncoder.encode("1111"))
                    .build();

            //default role
            member.addMemberRole(MemberRole.USER);

            if (i >80){
                member.addMemberRole(MemberRole.MANAGER);
            }

            if (i > 90){
                member.addMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){

        Optional<Member> result = memberRepository.findByEmail("user95@zerock.org", false);

        Member member = result.get();

        System.out.println(member);
    }
}
