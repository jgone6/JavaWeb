package org.zerock.mreview.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.mreview.dto.MemberDTO;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.MemberRole;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;
import org.zerock.mreview.repository.MemberRepository;
import org.zerock.mreview.security.dto.AuthMemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class _UserDetailsService implements UserDetailsService, _UserService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("UserDetailsService loadUserByUsername " + username);

        Optional<Member> result = memberRepository.findByEmail(username, false);

        log.info("result.isPresent : " + result.isPresent());

        if (!result.isPresent()){
            throw new UsernameNotFoundException("Check Email or Social");
        }

        Member member = result.get();

        log.info("---------------------------------");
        log.info(member);

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPw(),
                member.isFromSocial(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet())
        );

        authMemberDTO.setNickname(member.getNickname());
        authMemberDTO.setFromSocial(member.isFromSocial());

        return authMemberDTO;
    }

    @Override
    public String createUser(MemberDTO memberDTO){
        Member member = dtoToEntity(memberDTO);

        //default role
        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);

        return member.getEmail();
    }

    @Override
    public MemberDTO getMember(String email){

        Optional<Member> result = memberRepository.findByEmail(email, false);

        Member member = result.get();

        return entityToDTO(member);
    }


    @Override
    public void modify(MemberDTO dto){
        //업데이트 하는 항목은 '제목', '내용'

        Optional<Member> result = memberRepository.findById(dto.getEmail());

        if(result.isPresent()){
            Member entity = result.get();

            entity.changeNickname(dto.getNickname());
            entity.changeSex(dto.getSex());

            memberRepository.save(entity);
        }
    }

    @Override
    public void remove(String email){
        memberRepository.deleteById(email);
    }
}
