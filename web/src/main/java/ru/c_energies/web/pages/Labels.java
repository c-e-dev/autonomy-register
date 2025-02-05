package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.c_energies.databases.entity.labels.LabelCreate;
import ru.c_energies.databases.entity.labels.LabelDelete;

import java.sql.SQLException;
import java.util.Map;

@Controller
public class Labels {
    @PostMapping(value = "/labels/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> create(@RequestPart String appealId, @RequestPart String tagName) throws SQLException {
        int tagId = new LabelCreate(Integer.parseInt(appealId), tagName).insert().id();
        return ResponseEntity.ok().body(tagId);
    }

    @GetMapping(value = "/labels/delete")
    public ResponseEntity<Object> delete(@RequestParam Map<String, String> req) throws SQLException {
        new LabelDelete(Integer.parseInt(req.get("id"))).delete();
        return ResponseEntity.ok().build();
    }
}
