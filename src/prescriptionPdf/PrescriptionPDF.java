
package prescriptionPdf;

import com.itextpdf.text.DocumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class PrescriptionPDF {
    public static final String path = System.getProperty("user.home") + "/Documents/Ordonnances/";

    public static void generatePdf(String patientName, String medicines) throws DocumentException, IOException, URISyntaxException {
        String title = "ORDONNANCE";
        String head1 = "Université Ferhat Abbas Sétif";
        String head2 = "Centre Médico-socio";
        String dateLabel = "Sétif, le   .. / .. / ....";
        String nameLabel = "Nom et Prénom: ";
        String ageLabel = "Age: ";
        String age = "";
        String name = "";

        PDDocument document = new PDDocument();
        PDImageXObject logo = PDImageXObject.createFromFile(System.getProperty("user.home") + "/Documents/Projects/ClinicOffice/src/icons/Logo.png", document);
        PDImageXObject header = PDImageXObject.createFromFile(System.getProperty("user.home") + "/Documents/Projects/ClinicOffice/src/icons/header.png", document);
        PDPage page = new PDPage(PDRectangle.A5);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDType1Font font = PDType1Font.HELVETICA_BOLD;

        age = patientName.substring(patientName.length() - 10, patientName.length() - 6);
        name = patientName.substring(0, patientName.length() - 10);


        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.moveTextPositionByAmount(16, page.getMediaBox().getHeight() - 20);
        contentStream.drawString(head1);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.moveTextPositionByAmount(16, page.getMediaBox().getHeight() - 35);
        contentStream.drawString(head2);
        contentStream.endText();

        contentStream.drawImage(logo, 20, page.getMediaBox().getHeight() - 80, 80, 30);

        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - 100), page.getMediaBox().getHeight() - 60);
        contentStream.drawString(dateLabel);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.moveTextPositionByAmount(16, page.getMediaBox().getHeight() - 100);
        contentStream.drawString(nameLabel);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 10);
        contentStream.moveTextPositionByAmount(86, page.getMediaBox().getHeight() - 100);
        contentStream.drawString(name.toUpperCase());
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.moveTextPositionByAmount(350, page.getMediaBox().getHeight() - 100);
        contentStream.drawString(ageLabel);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.moveTextPositionByAmount(370, page.getMediaBox().getHeight() - 100);
        contentStream.drawString(String.valueOf(LocalDateTime.now().getYear() - Integer.parseInt(age)));
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 16);
        contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - 116) / 2, page.getMediaBox().getHeight() - 140);
        contentStream.drawString(title);
        contentStream.endText();

        contentStream.drawLine((page.getMediaBox().getWidth() - 116) / 2, page.getMediaBox().getHeight() - 146, (page.getMediaBox().getWidth() - 116) / 2 + 120, page.getMediaBox().getHeight() - 146);

        medicines = medicines.replace("\n", ";").replace("\r", ";");
        String[] parts = medicines.split(";");

        for (int i=0; i<parts.length; i++) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            contentStream.moveTextPositionByAmount(50, page.getMediaBox().getHeight() - 180 - 30*i);
            contentStream.drawString(parts[i]);
            contentStream.endText();
        }

        contentStream.drawLine(150, 40, page.getMediaBox().getWidth() - 150, 40);

        //contentStream.drawImage(header, page.getMediaBox().getWidth() / 2 - 100, 0, 200, 50);

        contentStream.close();

        String fileName = name.replaceAll("\\s+", "-");

        File f = new File(path + "/" + fileName.toLowerCase() + "-" + LocalDateTime.now().getDayOfMonth() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getYear() + ".pdf");
        f.getParentFile().mkdirs();
        f.createNewFile();
        document.save(f);
        /*if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
            }
        }*/
        document.close();
    }
}

