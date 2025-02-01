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
import ru.c_energies.databases.entity.addresses.AddressCreate;
import ru.c_energies.databases.entity.appeals.AppealAddress;
import ru.c_energies.databases.entity.appeals.AppealChanges;
import ru.c_energies.databases.entity.appeals.AppealCreate;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.utils.converters.DigitsToYesNo;
import ru.c_energies.web.models.appeals.AppealsTable;
import ru.c_energies.databases.entity.files.FileRow;

import java.sql.SQLException;
import java.util.List;

@Controller
public class Appeals {
    @GetMapping(value = "/document/appeals")
    public String list(Model model) throws SQLException {
        Query q = new Query(new SqliteDataSource(), "select * from appeals");
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        model.addAttribute("list", list);
        return "pages/appeals";
    }

    @PostMapping(value = "/document/appeals", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> createAppeal(Model model, @RequestPart String themeId, @RequestPart String title, @RequestPart String registerTrackNumber,
                                               @RequestPart String dueDate, @RequestPart String getAnsweredable,
                                               @RequestPart String recipient, @RequestPart String address
                                               ) throws SQLException {
        AppealRow appealRowNewTemp = new AppealRow(0, title, "", registerTrackNumber, "", dueDate+":00Z", getAnsweredable);
        AppealCreate appealCreate = new AppealCreate(Integer.parseInt(themeId), appealRowNewTemp);
        appealCreate.insert();
        AddressCreate addressCreate = new AddressCreate(recipient, address).insert();
        new AppealAddress(appealCreate.id()).address(addressCreate.id());
        return ResponseEntity.ok().build();
    }

    /**
     * Получаем содержимое обращения
     * @param model
     * @param id
     * @return
     * @throws SQLException
     */
    @GetMapping(value = "/document/appeals/{id}")
    public String fullAppealById(Model model, @PathVariable("id") String id) throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format("select * from appeals where id = %d", Integer.parseInt(id)));
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        List<FileRow> listFileRow = new Files().listFilesSended(id);
        List<FileRow> listFilesAnswered = new Files().listFilesAnswered(id);
        model.addAttribute("appeal", list.get(0));
        model.addAttribute("answered", new DigitsToYesNo(0).reverse(list.get(0).answered()));
        model.addAttribute("listFiles", listFileRow);
        model.addAttribute("listFileAnswered", listFilesAnswered);
        return "pages/appeal";
    }

    /**
     * Изменение обращения
     * @param model
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping(value = "/document/appeals/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> editAppeal(Model model, @PathVariable("id") String id,
                                             @RequestPart String registerTrackNumber, @RequestPart String dueDate, @RequestPart String getAnsweredable ) throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format("select * from appeals where id = %d", Integer.parseInt(id)));
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        AppealRow appealRowOld = list.get(0);
        AppealRow appealRowNew = new AppealRow(
                appealRowOld.id(), appealRowOld.title(), appealRowOld.internalNumber(),
                registerTrackNumber, appealRowOld.createDate(), dueDate+":00Z", getAnsweredable
        );
        AppealChanges appealChanges = new AppealChanges(appealRowNew);
        appealChanges.update();
        return ResponseEntity.ok().build();
    }
}
