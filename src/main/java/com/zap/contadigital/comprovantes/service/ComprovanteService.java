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

import java.util.Date;

@Service
public class ComprovanteService {
    private String imFile = "/home/comprovantes/logo.jpg";
    private final String TRACOS="-------------------------------------------------------------------------------------------------";
    private String getComprovante(){
        String dest = "/home/comprovantes/comprovante" + new Date().getTime() + ".pdf";
        return dest;
    }
    public Table criarCabecalho(String transacao) throws Exception {
        ImageData imageData = ImageDataFactory.create(imFile);
        Image image = new Image(imageData);
        Paragraph p = new Paragraph().add(image);
        Table table = new Table(3);
        Cell logo = new Cell(3, 1).add(p).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(logo);
        table.addCell(criarCelula(1,2,"CONTA ZAP - CONTA DIGITAL"));
        table.addCell(criarCelula(1,2,transacao));
        table.addCell(criarCelula(1,2,"TRANSAÇÃO: F191C348-4D3D-BD5B-6B09-9DC3C9128D24"));
        return table;
    }
    public String gerarComprovanteOutroFavorecido() throws Exception {
        String comprovante = getComprovante();
        PdfWriter writer = new PdfWriter(comprovante);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(criarCabecalho("TRANSFERENCIA ENTRE CONTAS"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("CLIENTE: ","CONTA ZAP - SA"));
        document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("FAVORECIDO",""));
        document.add(criarCelula("NOME: ","LUCAS SILVA PEREIRA"));
        //document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula("CPF/CNPJ: ","123.456.789-10"));
        document.add(criarCelula("VALOR: ","1.234,56"));
        document.add(criarCelula("DATA: ","22/11/2019"));
        document.add(criarCelula(TRACOS,""));
        document.close();
        return comprovante;
    }
    public String gerarComprovanteMesmoFavorecido() throws Exception {
        String comprovante = getComprovante();
        PdfWriter writer = new PdfWriter(comprovante);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(criarCabecalho("TRANSFERENCIA ENTRE CONTAS DE MESMO FAVORECIDO"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("CLIENTE: ","CONTA ZAP - SA"));
        document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("FAVORECIDO",""));
        document.add(criarCelula("NOME: ","CONTA ZAP - SA"));
       // document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula("CPF/CNPJ: ","123.456.789-10"));
        document.add(criarCelula("VALOR: ","1.234,56"));
        document.add(criarCelula("DATA: ","22/11/2019"));
        document.add(criarCelula(TRACOS,""));
        document.close();
        return comprovante;
    }
    public String gerarComprovanteP2P() throws Exception {
        String comprovante = getComprovante();
        PdfWriter writer = new PdfWriter(comprovante);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(criarCabecalho("TRANSFERENCIA P2P"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("CLIENTE: ","CONTA ZAP - SA"));
        // document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("FAVORECIDO",""));
        document.add(criarCelula("TELEFONE: ","11 91234 - 5678"));
        document.add(criarCelula("VALOR: ","1.234,56"));
        document.add(criarCelula("DATA: ","22/11/2019"));
        document.add(criarCelula(TRACOS,""));
        document.close();
        return comprovante;
    }
    public String gerarComprovantePagamento() throws Exception {
        String comprovante = getComprovante();
        PdfWriter writer = new PdfWriter(comprovante);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(criarCabecalho("COMRPOVANTE DE PAGAMENTO"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("CLIENTE: ","LUCIO ALVES PEREIRA"));
        // document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("CODIGO DE BARRAS: ","13546546464876548654876546548"));
        document.add(criarCelula("BANCO CEDENTE: ","CONTA ZAP - DIGITAL"));
        document.add(criarCelula("Nº DOCUMENTO: ","123456"));
        document.add(criarCelula("DATA VENCIMENTO: ","22/11/2019"));
        document.add(criarCelula("DATA PAGAMENTO: ","22/11/2019"));
        document.add(criarCelula("VALOR DESCONTO: ","R$ 0,00"));
        document.add(criarCelula("VALOR ACRESCIMO: ","R$ 0,00"));
        document.add(criarCelula("VALOR TOTAL: ","R$ 1.234,00"));
        document.add(criarCelula("DESCRIÇÃO: ","PAGAMENTO DE TITULO"));
        document.add(criarCelula(TRACOS,""));
        document.close();
        return comprovante;
    }
    public String gerarComprovanteRecarga() throws Exception {
        String comprovante = getComprovante();
        PdfWriter writer = new PdfWriter(comprovante);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(criarCabecalho("RECARGA BILHETE ÚNICO"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("CLIENTE: ","LUCIO ALVES PEREIRA"));
        // document.add(criarCelula("CONTA: ","20FA48B8-9AC1-B414-5073-36FB291A0C78"));
        document.add(criarCelula(TRACOS,""));
        document.add(criarCelula("RECARGA: ","BILHETE UNICO - SPTRANS"));
        document.add(criarCelula("N° BILHETE: ","123456"));
        document.add(criarCelula("N° DOCUMENTO: ","708545"));
        document.add(criarCelula("DATA RECARGA: ","22/11/2019"));
        document.add(criarCelula("VALOR RECARGA: ","R$ 100,00"));
        document.add(criarCelula("SALDO ANTERIOR: ","R$ 10,00 "));
        document.add(criarCelula("SALDO ATUAL: ","R$ 110,00"));
        document.add(criarCelula(TRACOS,""));
        document.close();
        return comprovante;
    }
    private Paragraph criarCelula(String campo, String valor){
        Paragraph p= new Paragraph();
        p.setFontSize(8);
        p.setFirstLineIndent(55);
        p.add(campo + valor);
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
