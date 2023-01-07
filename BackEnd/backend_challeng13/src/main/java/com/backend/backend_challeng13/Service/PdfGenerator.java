package com.backend.backend_challeng13.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.backend_challeng13.Entity.User;
import com.google.zxing.WriterException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfGenerator {
    @Autowired
    private UserService userService;

    public void generatePdf(User user) throws DocumentException, MalformedURLException, IOException, WriterException {
        Rectangle envelope = new Rectangle(86, 54);
        Document document = new Document();
        // document.setPageSize(envelope);
        PdfWriter.getInstance(document, new FileOutputStream("helloworld.pdf"));

        document.open();
        // add content to PDF
        Paragraph p = new Paragraph();
        // add Nome
        p.add(new Chunk("Nome : " + user.getNome()));
        document.add(p);
        // add prenome
        p = new Paragraph();
        p.add(new Chunk("Prenome: " + user.getPrenome()));
        document.add(p);
        // add Nome Arabe
        p = new Paragraph();
        p.add(new Chunk("الاسم العائلي : " + user.getNomeArabe()));
        document.add(p);
        // add prenome arabe
        p = new Paragraph();
        p.add(new Chunk("الاسم الشخصي: " + user.getPrenomeArab()));
        document.add(p);
        // add Cin
        p = new Paragraph();
        p.add(new Chunk("CIN: " + user.getPrenome()));
        document.add(p);
        // add date naissance
        p = new Paragraph();
        p.add(new Chunk("Date Naissance: " + user.getDateNaissance()));
        document.add(p);

        // Image img = Image.getInstance(images.getPicByte().toString());
        // document.add(img);
        document.newPage();

        String text = user.toString();
        if (text == null) {
            text = "ajouter un utulisateur"; // initialize with string if there is no users
        }

        Image img = Image.getInstance(QrCodeService.getQRCodeImage(text, 200, 200));
        document.add(img);

        document.close();
    }
}