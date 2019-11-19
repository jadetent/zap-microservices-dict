package com.zap.contadigital.comprovantes.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;


import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;


@Service
public class ComprovanteService {
    public void getLogo() throws Exception {
        String dest = "/home/comprovantes/transferencia.pdf";
        PdfWriter writer = new PdfWriter(dest);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);

        String imFile = "/home/comprovantes/logo.jpg";
        ImageData data = ImageDataFactory.create(imFile);

        Image image = new Image(data);
        Paragraph p = new Paragraph().add(image);
        Table table = new Table(3);
        Cell cell = new Cell(3, 1).add(p).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        table.addCell(gerarCabecalho("Comprovante de Transferência"));
        table.addCell(new Cell(1,2).setBorder(Border.NO_BORDER).add(new Paragraph("Data Transação: 19/11/2019 17:00")));
        table.addCell(new Cell(1,3).setBorder(Border.NO_BORDER).add(new Paragraph("Autenticação: AX155AS65466AQSDAS854Q%")));
        /*
        //table.addCell(new Cell().add(new Paragraph("Cell 1.2")));
        table.addCell(new Cell()
                .add(new Paragraph("Data 2")));
        table.addCell(new Cell()
                .add(new Paragraph("Data 3"))
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setPadding(5));


        // Closing the document
         */
        document.add(table);
        document.close();

        System.out.println("Image added");
    }
    private Cell gerarCabecalho(String cabecalho){
        Cell cell = new Cell(1, 2)
        .add(new Paragraph(cabecalho))
        .setBorder(Border.NO_BORDER);
        return cell;
    }
}
