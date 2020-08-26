package com.zap.contadigital.comprovantes.service.impl;

import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.request.ImagemRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.comprovantes.dto.response.ImagemResponse;
import com.zap.contadigital.comprovantes.exception.ProtocoloNaoEncontradoException;
import com.zap.contadigital.comprovantes.model.Comprovante;
import com.zap.contadigital.comprovantes.service.IComprovanteService;
import com.zap.contadigital.model.recibos.ComprovanteModel;
import com.zap.contadigital.repository.comprovantes.ComprovantesRepository;
import com.zap.contadigital.util.DateUtil;
import gui.ava.html.image.generator.HtmlImageGenerator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprovanteService implements IComprovanteService {

    @Autowired
    private ComprovantesRepository repository;

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
    public ImagemResponse gerarImagem(ImagemRequest imagemRequest) throws ProtocoloNaoEncontradoException {
        String protocolo = imagemRequest.getProtocolo();

        ComprovanteModel comprovante = repository.findByProtocolo(protocolo);
        if (comprovante == null) {
            throw new ProtocoloNaoEncontradoException();
        }
        String reciboHtml = comprovante.getRecibo();

        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(reciboHtml);
        imageGenerator.saveAsImage("comprovante_" + protocolo + ".png");

        return ImagemResponse.builder()
                .protocolo(imagemRequest.getProtocolo())
                .build();
    }
}
