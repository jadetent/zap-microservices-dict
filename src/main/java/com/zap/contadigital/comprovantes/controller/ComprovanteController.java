package com.zap.contadigital.comprovantes.controller;


import com.zap.contadigital.auth.JwtSession;
import com.zap.contadigital.model.auth.Role;
import com.zap.contadigital.model.auth.Roles;
import com.zap.contadigital.model.auth.Usuario;
import com.zap.contadigital.repository.RoleRepository;
import com.zap.contadigital.repository.UsuarioRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/comprovantes")
public class ComprovanteController {
    @GetMapping("/text")
    public @ResponseBody String getText() {
        return "Hello world";
    }
    @GetMapping(value = "/pdf")
    public @ResponseBody byte[] getPdf() throws IOException {
        InputStream in = new FileInputStream(new File("/dev/comprovante.pdf"));
        //.getResourceAsStream("/dev/comprovante.pdf");
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/pdf-media",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] getPdfWithMediaType() throws IOException {
        InputStream in = new FileInputStream(new File("/dev/comprovante.pdf"));
        return IOUtils.toByteArray(in);
    }

}
