package com.zap.contadigital.comprovantes.service.impl;

import com.zap.contadigital.comprovantes.dto.ComprovanteRecargaCelularDTO;
import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.service.RecargaCelularService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecargaCelularServiceImpl extends ComprovanteService implements RecargaCelularService {

    public byte[] gerarComprovanteRecargaCelular(ComprovanteRecargaCelularDTO comprovante) throws Exception {
        if (comprovante.getIdTransacao().equals("999"))
            throw new TransacaoNaoLocalizadaException(comprovante.getIdTransacao());

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("transacao", comprovante.getIdTransacao());
        parametros.put("protocolo", comprovante.getProtocolo());
        parametros.put("cliente", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("terminal", "228005");
        parametros.put("agente", "238804");
        parametros.put("autenticacao", "07656");
        parametros.put("nsu", "610761");
        parametros.put("produto", "TIM - SP");
        parametros.put("cpfCnpj", "123.456.789-10");
        parametros.put("telefone", "(11) 99801-1234");
        parametros.put("documento", "12345678");
        parametros.put("data", "22/11/19");
        parametros.put("valor", "R$ 35,00");
        return comprovanteByteArray("COMPROVANTE_RECARGA_CELULAR", parametros);
    }
}
