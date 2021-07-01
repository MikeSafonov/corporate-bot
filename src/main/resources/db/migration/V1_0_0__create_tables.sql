
CREATE TABLE "public"."employee"
(
    "id"      SERIAL NOT NULL,
    "user_id" varchar(15) COLLATE "pg_catalog"."default",
    "fio"     varchar(200) COLLATE "pg_catalog"."default",
    "phone"   varchar(13) COLLATE "pg_catalog"."default",
    "email"   varchar(254) COLLATE "pg_catalog"."default",
    CONSTRAINT "employee_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "user" UNIQUE ("user_id")
)
;

CREATE TABLE "public"."type_of_message"
(
    "id"           SERIAL NOT NULL,
    "type_message" varchar(30) COLLATE "pg_catalog"."default",
    CONSTRAINT "type_of_message_pkey" PRIMARY KEY ("id")
)
;

CREATE TABLE "public"."message"
(
    "id"              SERIAL NOT NULL,
    "employee_id"     int4,
    "message_text"    text COLLATE "pg_catalog"."default",
    "date_of_message" timestamp(6),
    "type_id"         int4,
    CONSTRAINT "message_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "employee_id_fk" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT "type_id_fk" FOREIGN KEY ("type_id") REFERENCES "public"."type_of_message" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
)
;

CREATE TABLE "public"."type_of_actions"
(
    "id"          SERIAL NOT NULL,
    "type_action" varchar(30) COLLATE "pg_catalog"."default",
    CONSTRAINT "type_of_actions_pkey" PRIMARY KEY ("id")
)
;


CREATE TABLE "public"."actions"
(
    "id"             SERIAL NOT NULL,
    "employee_id"    int4,
    "type_id"        int4,
    "date_of_action" timestamp(6),
    CONSTRAINT "actions_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "employee_id_fk" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT "type_id_fk" FOREIGN KEY ("type_id") REFERENCES "public"."type_of_actions" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
)
;


CREATE TABLE "public"."authorization_code"
(
    "id"                 SERIAL NOT NULL,
    "code"               varchar(6) COLLATE "pg_catalog"."default",
    "employee_id"        int4,
    "user_id"            varchar(15) COLLATE "pg_catalog"."default",
    "date_of_generation" timestamp(6),
    CONSTRAINT "authorization_code_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "employee_id_fk" FOREIGN KEY ("employee_id") REFERENCES "public"."employee" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT "code" UNIQUE ("code"),
    CONSTRAINT "user_id_unique" UNIQUE ("user_id")
)
;



CREATE TABLE "public"."mailing"
(
    "id"              SERIAL NOT NULL,
    "user_id"         varchar(15) COLLATE "pg_catalog"."default",
    "mail_text"       text COLLATE "pg_catalog"."default",
    "date_of_mailing" timestamp(6),
    CONSTRAINT "mailing_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "user_id" UNIQUE ("user_id")
)
;


CREATE TABLE "public"."usefulness"
(
    "answer_yes" int4,
    "answer_no"  int4
)
;
