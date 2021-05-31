package com.tp1_transac.services.permis;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.*;
import java.time.LocalDate;

@Service
public class PermisServices {

    final private String filePathQrAdulte = "c:/WEB/420/citoyen/adulte/qr/";
    final private String filePathQrEnfant = "c:/WEB/420/citoyen/enfant/qr/";

    final private String filePathPdfAdulte = "c:/WEB/420/citoyen/adulte/pdf/";
    final private String filePathPdfEnfant = "c:/WEB/420/citoyen/enfant/pdf/";

    public static int dureVaccin = 6;
    public static int dureTest = 15;

    @Autowired
    private CitoyenRepository citoyenRepository;

    @Autowired
    static JavaMailSender mailSender;

    public boolean createNewQRAdulte(CitoyenAdulte citoyenAdulte) throws Exception {
        boolean flag = true;
        Path path = FileSystems.getDefault().getPath(filePathQrAdulte + citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".PNG");

        if (!checkIfPathExists(path)) {
            Files.createDirectories(path);
            QRCodeWriter qr = new QRCodeWriter();
            MatrixToImageWriter.writeToPath(qr.encode(citoyenAdulte.toString(), BarcodeFormat.QR_CODE, 300, 300), "PNG", path);
        }
        else {
            QRCodeWriter qr = new QRCodeWriter();
            MatrixToImageWriter.writeToPath(qr.encode(citoyenAdulte.toString(), BarcodeFormat.QR_CODE, 300, 300), "PNG", path);
        }
        createNewPDFAdulte(citoyenAdulte);
        return flag;
    }

    public boolean createNewQREnfant(CitoyenEnfant citoyenEnfant) throws Exception {
        boolean flag = true;
        Path path = FileSystems.getDefault().getPath(filePathQrEnfant +  citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".PNG");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);
        if(!pathExists) {
            Files.createDirectories(path);
        }
        QRCodeWriter qr = new QRCodeWriter();
        MatrixToImageWriter.writeToPath(qr.encode(citoyenEnfant.toString(), BarcodeFormat.QR_CODE, 300, 300), "PNG", path);

        createNewPDFEnfant(citoyenEnfant);
        return flag;
    }

    public boolean createNewPDFAdulte(CitoyenAdulte citoyenAdulte) throws Exception{
        boolean flag = true;
        Path path = FileSystems.getDefault().getPath(filePathPdfAdulte);
        if(!checkIfPathExists(path)) {
            Files.createDirectories(path);
        }
        PdfWriter writer = new PdfWriter(filePathPdfAdulte + citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Image image = new Image(ImageDataFactory.create(filePathQrAdulte + citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".PNG"));
        Paragraph p = new Paragraph("Santé Canada " + citoyenAdulte.getNom()).add("Voici ton code permis de " + citoyenAdulte.getPermis().getTypePermis()).add(image);
        document.add(p);
        document.close();

        sendEmail(citoyenAdulte.getEmail(), "Permis santé", document.toString(), "Adulte");
        return flag;
    }
    public boolean createNewPDFEnfant(CitoyenEnfant citoyenEnfant) throws Exception{
        boolean flag = true;
        Path path = FileSystems.getDefault().getPath(filePathPdfEnfant);
        if(!checkIfPathExists(path)) {
            Files.createDirectories(path);
        }
        PdfWriter writer = new PdfWriter(filePathPdfEnfant + citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Image image = new Image(ImageDataFactory.create(filePathQrEnfant +  citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".PNG"));
        Paragraph p = new Paragraph("Santé Canada " + citoyenEnfant.getNom()).add("Voici ton code permis de " + citoyenEnfant.getPermis().getTypePermis()).add(image);
        document.add(p);
        document.close();

        CitoyenAdulte citoyenAdulte = citoyenRepository.findCitoyenAdulteById(citoyenEnfant.getCitoyenAdulteId());
        sendEmail(citoyenAdulte.getEmail(), "Permis santé", document.toString(), "Enfant");

        return flag;
    }

    public boolean sendEmail(String mailTo, String subject, String body, String acteur) throws Exception {
        boolean flag = true;
        String filePathQr = "";
        String filePathPdf = "";

        if (acteur.equals("Adulte")) {
            filePathQr = filePathQrAdulte;
            filePathPdf = filePathPdfAdulte;
        }
        else {
            filePathQr = filePathQrEnfant;
            filePathPdf = filePathPdfEnfant;
        }
        /*MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment("QR CODE", new File(filePathQr));
        helper.addAttachment("QR PDF", new File(filePathPdf));

        mailSender.send(message);*/
        return flag;
    }

    public boolean renewQREnfant(CitoyenEnfant citoyenEnfant) throws Exception {
        boolean flag = true;
        Permis permis = citoyenEnfant.getPermis();
        if (permis.getTypePermis().equals("VA")) {
            permis.setDatePermisVaccin(LocalDate.now());
            permis.setDateExpirationVaccin(LocalDate.now().plusMonths(dureVaccin));
        }
        else {
            permis.setDatePermisTest(LocalDate.now());
            permis.setDateExpirationTest(LocalDate.now().plusDays(dureTest));
        }
        createNewQREnfant(citoyenEnfant);
        return flag;
    }

    public boolean renewQrAdulte(CitoyenAdulte citoyenAdulte) throws Exception {
        boolean flag = true;
        Permis permis = citoyenAdulte.getPermis();
        if (permis.getTypePermis().equals("VA")) {
            permis.setDatePermisVaccin(LocalDate.now());
            permis.setDateExpirationVaccin(LocalDate.now().plusMonths(dureVaccin));
        }
        else {
            permis.setDatePermisTest(LocalDate.now());
            permis.setDateExpirationTest(LocalDate.now().plusDays(dureTest));
        }
        createNewQRAdulte(citoyenAdulte);
        return flag;
    }

    public boolean checkIfPathExists(Path path){
        return Files.exists(path , LinkOption.NOFOLLOW_LINKS);
    }
}
