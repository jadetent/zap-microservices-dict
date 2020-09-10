package com.zap.contadigital.comprovantes.service.impl;

import com.zap.contadigital.comprovantes.client.UploadAwsClient;
import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.request.ImagemRequest;
import com.zap.contadigital.comprovantes.dto.request.UploadClientRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.comprovantes.dto.response.ImagemResponse;
import com.zap.contadigital.comprovantes.exception.ProtocoloNaoEncontradoException;
import com.zap.contadigital.comprovantes.service.IComprovanteService;
import com.zap.contadigital.model.recibos.ComprovanteModel;
import com.zap.contadigital.repository.comprovantes.ComprovantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ComprovanteService implements IComprovanteService {

    @Autowired
    private ComprovantesRepository repository;

    @Autowired
    private UploadAwsClient client;

    @Override
    public ComprovanteResponse listarPagamentosRealizados(ComprovanteRequest comprovanteRequest) throws Exception {

        List<ComprovanteModel> comprovantes = repository.buscaUltimosDezComprovantes(comprovanteRequest.getTelefone());

        return ComprovanteResponse.builder()
                .codigoPessoa(comprovanteRequest.getCodigoPessoa())
                .telefone(comprovanteRequest.getTelefone())
                .data(comprovantes)
                .build();
    }

    @Override
    public String gerarImagem(ImagemRequest imagemRequest) throws ProtocoloNaoEncontradoException, IOException {
        String protocolo = imagemRequest.getProtocolo();

        ComprovanteModel comprovante = repository.findByProtocolo(protocolo);
        if (comprovante == null) {
            throw new ProtocoloNaoEncontradoException();
        }
        String reciboHtml = comprovante.getRecibo();

        UploadClientRequest request = UploadClientRequest.builder()
                .protocoloId(protocolo)
                .html(reciboHtml)
                .build();

        String url = client.getUrlFromAws(request);

        return url;
    }
}
