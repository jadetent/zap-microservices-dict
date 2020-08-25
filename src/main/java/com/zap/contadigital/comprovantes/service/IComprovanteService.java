package com.zap.contadigital.comprovantes.service;

import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.request.ImagemRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.comprovantes.dto.response.ImagemResponse;

public interface IComprovanteService {
    ComprovanteResponse listarPagamentosRealizados(ComprovanteRequest comprovanteRequest) throws Exception;

    ImagemResponse gerarImagem(ImagemRequest imagemRequest);
}
