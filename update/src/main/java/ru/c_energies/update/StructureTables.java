package ru.c_energies.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Структура таблиц
 */
public interface StructureTables {
    Map<String, List<TableField>> DB = new HashMap<>(){{
       put("addresses", new ArrayList<>(){{
           add(new TableField("id", "INTEGER", 1, 1));
           add(new TableField("recipient", "TEXT", 0, 0));
           add(new TableField("address", "TEXT", 0, 0));
           add(new TableField("actually", "INTEGER", 1, 0));
       }});
        put("appeal_address", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 1, 1));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
            add(new TableField("address_id", "INTEGER", 0, 0));
        }});
        put("appeal_type", new ArrayList<>(){{
            add(new TableField("type", "INTEGER", 0, 0));
            add(new TableField("name", "TEXT", 0, 0));
        }});
        put("appeals", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 1, 1));
            add(new TableField("title", "TEXT", 0, 0));
            add(new TableField("internal_number", "TEXT", 0, 0));
            add(new TableField("register_track_number", "TEXT", 0, 0));
            add(new TableField("create_date", "INTEGER", 0, 0));
            add(new TableField("due_date", "INTEGER", 0, 0));
            add(new TableField("answered", "INTEGER", 0, 0));
            add(new TableField("type", "TEXT", 0, 0)); // тип обращения - inbound, outbound, none
        }});
        put("files", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 0, 1));
            add(new TableField("name", "TEXT", 0, 0));
            add(new TableField("content", "BLOB", 0, 0));
            add(new TableField("extension", "TEXT", 0, 0));
            add(new TableField("size", "INTEGER", 0, 0));
            add(new TableField("create_date", "INTEGER", 0, 0));
            add(new TableField("content_type", "TEXT", 0, 0));
        }});
        put("files_appeal", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 0, 1));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
            add(new TableField("file_id", "INTEGER", 0, 0));
            add(new TableField("appeal_type_id", "INTEGER", 0, 0));
        }});
        put("labels", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 1, 1));
            add(new TableField("name", "TEXT", 0, 0));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
        }});
        put("notes", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 1, 1));
            add(new TableField("content", "TEXT", 0, 0));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
            add(new TableField("theme_id", "INTEGER", 0, 0));
            add(new TableField("create_date", "INTEGER", 0, 0));
            add(new TableField("title", "TEXT", 0, 0));
        }});
        put("secured_partyes", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 1, 1));
            add(new TableField("amount", "INTEGER", 0, 0));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
            add(new TableField("name", "TEXT", 0, 0));
            add(new TableField("create_date", "INTEGER", 0, 0));
            add(new TableField("apply_date", "INTEGER", 0, 0));
            add(new TableField("used", "INTEGER", 0, 0));
        }});
        put("settings", new ArrayList<>(){{
            add(new TableField("name", "TEXT", 0, 0));
            add(new TableField("value", "TEXT", 0, 0));
        }});
        put("themes", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 0, 1));
            add(new TableField("title", "TEXT", 0, 0));
            add(new TableField("create_date", "INTEGER", 0, 0));
            add(new TableField("decision_date", "INTEGER", 0, 0));
            add(new TableField("decision_status", "INTEGER", 0, 0));
            add(new TableField("description", "TEXT", 0, 0));
        }});
        put("themes_link_appeals", new ArrayList<>(){{
            add(new TableField("theme_id", "INTEGER", 0, 0));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
        }});
        put("notifications", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 0, 1));
            add(new TableField("time", "INTEGER", 0, 0));
            add(new TableField("content", "TEXT", 0, 0));
            add(new TableField("is_read", "INTEGER", 0, 0));
        }});
        put("version", new ArrayList<>(){{
            add(new TableField("value", "TEXT", 0, 0));
        }});
        put("appeal_from_appeal", new ArrayList<>(){{
            add(new TableField("id", "INTEGER", 0, 1));
            add(new TableField("appeal_id", "INTEGER", 0, 0));
            add(new TableField("parent_appeal_id", "INTEGER", 0, 0));
            add(new TableField("theme_id", "INTEGER", 0, 0));
        }});
    }};
}
