package team_6.web_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        log.info("home controller");
        return "home";
    }
    @GetMapping("/user")
    public String dispUser(Model model){
        log.info("home controller");
        return "/user/login/user";
    }
    @GetMapping("/manager")
    public String dispManager(Model model){
        log.info("home controller");
        return "/user/manager";
    }
    @GetMapping("/admin")
    public String dispAdmin(Model model){
        log.info("home controller");
        return "/user/admin";
    }
}
