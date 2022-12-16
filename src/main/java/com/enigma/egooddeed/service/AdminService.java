package com.enigma.egooddeed.service;

import com.enigma.egooddeed.entity.Admin;
import com.enigma.egooddeed.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService implements IAdminService{

    @Autowired
    private IAdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {

        adminRepository.create(admin.getUsername(),admin.getPassword());
        return admin;
    }

    @Override
    public List<Admin> viewAll() {
        return adminRepository.getAll();
    }

    @Override
    public void delete(String username) {
        adminRepository.deleteById(username);
    }

    @Override
    public void update(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findById(username).get();
    }
}
