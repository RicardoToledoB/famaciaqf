package com.farmacia.v1.service;

import com.farmacia.v1.dto.UserRoleDTO;

import java.util.List;

public interface IUserRoleService {

    UserRoleDTO create(UserRoleDTO dto);

    UserRoleDTO update(Integer id, UserRoleDTO dto);

    UserRoleDTO getById(Integer id);

    List<UserRoleDTO> getAll();
}

