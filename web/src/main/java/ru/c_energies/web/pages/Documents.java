package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import ru.c_energies.databases.entity.documents.DocumentCreate;
import ru.c_energies.databases.entity.documents.DocumentRow;

import java.sql.SQLException;

@Controller
public class Documents {
    /**
     * Создание файла-документа для определенного обращения
     * @param appealId - id обращения
     * @param name - название файла-документа
     * @return
     * @throws SQLException
     */
    @PostMapping(value = "/documents", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> create(@RequestPart String appealId, @RequestPart String name) throws SQLException {
        int aId = Integer.parseInt(appealId);
        new DocumentCreate(new DocumentRow(
                0, 0, aId, name, "", "", 0
        )).insert();
        return ResponseEntity.ok().build();
    }

}
