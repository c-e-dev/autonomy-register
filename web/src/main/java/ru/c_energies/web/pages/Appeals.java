package ru.c_energies.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.c_energies.web.models.appeals.AppealsTable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Appeals {
    @GetMapping(value = "/document/appeals")
    public String request(Model model){
        List<AppealsTable> list = new ArrayList<>(){{
            add(new AppealsTable(1, "Волеизъявление 1", "202501201100", "8800325464564"));
            add(new AppealsTable(2, "Извещение 2", "202501201200", "8800677754564"));
            add(new AppealsTable(3, "Запрос 3", "202501231100", "88003254656456"));
            add(new AppealsTable(4, "Благодарность 4", "202501251600", "8800364987987"));
        }};
        model.addAttribute("list", list);
        return "pages/appeals";
    }

    @GetMapping(value = "/document/appeals/<id>")
    public String fullAppealById(){
        return "pages/appeals";
    }
}
