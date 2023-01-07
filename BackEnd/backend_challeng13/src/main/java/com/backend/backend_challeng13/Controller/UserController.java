package com.backend.backend_challeng13.Controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend_challeng13.Entity.ImageModel;
import com.backend.backend_challeng13.Entity.User;
import com.backend.backend_challeng13.Service.PdfGenerator;
import com.backend.backend_challeng13.Service.UserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired 
    private PdfGenerator pdfGenerator;

    // return All user
    @GetMapping({ "/getAllUsers" })
    public List<User> getAllUser() {
        return userService.getAlluser();
    }

    // add new user
    @PostMapping(value = { "/addUser" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public User addUser(@RequestPart("user") User user,
            @RequestPart("imagefile") MultipartFile file) {
        try {
            ImageModel image = uploadImage(file);
            user.setUserImage(image);
            return userService.addUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ImageModel uploadImage(MultipartFile multipartFiles) throws IOException {

        ImageModel imageModel = new ImageModel(multipartFiles.getOriginalFilename(), multipartFiles.getContentType(),
                multipartFiles.getBytes());

        return imageModel;
    }

    @GetMapping("/QrCode")
    public void getQrCode(HttpServletResponse response) throws WriterException, IOException {
        String text = userService.getSpecificuser();
        if (text == null) {
            text = "ajouter un utulisateur"; // initialize with string if there is no users
        }
        int width = 200;
        int height = 200;

        // create the QR code image
        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);

        // write the QR code image to a ByteArrayOutputStream
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        // set the content type and return the QR code image as a response
        response.setContentType("image/png");
        response.getOutputStream().write(pngOutputStream.toByteArray());
    }

    @PostMapping("/generate-pdf")
    public void generetaPDF(@RequestPart("user") User user
    ) throws FileNotFoundException, DocumentException{
        
        try {
            // ImageModel images = uploadImage(file);

            pdfGenerator.generatePdf(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
