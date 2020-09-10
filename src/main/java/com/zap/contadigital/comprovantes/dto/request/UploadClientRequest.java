package com.zap.contadigital.comprovantes.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadClientRequest {
    private String protocoloId;
    private String html;
}
