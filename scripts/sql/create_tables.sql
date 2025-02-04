CREATE TABLE appeals (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	title TEXT,
	internal_number TEXT,
	register_track_number TEXT,
	create_date INTEGER
, due_date INTEGER, answered INTEGER);

CREATE TABLE "files" (
	"id"	INTEGER,
	"name"	TEXT,
	"content"	BLOB, extension TEXT, "size" INTEGER, create_date INTEGER, "content_type" TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "settings" (
	"name"	TEXT,
	"value"	TEXT
);

CREATE TABLE themes (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	title TEXT,
	create_date INTEGER
, decision_date INTEGER, decision_status INTEGER, description TEXT);

CREATE TABLE themes_link_appeals (
	theme_id INTEGER,
	appeal_id INTEGER
);

CREATE TABLE files_appeal (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	appeal_id INTEGER,
	file_id INTEGER
, appeal_type_id INTEGER);

CREATE TABLE appeal_type (
	"type" INTEGER,
	name TEXT
);

CREATE TABLE addresses (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	recipient TEXT,
	address TEXT,
	actually INTEGER DEFAULT 1 NOT NULL
);

CREATE TABLE appeal_address (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	appeal_id INTEGER,
	address_id INTEGER
);

CREATE TABLE labels (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	name TEXT,
	appeal_id INTEGER
);