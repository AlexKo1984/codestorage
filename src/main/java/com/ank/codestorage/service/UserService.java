package com.ank.codestorage.service;
import com.ank.codestorage.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> findAll();

    /**
     * Постраничный вывод
     * @param pageNumber номер страницы
     * @param pageSize размер страницы
     * @return страница
     */
    Page<User> findAll(int pageNumber, int pageSize);

    User findById(Integer id);

    User create(User user);

    User update(Integer id, User userForUpdate);

    void delete(Integer id);

    User findByLogin(String login);
}
