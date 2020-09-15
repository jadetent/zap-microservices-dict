package com.zap.contadigital.dict.dto.response;

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