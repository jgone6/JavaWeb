package org.zerock.web_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.web_board.dto.AccountForm;
import org.zerock.web_board.service.AccountService;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final AccountService accountService;

    @GetMapping("/loginUser")
    public String createUserForm(Model model){
        model.addAttribute("userForm", new AccountForm());
        return "user/login/register";
    }

    @PostMapping("/loginUser")
    //유효성 검증
    public String createUser(@Valid AccountForm form, BindingResult result){
        if(result.hasErrors()){
            return "user/login/register";
        }

        accountService.createUser(form);
        return "redirect:/";
    }
}
