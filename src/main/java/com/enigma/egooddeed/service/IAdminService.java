package com.enigma.egooddeed.service;

import com.enigma.egooddeed.entity.Admin;

import java.util.List;

public interface IAdminService {
    Admin createAdmin(Admin admin);

    List<Admin> viewAll();

    void delete(String username);

    void update(Admin admin);

    Admin findByUsername(String username);
}
