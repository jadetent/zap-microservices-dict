package com.zap.contadigital.comprovantes.controller;

import com.zap.contadigital.comprovantes.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comprovantes")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;


    @GetMapping(path = "/qrcode", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> qrCode() throws Exception {
        String qrCodeText = "bit.ly/ZapGanhei5";
        byte[] result = qrCodeService.createQRCode(qrCodeText);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/qrcode-template", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> qrCodeTemplate() throws Exception {
        byte[] bytes = qrCodeService.gerarQrCodeEstabelecimento("bit.ly/ZapGanhei5");
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
