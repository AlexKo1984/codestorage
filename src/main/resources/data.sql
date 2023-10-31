--языки
insert into public.lang_code(name, title)
VALUES ('java', 'Java');

insert into public.lang_code(name, title)
VALUES ('python', 'Python');

--пользователи
--Для всех пароль 123
insert into public.user(name, login, password, email, type_user)
VALUES ('Сергей Ветров', 'veter', '$2a$12$Ge3C8bfSSQbimNb1YqbUce9cm8jT8wJPr2IUR0hvZP.5LZPqfEq3K', 'veter@q.ru', 'ADMIN');

insert into public.user(name, login, password, email, type_user)
VALUES ('Алена Павлова', 'Pavlova', '$2a$12$Ge3C8bfSSQbimNb1YqbUce9cm8jT8wJPr2IUR0hvZP.5LZPqfEq3K', 'Pavlova@q.ru', 'USER');

insert into public.user(name, login, password, email, type_user)
VALUES ('Михаил Фатьянов', 'Miha', '$2a$12$Ge3C8bfSSQbimNb1YqbUce9cm8jT8wJPr2IUR0hvZP.5LZPqfEq3K', 'Miha@q.ru', 'USER');

--посты
insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('List<String> list = new ArrayList<>();',
'ArrayList Добавим элемент',
'ArrayList Добавим элемент',
'2023-10-01 10:00:00', '2023-10-29  10:00:00', 1, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('for (String item: list) {
     System.out.println(item);
};',
'ArrayList Перебор элементов',
'ArrayList Перебор элементов for each',
'2023-10-01 11:00:00', '2023-10-30 11:00:00', 1, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('Map<String, String> map = new HashMap<>();
Map<String, String> map1 = Map.of("0","-", "1", "A", "2", "B");',
'Map Добавление элемнтов',
'Добавление элемнтов',
'2023-10-01 12:00:00', '2023-10-02 11:00:00', 2, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('for (Map.Entry<String, String> entry: map.entrySet()) {
     System.out.println("Ключ: " + entry.getKey() + " Значение: " + entry.getValue());
}',
'Map. Обход коллекции ключ/значение',
'Обход коллекции ключ/значение',
'2023-10-01 12:00:00', '2023-10-03 11:00:00', 2, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('for (String key: map.keySet()) {
	 System.out.println("Ключ: " + key);
}',
'Map. Обход коллекции ключей',
'Обход коллекции ключей',
'2023-10-01 12:00:00', '2023-10-03 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('for (String value: map.values()) {
      System.out.println("Значение: " + value);
}',
'Map. Обход коллекции значениц',
'Обход коллекции значениц',
'2023-10-04 12:00:00', '2023-10-04 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('//Создание пустого
        Optional<String> o = Optional.empty();

        o = Optional.of("qwe");

        //Ошибка создания NullPointerException
        o = Optional.of(null);

        //Можно вставить null
        o = Optional.ofNullable(null);',
'Optional. Создание',
'Создание',
'2023-10-05 12:00:00', '2023-10-05 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('      Optional<String> o = Optional.of("qwe");

        String s = o.get();

        o = Optional.ofNullable(null);
        //Если пусто то другое значение
        s = o.orElse("asd");
        //Если пусто то получит из метода
        s = o.orElseGet(Program::calcOther);
        //Если пусто то вызов исключения
        s = o.orElseThrow(()-> new IllegalStateException());

    public static String calcOther(){
        return "zxc";
    }',
'Optional. Расчехление',
'Расчехление',
'2023-10-05 12:00:00', '2023-10-05 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('        Optional<String> o = Optional.of("qwe");
        int length = o.map(s->s.length()).get();
        System.out.println("length: " + length);

        o = Optional.ofNullable(null);
        length = o.map(s->s.length()).orElse(0);
        System.out.println("length: " + length);',
'Optional. Функции стрима',
'Функции стрима',
'2023-10-05 12:00:00', '2023-10-05 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('Stream<Integer> stream = Stream.of(1,2,3,4,5);
        stream.filter(i-> 0 < i % 2)
                .forEach(System.out::println);',
'Stream API.filter',
'Фильтр стрима',
'2023-10-05 12:00:00', '2023-10-06 11:00:00', 2, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('List list  = Collections.emptyList();
Set set = Collections.emptySet();
Map map = Collections.emptyMap();',
'Пустые коллекции',
'Пустые коллекции',
'2023-10-05 12:00:00', '2023-10-07 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('List<String> list = Arrays.asList("A", "B", "C");
String delim = "-";

String res = String.join(delim, list);',
'String.join',
'String.join',
'2023-10-05 12:00:00', '2023-10-07 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('List<Integer> list = Arrays.asList(1, 2, 3);
String delim = "-";

String res = list.stream().map(Object::toString).collect(Collectors.joining(delim));',
'stream join',
'join',
'2023-10-08 12:00:00', '2023-10-08 11:00:00', 3, 1);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('language = "english"
if language == "english":
    print("Hello")
print("End")',
'Цикл for',
'Этот цикл пробегается по набору значений, помещает каждое значение в переменную, и затем в цикле мы можем с этой переменной производить различные действия',
'2023-10-01 10:00:00', '2023-10-29  10:00:00', 1, 2);

insert into public.post(code, title, description, date_create, date_change, user_id, lang_code_id)
VALUES ('language = "english"
if language == "english":
    print("Hello")
print("End")',
'Условная конструкция if',
'Условная конструкция if',
'2023-10-01 10:00:00', '2023-10-29  10:00:00', 1, 2);

--комментариии
insert into public.comment_post(text,date_create,user_id, post_id)
VALUES ('То, что надо.
Давно искал',
'2023-10-01 11:00:00', 2, 2);

insert into public.comment_post(text,date_create,user_id, post_id)
VALUES ('Круто',
'2023-10-01 11:00:00', 3, 2);

--оценки
INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (1,1, 3);

INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (2, 1, 4);

INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (3, 1, 5);

INSERT INTO public.grade_post(user_id,post_id, value)
VALUES (1, 2, 5);

