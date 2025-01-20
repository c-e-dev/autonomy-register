package ru.c_energies.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.c_energies.web.models.themes.ThemeTable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Themes {
    @GetMapping(value = "/document/themes")
    public String themes(Model model){
        List<ThemeTable> listThemes = new ArrayList<>(){{
            add(new ThemeTable(1, "sdfgdlfjgahlfjg"));
            add(new ThemeTable(2, "sfdhsthjrtj"));
            add(new ThemeTable(3, "sdfgdlfjgfgahlfjg"));
            add(new ThemeTable(4, "jet7ik4tyjtyjtyjkedtjkety6"));
        }};
        model.addAttribute("listThemes", listThemes);
        return "pages/themes";
    }

    @GetMapping(value = "/document/themes/{id}")
    public String fullThemeById(Model model, @PathVariable("id") String id){
        return "pages/themeById";
    }
}
