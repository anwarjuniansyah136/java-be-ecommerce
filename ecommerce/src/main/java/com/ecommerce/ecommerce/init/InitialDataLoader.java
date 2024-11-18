package com.ecommerce.ecommerce.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce.model.Auth;
import com.ecommerce.ecommerce.model.Roles;
import com.ecommerce.ecommerce.model.Sender;
import com.ecommerce.ecommerce.repositories.AuthRepository;
import com.ecommerce.ecommerce.repositories.RolesRepository;
import com.ecommerce.ecommerce.repositories.SenderRepository;

@Component
public class InitialDataLoader implements ApplicationRunner{
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SenderRepository senderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Roles> roles = rolesRepository.findAll();

        if(roles.isEmpty()){
            Roles admin = new Roles(null,"admin");
            Roles users = new Roles(null, "users");
            Roles gudang = new Roles(null,"gudang");
            Roles sender = new Roles(null, "sender");
            rolesRepository.saveAll(List.of(admin,users,gudang,sender));
        }
        Roles admin = rolesRepository.findByRolesName("admin");
        List<Auth> authAdmin = authRepository.findByRoles(admin);
        if(authAdmin.isEmpty()){

            Auth auth = new Auth(null, "Admin", "admin@gmail.com", passwordEncoder.encode("admin123"), admin,null,null);
            authRepository.save(auth);
        }
        Roles gudang = rolesRepository.findByRolesName("gudang");
        List<Auth> authsGudang = authRepository.findByRoles(gudang);
        if(authsGudang.isEmpty()){
            Auth auth = new Auth(null, "Gudang", "gudang@gmail.com", passwordEncoder.encode("gudang123"), gudang,null,null);
            authRepository.save(auth);
        }
        Roles sender = rolesRepository.findByRolesName("sender");
        List<Auth> senderAuths = authRepository.findByRoles(sender);
        if(senderAuths.isEmpty()){
            Auth auth = new Auth(null,"Kurir","kurir@gmail.com",passwordEncoder.encode("kurir123"),sender,null,null);
            authRepository.save(auth);
        }
        if(senderRepository.findAll().isEmpty()){
            Sender sender1 = new Sender(null, "ikmal@gmail.com");
            Sender sender2 = new Sender(null, "anwar@gmail.com");
            senderRepository.saveAll(List.of(sender1,sender2));
        }
    }   
}
