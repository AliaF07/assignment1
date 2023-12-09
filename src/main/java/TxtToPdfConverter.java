import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtToPdfConverter {

    public static void convertTxtToPdf(String txtFile, String pdfFile) {
        try (PDDocument doc = new PDDocument();
             BufferedReader br = new BufferedReader(new FileReader(txtFile));
             PDPageContentStream contentStream = new PDPageContentStream(doc, new PDPage())) {

            PDPage page = new PDPage();
            doc.addPage(page);
            float y = page.getMediaBox().getHeight() - 50; // Start at top of the page

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(50, y);

            String line;
            while ((line = br.readLine()) != null) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -15); // Move to the next line
            }
            contentStream.endText();

            doc.save(pdfFile);
        } catch (IOException e) {
            System.err.println("Error occurred during TXT to PDF conversion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        convertTxtToPdf("input.txt", "output.pdf");
    }
}