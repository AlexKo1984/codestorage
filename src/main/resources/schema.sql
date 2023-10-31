
-- DDL Generated from https:/databasediagram.com
DROP TABLE IF EXISTS comment_post;
DROP TABLE IF EXISTS grade_post;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS public.user CASCADE;
DROP TABLE IF EXISTS lang_code;


--Язык кода: напр java, python
CREATE TABLE IF NOT EXISTS lang_code (
  id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name varchar(30) NOT NULL UNIQUE,
  title varchar(30) NOT NULL
);
--Пользователеи
CREATE TABLE IF NOT EXISTS "user" (
  id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name varchar(255) NOT NULL,
  --Добавил
  login varchar(255) NOT NULL UNIQUE,
  password varchar(60) NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  type_user varchar(30) NOT NULL
);
--Посты с кодом
CREATE TABLE IF NOT EXISTS post (
  id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  code text NOT NULL,
  title varchar(100) NOT NULL,
  description varchar(500) NOT NULL,
  date_create timestamp NOT NULL,
  date_change timestamp NOT NULL,
  user_id int NOT NULL REFERENCES "user"(id),
  lang_code_id int NOT NULL REFERENCES lang_code(id) ON DELETE CASCADE
);
--оценки постов
CREATE TABLE IF NOT EXISTS grade_post (
  id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  user_id int REFERENCES "user"(id) ON DELETE CASCADE,
  post_id int REFERENCES post(id) ON DELETE CASCADE,
  value int NOT NULL,
  --Для уникальности оценки поста
  UNIQUE (user_id, post_id)
);
--комментарии постов
CREATE TABLE IF NOT EXISTS comment_post (
  id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  text text NOT NULL,
  date_create timestamp NOT NULL,
  user_id int REFERENCES "user"(id) ON DELETE CASCADE,
  post_id int REFERENCES post(id) ON DELETE CASCADE
);

