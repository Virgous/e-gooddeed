package com.enigma.egooddeed.controller;

import com.enigma.egooddeed.entity.Admin;
import com.enigma.egooddeed.model.request.AdminRequest;
import com.enigma.egooddeed.model.response.SuccessResponse;
import com.enigma.egooddeed.service.IAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(UrlMappings.ADMIN_URL)
@Validated
public class AdminController {
    @Autowired
    private ModelMapper modelMapper;
    private final IAdminService adminService;


    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity createAdmin(@Valid @RequestBody Admin admin) throws Exception{
        Admin result = adminService.createAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create admin",result));
    }

    @GetMapping
    public ResponseEntity findAll(){
        List<Admin> result = adminService.viewAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Data Found",result));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity delete(@PathVariable("username") String username){
        adminService.delete(username);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Admin by username "+username+" success");
    }

    @PutMapping("/{username}")
    public ResponseEntity updateAdminById(@PathVariable("username") @NotBlank String username, @Valid @RequestBody AdminRequest admin) throws Exception {
        Admin newAdmin = modelMapper.map(admin,Admin.class);
        adminService.update(newAdmin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse<>("Update admin by username " + username + " Success", admin));
    }
}
