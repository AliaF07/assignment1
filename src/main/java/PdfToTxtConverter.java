import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PdfToTxtConverter {

    public static void convertPdfToTxt(String pdfFile, String txtFile) {
        try (PDDocument document = PDDocument.load(new File(pdfFile));
             FileWriter fw = new FileWriter(txtFile)) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            fw.write(text);

        } catch (IOException e) {
            System.err.println("Error occurred during PDF to TXT conversion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        convertPdfToTxt("input.pdf", "output.txt");
    }
}
