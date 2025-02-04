package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import ru.c_energies.databases.entity.labels.LabelCreate;

import java.sql.SQLException;

@Controller
public class Labels {
    @PostMapping(value = "/labels/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> createAppeal(@RequestPart String appealId, @RequestPart String tagName) throws SQLException {
        new LabelCreate(Integer.parseInt(appealId), tagName).insert();
        return ResponseEntity.ok().build();
    }
}
