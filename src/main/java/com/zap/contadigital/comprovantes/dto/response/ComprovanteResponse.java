package com.zap.contadigital.comprovantes.dto.response;

import com.zap.contadigital.comprovantes.model.Comprovante;
import com.zap.contadigital.model.recibos.ComprovanteModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprovanteResponse implements Serializable {

    private String telefone;

    private String codigoPessoa;

    private List<ComprovanteModel> data;

}