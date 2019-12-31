package com.zap.contadigital.comprovantes.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class QRCodeService extends ComprovanteService {

    public byte[] gerarQrCodeEstabelecimento(String conteudo) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        byte[] qrCode = createQRCode(conteudo);
        File file = createQRCodeFile(conteudo);
        parametros.put("qrcode", file.getAbsolutePath());
        //parametros.put("base64", "data:image/jpeg;base64," + imagem);
        parametros.put("base64", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(qrCode));
        return comprovanteByteArray("ESTABELECIMENTO_QRCODE", parametros);
    }

    public byte[] createQRCode(String conteudo) throws WriterException, IOException {
        BufferedImage image = image(conteudo);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    private File createQRCodeFile(String conteudo) throws WriterException, IOException {
        File dir = new File("/dev/qrcode");
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dir, "qrcode-" + new Date().getTime() + ".jpg");
        BufferedImage image = image(conteudo);
        ImageIO.write(image, "jpg", file);
        return file;
    }

    private BufferedImage image(String conteudo) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int size = 125;
        BitMatrix byteMatrix = qrCodeWriter.encode(conteudo, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        return image;
    }

}
