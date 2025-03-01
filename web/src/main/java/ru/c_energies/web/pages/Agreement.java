package ru.c_energies.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Agreement {
    @GetMapping("/agreement")
    public String home(Model model){
        return "pages/agreement";
    }
}
