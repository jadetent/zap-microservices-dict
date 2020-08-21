package com.zap.contadigital.comprovantes.service.impl;

import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.comprovantes.model.Comprovante;
import com.zap.contadigital.comprovantes.repository.ComprovanteRepository;
import com.zap.contadigital.comprovantes.service.IComprovanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprovanteService implements IComprovanteService {

    @Autowired
    private ComprovanteRepository repository;

    @Override
    public ComprovanteResponse listarPagamentosRealizados(ComprovanteRequest comprovanteRequest) throws Exception {

        List<Comprovante> comprovantes = repository.buscaUltimosDezComprovantes(comprovanteRequest.getTelefone());

        return ComprovanteResponse.builder()
                .codigoPessoa(comprovanteRequest.getCodigoPessoa())
                .telefone(comprovanteRequest.getTelefone())
                .data(comprovantes)
                .build();
    }
}
