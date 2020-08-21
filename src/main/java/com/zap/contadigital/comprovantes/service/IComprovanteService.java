package com.zap.contadigital.comprovantes.service;

import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;

public interface IComprovanteService {
    ComprovanteResponse listarPagamentosRealizados(ComprovanteRequest comprovanteRequest) throws Exception;
}
