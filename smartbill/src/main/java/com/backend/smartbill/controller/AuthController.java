package com.backend.smartbill.controller;


import com.backend.smartbill.dto.AuthRegisterRequestDto;
import com.backend.smartbill.dto.AuthRequestDto;
import com.backend.smartbill.model.Role;
import com.backend.smartbill.model.UserModel;
import com.backend.smartbill.repository.IUserRepository;
import com.backend.smartbill.service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository iUserRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto) {
        try {

            //1. Session authenticationManager
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequestDto.getUser(), authRequestDto.getPassword()
            ));

            //2. Validar token
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequestDto.getUser());

            //3. Generar token
            String jwt = this.jwtUtilService.generateToken(userDetails);
            System.out.println("Token generado: " + jwt);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRegisterRequestDto authRegisterRequestDto) {
        try {
            // 2. Crear el nuevo usuario
            UserModel newUser = new UserModel();
            newUser.setName(authRegisterRequestDto.getUser());
            newUser.setPassword(passwordEncoder.encode(authRegisterRequestDto.getPassword()));
            newUser.setPhone(authRegisterRequestDto.getPhone());
            newUser.setRole(Role.USER); // O el rol que desees asignar

            // 3. Guardar el usuario en la base de datos
            iUserRepository.save(newUser);

            // 4. Generar token
            String jwt = this.jwtUtilService.generateToken(newUser);

            System.out.println("Usuario registrado: " + newUser.getName());
            return new ResponseEntity<>(jwt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
