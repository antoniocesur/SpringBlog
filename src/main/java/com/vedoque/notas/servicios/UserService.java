package com.vedoque.notas.servicios;


import com.vedoque.notas.dto.UserDto;
import com.vedoque.notas.modelo.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();

    //Métodos nuevos que añado yo para el chat
    List<User> findAll();
    User findById(long id);
    User save(User usuario);
    void delete(User usuario);
    void deleteById(long id);
}
