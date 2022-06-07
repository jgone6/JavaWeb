package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.mreview.dto.MemberDTO;
import org.zerock.mreview.security.service._UserDetailsService;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserController {
    private final _UserDetailsService userService;

    @GetMapping("/registerUser")
    public String createUserForm(Model model){
        model.addAttribute("userForm", new MemberDTO());
        return "user/register";
    }

    @PostMapping("/registerUser")
    //유효성 검증
    public String createUser(@Valid MemberDTO memberDTO, BindingResult result){
        if(result.hasErrors()){
            return "user/register";
        }

        userService.createUser(memberDTO);
        return "redirect:/movie/list";
    }

    @GetMapping("/loginUser")
    public String loginUser(){
        return "user/user";
    }

    @GetMapping("/logoutUser")
    public String logoutUser(){
        return "redirect:/movie/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Authentication authentication, Model model){

        log.info("authentication : " + authentication);

        String email = authentication.getName();

        log.info("email : " + email);

        MemberDTO memberDTO = userService.getMember(email);

        model.addAttribute("dto", memberDTO);
    }

    @PostMapping("/modify")
    public String modify(MemberDTO dto, RedirectAttributes redirectAttributes){
        log.info("post modify........................................");
        log.info("dto: " + dto);

        userService.modify(dto);

        redirectAttributes.addAttribute("email", dto.getEmail());

        return "redirect:/user/read";
    }

    @PostMapping("/remove")
    public String remove(String email, RedirectAttributes redirectAttributes){
        log.info("email" + email);

        userService.remove(email);

        redirectAttributes.addFlashAttribute("msg", email);

        return "redirect:/user/logoutUser";
    }
}