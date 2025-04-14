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
import ru.c_energies.databases.entity.addresses.AddressDublicateSearch;
import ru.c_energies.databases.entity.addresses.AddressRow;
import ru.c_energies.databases.entity.appeals.AppealAddress;
import ru.c_energies.databases.entity.appeals.AppealChanges;
import ru.c_energies.databases.entity.appeals.AppealCreate;
import ru.c_energies.databases.entity.labels.LabelRow;
import ru.c_energies.databases.entity.labels.LabelTable;
import ru.c_energies.databases.entity.themes.ThemesLinkAppeals;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.utils.converters.DigitsToYesNo;
import ru.c_energies.utils.converters.TypeAppealsConvert;
import ru.c_energies.web.models.appeals.AppealsTable;
import ru.c_energies.databases.entity.files.FileRow;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public ResponseEntity<Object> createAppeal(@RequestPart String themeId, @RequestPart String title, @RequestPart String registerTrackNumber,
                                               @RequestPart String dueDate, @RequestPart String getAnsweredable, @RequestPart(required = false) String internalNumber,
                                               @RequestPart(required = false) String recipient, @RequestPart(required = false) String address, @RequestPart String type
                                               ) throws SQLException {
        if(recipient == null) recipient = "";
        if(address == null) address = "";
        if(internalNumber == null) internalNumber = "";
        AppealRow appealRowNewTemp = new AppealRow(0, title, internalNumber, registerTrackNumber, "", dueDate+":00Z", getAnsweredable, type);
        AppealCreate appealCreate = new AppealCreate(Integer.parseInt(themeId), appealRowNewTemp);
        appealCreate.insert();
        AddressDublicateSearch addressDublicateSearch = new AddressDublicateSearch(recipient, address);
        AddressCreate addressCreate = new AddressCreate(recipient, address);
        if(!addressDublicateSearch.foundable()){
            addressCreate.insert();
            new AppealAddress(appealCreate.id()).address(addressCreate.id());
        }else {
            new AppealAddress(appealCreate.id()).address(addressDublicateSearch.id());
        }
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
        int ID = Integer.parseInt(id);
        Query q = new Query(new SqliteDataSource(), String.format("select * from appeals where id = %d", ID));
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        if(list.size() == 0){
            return "pages/appealNotFound";
        }
        AppealRow appealRow = list.get(0);
        List<FileRow> listFileRow = new Files().listFilesSended(id);
        List<List<FileRow>> lists = lists(listFileRow);
        List<FileRow> listFilesAnswered = new Files().listFilesAnswered(id);
        List<List<FileRow>> listsFileAnswered = lists(listFilesAnswered);
        AddressRow addressRow = new AppealAddress(ID).address();
        List<LabelRow> labelRows = new LabelTable(ID).list();
        ThemesLinkAppeals themesLinkAppeals = new ThemesLinkAppeals();
        model.addAttribute("appeal", appealRow);
        model.addAttribute("answered", new DigitsToYesNo(0).reverse(list.get(0).answered()));
        model.addAttribute("lists", lists);
        model.addAttribute("listFiles", listFileRow);
        model.addAttribute("listFileAnswered", listFilesAnswered);
        model.addAttribute("listsFileAnswered", listsFileAnswered);
        model.addAttribute("address", addressRow);
        model.addAttribute("labels", labelRows);
        model.addAttribute("theme", themesLinkAppeals.theme(ID));
        model.addAttribute("type", new TypeAppealsConvert("").reverse(appealRow.type()));
        return "pages/appeal";
    }

    /**
     * Изменение обращения
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping(value = "/document/appeals/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> editAppeal(@PathVariable("id") String id, @RequestPart String internalNumber,
                                             @RequestPart String registerTrackNumber, @RequestPart String dueDate, @RequestPart String getAnsweredable,
                                             @RequestPart(required = false) String recipient, @RequestPart(required = false) String address, @RequestPart String type ) throws SQLException {
        if(recipient == null) recipient = "";
        if(address == null) address = "";
        Query q = new Query(new SqliteDataSource(), String.format("select * from appeals where id = %d", Integer.parseInt(id)));
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        AppealRow appealRowOld = list.get(0);
        AppealRow appealRowNew = new AppealRow(
                appealRowOld.id(), appealRowOld.title(), internalNumber,
                registerTrackNumber, appealRowOld.createDate(), dueDate+":00Z", getAnsweredable, type
        );
        AppealChanges appealChanges = new AppealChanges(appealRowNew);
        appealChanges.update();
        AddressDublicateSearch addressDublicateSearch = new AddressDublicateSearch(recipient, address);
        AddressCreate addressCreate = new AddressCreate(recipient, address);
        if(!addressDublicateSearch.foundable()){
            addressCreate.insert();
            new AppealAddress(appealRowNew.id()).address(addressCreate.id());
        }else {
            new AppealAddress(appealRowNew.id()).address(addressDublicateSearch.id());
        }
        return ResponseEntity.ok().build();
    }

    private List<List<FileRow>> lists(List<FileRow> fileRowList){
        List<List<FileRow>> lists = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(FileRow dr : fileRowList){
            List<FileRow> flr = new ArrayList<>();
            flr.add(dr);
            if(i % 4 == 0){
                lists.add(flr);
                j++;
            }else{
                lists.get(j-1).add(dr);
            }
            i++;
        }
        return lists;
    }
}
