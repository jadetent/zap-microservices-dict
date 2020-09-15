package com.zap.contadigital.dict.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comprovantes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Comprovante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "servico")
    private String servico;

    @Column(name = "recibo")
    private String recibo;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "codigo_pessoa")
    private String codigoPessoa;

    @Column(name = "data_transacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataTransacao;

    @Column(name = "protocolo")
    private String protocolo;
}