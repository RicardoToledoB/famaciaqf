package com.farmacia.v1.service;


import com.farmacia.v1.dto.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO create(UserDTO dto);

    UserDTO update(Integer id, UserDTO dto);

    UserDTO getById(Integer id);

    List<UserDTO> getAll();

    void delete(Integer id);
}