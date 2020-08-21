package com.zap.contadigital.comprovantes.dto.response;

import com.zap.contadigital.comprovantes.model.Comprovante;
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

    private List<Comprovante> data;

}