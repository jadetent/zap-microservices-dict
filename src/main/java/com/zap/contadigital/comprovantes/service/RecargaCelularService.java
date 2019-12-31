package com.zap.contadigital.comprovantes.service;

import com.zap.contadigital.comprovantes.dto.ComprovanteRecargaCelularDTO;

public interface RecargaCelularService {

    byte[] gerarComprovanteRecargaCelular(ComprovanteRecargaCelularDTO comprovante) throws Exception;

}
