package com.zap.contadigital.comprovantes.controller.impl;

import com.zap.contadigital.comprovantes.controller.IQRCodeController;
import com.zap.contadigital.comprovantes.service.impl.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comprovantes")
public class QRCodeController implements IQRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Override
    @GetMapping(path = "/qrcode", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> qrCode() throws Exception {
        /**
         * testes - geração de qr code
         */

        //String qrCodeText = "bit.ly/ZapGanhei5";
        String qrCodeText = "00020101021126440014br.gov.bcb.spi0122fulano2019@example.com52 04000053039865802BR5913FULANO DE TAL6008BRASILIA6304DFE3";

        byte[] result = qrCodeService.createQRCode(qrCodeText);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/qrcode-template", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> qrCodeTemplate() throws Exception {
        byte[] bytes = qrCodeService.gerarQrCodeEstabelecimento("bit.ly/ZapGanhei5");
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

}