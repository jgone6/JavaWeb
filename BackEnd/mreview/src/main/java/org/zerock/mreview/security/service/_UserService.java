package org.zerock.mreview.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.zerock.mreview.dto.MemberDTO;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.entity.Member;

public interface _UserService {

    String createUser(MemberDTO memberDTO);

    MemberDTO getMember(String email);

    void modify(MemberDTO dto);

    void remove(String email);

    default Member dtoToEntity(MemberDTO memberDTO){

        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .pw(new BCryptPasswordEncoder().encode(memberDTO.getPw()))
                .nickname(memberDTO.getNickname())
                .fromSocial(false)
                .sex(memberDTO.getSex())
                .birthday(memberDTO.getBirthday())
                .build();

        return member;
    }

    default  MemberDTO entityToDTO(Member member){

        MemberDTO memberDTO = MemberDTO.builder()
                .email(member.getEmail())
                .pw(member.getPw())
                .nickname(member.getNickname())
                .sex(member.getSex())
                .birthday(member.getBirthday())
                .modDate(member.getModDate())
                .regDate(member.getRegDate())
                .build();

        return memberDTO;
    }
}
