package ru.c_energies.web.pages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.c_energies.databases.entity.notes.NoteCreate;
import ru.c_energies.databases.entity.notes.NoteDelete;

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

    /**
     * Удаление заметок
     * @param noteId - порядковый номер заметки
     * @return
     * @throws SQLException
     */
    @DeleteMapping(value = "/notes/{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") String noteId) throws SQLException {
        new NoteDelete(Integer.parseInt(noteId)).delete();
        return ResponseEntity.ok().build();
    }
}
