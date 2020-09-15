package com.zap.contadigital.dict.controller.impl;

import com.zap.contadigital.dict.controller.IDictController;
import com.zap.contadigital.dict.dto.request.PreCadastroRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/dict")
public class DictController implements IDictController {

    @Override
    @PostMapping(value = "/pre-cadastro", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> preCadastro(
            @RequestBody @Valid PreCadastroRequest preCadastroRequest) {

        String s = "";

        return new ResponseEntity(s, HttpStatus.OK);
    }
}
