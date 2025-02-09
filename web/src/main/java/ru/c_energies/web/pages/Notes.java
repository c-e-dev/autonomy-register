package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.c_energies.databases.entity.notes.NoteCreate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Notes {
    @PostMapping(value = "/notes/create/themeId/{id}")
    public ResponseEntity<Object> createForTheme(@PathVariable(value = "id") String themeId, @RequestPart String title, @RequestPart String content) throws SQLException {
        //int tagId = new LabelCreate(Integer.parseInt(appealId), tagName).insert().id();
        int id = new NoteCreate(-1, Integer.parseInt(themeId), title, content).insert().id();
        Map<String, Integer> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/notes/delete")
    public ResponseEntity<Object> delete(@RequestParam Map<String, String> req) throws SQLException {
        //new LabelDelete(Integer.parseInt(req.get("id"))).delete();
        return ResponseEntity.ok().build();
    }
}
