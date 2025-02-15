package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.addresses.AddressRow;
import ru.c_energies.databases.entity.appeals.AppealAddress;
import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.databases.entity.files.FileRow;
import ru.c_energies.databases.entity.labels.LabelRow;
import ru.c_energies.databases.entity.labels.LabelTable;
import ru.c_energies.databases.entity.secured_party.SecuredPartyCreate;
import ru.c_energies.databases.entity.secured_party.SecuredPartyRow;
import ru.c_energies.databases.entity.secured_party.SecuredPartyTable;
import ru.c_energies.databases.entity.themes.ThemesLinkAppeals;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.utils.converters.DigitsToYesNo;
import ru.c_energies.web.models.appeals.AppealsTable;

import java.sql.SQLException;
import java.util.List;

@Controller
public class SecuredPartyes {
    @GetMapping(value = "/document/secured-party")
    public String list(Model model) throws SQLException {
        List<SecuredPartyRow> securedPartyRows = new SecuredPartyTable(0, 0).list();
        model.addAttribute("securedPartyRows", securedPartyRows);
        return "pages/secured-partyes";
    }

    @PostMapping(value = "/document/secured-party", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> create(@RequestPart String name, @RequestPart String amount) throws SQLException {
        SecuredPartyRow securedPartyRow = new SecuredPartyRow(0, Integer.parseInt(amount), 0, name, "", "", 0);
        new SecuredPartyCreate(securedPartyRow).insert();
        return ResponseEntity.ok().build();
    }

    /**
     * Получаем содержимое требования
     * @param model
     * @param id
     * @return
     * @throws SQLException
     */
    @GetMapping(value = "/document/secured-party/{id}")
    public String fullAppealById(Model model, @PathVariable("id") String id) throws SQLException {
        int ID = Integer.parseInt(id);
        SecuredPartyTable securedPartyTable = new SecuredPartyTable(ID, 0);
        List<SecuredPartyRow> securedPartyRows = securedPartyTable.list();
        if(securedPartyRows.size() == 0){
            return "pages/appealNotFound";
        }
        model.addAttribute("securedParty", securedPartyRows.get(0));
        return "pages/secured-party";
    }
}
