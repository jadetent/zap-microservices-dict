package com.zap.contadigital.comprovantes.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

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
        Cell logo = new Cell(3, 1).add(p).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(logo);
        table.addCell(criarCelula(1,2,"Comprovante de Transferência",12).setBold());
        table.addCell(criarCelula(1,2,"Data Transação: 19/11/2019 17:00"));
        table.addCell(criarCelula(1,2,"Autenticação: AX155AS65466AQSDAS854Q%"));
        document.add(table);

        document.add(detalheTransacao("Conta Origem - Telefone","1234566"));
        document.add(detalheTransacao("Conta Destino - Telefone","98785646"));
        document.add(detalheTransacao("Valor","R$ 122,33"));
        document.close();

        System.out.println("Image added");
    }
    private Paragraph detalheTransacao(String campo, String valor){
        Paragraph p= new Paragraph();
        p.setFontSize(8);
        p.setFirstLineIndent(55);
        p.add(campo + ":" + valor);
        return p;
    }
    private Cell criarCelula(String text){
        return criarCelula(1, 1,text,8);
    }
    private Cell criarCelula(int rowspan, int colspan,String text){
        return criarCelula(rowspan, colspan,text,8);
    }
    private Cell criarCelula(int rowspan, int colspan,String text, int fontFize ){
        return new Cell(rowspan,colspan).setBorder(Border.NO_BORDER).add(new Paragraph(text).setFontSize(fontFize));
    }
}
