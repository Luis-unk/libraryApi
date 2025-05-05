package github.devluiss.libraryapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //RestController -> Api || Controller -> Páginas Web
public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }

}
