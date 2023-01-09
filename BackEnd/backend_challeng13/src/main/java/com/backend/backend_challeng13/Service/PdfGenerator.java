package com.backend.backend_challeng13.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.backend.backend_challeng13.Entity.ImageModel;
import com.backend.backend_challeng13.Entity.User;
import com.google.zxing.WriterException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.BaseDirection;
import com.itextpdf.layout.properties.TextAlignment;

@Component
public class PdfGenerator {

    public void generatePdf(User user, ImageModel imageModel)
            throws MalformedURLException, IOException, WriterException {
        Rectangle envelope = new Rectangle(860, 540);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream("Challeng13.pdf")));
        Document document = new Document(pdfDoc, new PageSize(envelope));
        document.setMargins(2, 2, 2, 2);

        // add content to PDF
        // create tabel
        Table pdfPTable = new Table(2);
        pdfPTable.setWidth(860);
        pdfPTable.setHeight(new PageSize(envelope).getHeight() - document.getTopMargin() - document.getBottomMargin());
        // Add Font
        String fontPath = "src/main/resources/fonts/NotoSansArabic-VariableFont_wdth,wght.ttf";
        PdfFont font = PdfFontFactory.createFont(fontPath);
        // Create cells
        Cell row1 = new Cell(1, 2)
                .add(new Paragraph("البطاقة المهنية ").setFont(font).setTextAlignment(TextAlignment.CENTER));

        Cell row2 = new Cell(1, 2).add(new Paragraph("Carte Professionelle").setTextAlignment(TextAlignment.CENTER));

        Cell row3 = new Cell(1, 2).add(new Paragraph("N: " + user.getCin()));

        // cusstom row 4
        Table pdfPTable2 = new Table(2);
        Image img = new Image(ImageDataFactory.create(imageModel.getPicByte()));
        img.scaleAbsolute(150, 200);
        Cell Cell7 = new Cell(3, 1).add(img);

        // custom col8
        Table pdfPTable3 = new Table(2);
        Cell pdfPCe1 = new Cell().add(new Paragraph("Nome : " + user.getNome()).setTextAlignment(TextAlignment.CENTER));
        Cell pdfPCe2 = new Cell().add(new Paragraph("الاسم العائلي : " + user.getNomeArabe()).setFont(font).setTextAlignment(TextAlignment.CENTER));
        pdfPTable3.addCell(pdfPCe1);
        pdfPTable3.addCell(pdfPCe2);
        Cell pdfPCe12 = new Cell().add(new Paragraph("Prenome: " + user.getPrenome()).setTextAlignment(TextAlignment.CENTER));
        Cell pdfPCe22 = new Cell().add(new Paragraph("الاسم الشخصي: " + user.getPrenomeArab()).setFont(font).setTextAlignment(TextAlignment.CENTER));
        pdfPTable3.addCell(pdfPCe12);
        pdfPTable3.addCell(pdfPCe22);
        Cell pdfPe22 = new Cell(1, 2).add(new Paragraph("Date Naissance: " + user.getDateNaissance()).setTextAlignment(TextAlignment.CENTER));
        pdfPTable3.addCell(pdfPe22);
        Cell Cell8 = new Cell().add(pdfPTable3);
        // col 8
        pdfPTable2.addCell(Cell7);
        pdfPTable2.addCell(Cell8);
        Cell row4 = new Cell().add(pdfPTable2);

        // row 5
        Cell row5 = new Cell(2, 2).add(new Paragraph("CIN: " + user.getCin()));

        Cell row6 = new Cell(1, 2).add(new Paragraph("row 7 "));
        Cell row7 = new Cell(1, 2).add(new Paragraph("row 6"));

        // Add cells to table
        pdfPTable.addCell(row1);
        pdfPTable.addCell(row2);
        pdfPTable.addCell(row3);
        pdfPTable.addCell(row4);
        pdfPTable.addCell(row5);
        pdfPTable.addCell(row6);
        pdfPTable.addCell(row7);

        document.add(pdfPTable);
        // add new page for qrCode
        document.add(new AreaBreak());

        String text = user.toString();
        if (text == null) {
            text = "ajouter un utulisateur"; // initialize QrCode when there is no user
        }

        // cusstom QRCode
        Table tabelQR = new Table(2);
        tabelQR.setWidth(860);
        tabelQR.setHeight(new PageSize(envelope).getHeight() - document.getTopMargin() - document.getBottomMargin());
        Image imge = new Image(ImageDataFactory.create(QrCodeService.getQRCodeImage(text, 200, 200)));
        imge.scaleAbsolute(150, 200).setFixedPosition(100, 250);
        Cell cellQr = new Cell(1, 2).add(imge);
        // Add cells to table
        tabelQR.addCell(cellQr);
        document.add(tabelQR);

        document.close();
    }
}