--
-- Table structure for table "author"
--

DROP TABLE IF EXISTS "author";
CREATE TABLE "author" (
                          "id" bigint(20) NOT NULL PRIMARY KEY ,
                          "first_name" varchar(100) NOT NULL,
                          "last_name" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "photo"
--

DROP TABLE IF EXISTS "photo";
CREATE TABLE "photo" (
                         "id" bigint(20) NOT NULL PRIMARY KEY ,
                         "extension" varchar(10) NOT NULL,
                         "name" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "library"
--

DROP TABLE IF EXISTS "library";
CREATE TABLE "library" (
                           "id" bigint(20) NOT NULL PRIMARY KEY ,
                           "name" varchar(200) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "publisher"
--

DROP TABLE IF EXISTS "publisher";
CREATE TABLE "publisher" (
                             "id" bigint(20) NOT NULL PRIMARY KEY ,
                             "name" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "role"
--

DROP TABLE IF EXISTS "role";
CREATE TABLE "role" (
                        "id" bigint(20) NOT NULL PRIMARY KEY ,
                        "name" varchar(20) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "user"
--

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
                        "id" bigint(20) NOT NULL PRIMARY KEY ,
                        "email" varchar(100) NOT NULL,
                        "password" varchar(255) NOT NULL,
                        "username" varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `waiting_list`
--

CREATE TABLE "waiting_list" (
                                "id" bigint(20) NOT NULL PRIMARY KEY ,
                                "size" bigint(20) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `user_waiting_list`
--

CREATE TABLE "user_waiting_list" (
                                     "id" bigint(20) NOT NULL PRIMARY KEY,
                                     "date" date DEFAULT NULL,
                                     "user_position" bigint(20) DEFAULT NULL,
                                     "user_id" bigint(20) NOT NULL,
                                     "waiting_list_id" bigint(20) NOT NULL,
                                     FOREIGN KEY ("waiting_list_id") REFERENCES "waiting_list" ("id"),
                                     FOREIGN KEY ("user_id") REFERENCES "user" ("id")
);

-- --------------------------------------------------------


--
-- Table structure for table "document"
--

DROP TABLE IF EXISTS "document";
CREATE TABLE "document" (
                            "id" bigint(20) NOT NULL PRIMARY KEY ,
                            "category" varchar(20) NOT NULL,
                            "description" longtext NOT NULL,
                            "isbn" varchar(20) NOT NULL,
                            "publication_date" date NOT NULL,
                            "title" varchar(250) NOT NULL,
                            "type" varchar(10) NOT NULL,
                            "author_id" bigint(20) NOT NULL,
                            "photo_id" bigint(20) DEFAULT NULL,
                            "publisher_id" bigint(20) DEFAULT NULL,
                            "waiting_list_id" bigint(20) DEFAULT NULL,
                            FOREIGN KEY ("photo_id") REFERENCES "photo" ("id"),
                            FOREIGN KEY ("publisher_id") REFERENCES "publisher" ("id"),
                            FOREIGN KEY ("author_id") REFERENCES "author" ("id"),
                            FOREIGN KEY ("waiting_list_id") REFERENCES "waiting_list" ("id")
);

-- --------------------------------------------------------

--
-- Table structure for table "exemplar"
--

DROP TABLE IF EXISTS "exemplar";
CREATE TABLE "exemplar" (
                            "id" bigint(20) NOT NULL PRIMARY KEY ,
                            "reference" varchar(45) NOT NULL,
                            "document_id" bigint(20) NOT NULL,
                            "library_id" bigint(20) NOT NULL,
                            FOREIGN KEY ("library_id") REFERENCES "library" ("id"),
                            FOREIGN KEY ("document_id") REFERENCES "document" ("id")
);

-- --------------------------------------------------------

--
-- Table structure for table "hibernate_sequence"
--

DROP TABLE IF EXISTS "hibernate_sequence";
CREATE TABLE "hibernate_sequence" (
    "next_val" bigint(20) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "loan"
--

DROP TABLE IF EXISTS "loan";
CREATE TABLE "loan" (
                        "id" bigint(20) NOT NULL PRIMARY KEY ,
                        "end_date" date DEFAULT NULL,
                        "renewed" bit(1) NOT NULL,
                        "start_date" date DEFAULT NULL,
                        "state" varchar(255) DEFAULT NULL,
                        "exemplar_id" bigint(20) NOT NULL,
                        "user_id" bigint(20) NOT NULL,
                        FOREIGN KEY ("exemplar_id") REFERENCES "exemplar" ("id"),
                        FOREIGN KEY ("user_id") REFERENCES "user" ("id")
);

-- --------------------------------------------------------

--
-- Table structure for table "user_role"
--

DROP TABLE IF EXISTS "user_role";
CREATE TABLE "user_role" (
                             "user_id" bigint(20) NOT NULL,
                             "role_id" bigint(20) NOT NULL,
                             PRIMARY KEY ("user_id", "role_id"),
                             FOREIGN KEY ("user_id") REFERENCES "user" ("id"),
                             FOREIGN KEY ("role_id") REFERENCES "role" ("id")
);
