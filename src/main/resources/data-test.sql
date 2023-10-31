--языки
insert into public.lang_code(name, title)
VALUES ('java', 'Java');

insert into public.lang_code(name, title)
VALUES ('python', 'Python');

--пользователи
insert into "user"(name, login, password, email, type_user)
VALUES ('Сергей Ветров', 'veter', '$2a$12$Ge3C8bfSSQbimNb1YqbUce9cm8jT8wJPr2IUR0hvZP.5LZPqfEq3K', 'veter@q.ru', 'ADMIN');

insert into "user"(name, login, password, email, type_user)
VALUES ('user 1', 'user 1', '$2a$12$Ge3C8bfSSQbimNb1YqbUce9cm8jT8wJPr2IUR0hvZP.5LZPqfEq3K', 'user1@q.ru', 'USER');

--посты
insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('code 1','title 1','description 1','2023-10-01 10:00:00', '2023-10-01 10:00:00', 1, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('code 2','title 2','description 2','2023-10-01 10:00:00', '2023-10-02 10:00:00', 1, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('code 3','title 3','description 3','2023-10-01 10:00:00', '2023-10-03 10:00:00', 1, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('code 4','title 4','description 4','2023-10-01 10:00:00', '2023-10-04 10:00:00', 1, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('code 5','title 5','description 5','2023-10-01 10:00:00', '2023-10-05 10:00:00', 1, 1);

--комментариии
insert into public.comment_post(text,date_create,user_id, post_id)
VALUES ('text 1', '2023-10-01 11:00:00', 1, 1);

insert into public.comment_post(text,date_create,user_id, post_id)
VALUES ('text 2', '2023-10-02 11:00:00', 1, 1);

insert into public.comment_post(text,date_create,user_id, post_id)
VALUES ('text 3', '2023-10-03 11:00:00', 1, 1);

--оценки
INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (1, 1, 3);

INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (2, 1, 5);

INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (1, 2, 3);

