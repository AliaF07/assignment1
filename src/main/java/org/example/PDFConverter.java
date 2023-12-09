package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFConverter {

    private static final Logger LOGGER = Logger.getLogger(PDFConverter.class.getName());

    public static void main(String[] args) {
        if (args.length != 3) {
            LOGGER.warning("Usage: PDFConverter <inputFile> <outputFile> <conversionType>");
            LOGGER.warning("Conversion types: textToPDF, pdfToText");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        String conversionType = args[2];

        try {
            if (conversionType.equalsIgnoreCase("textToPDF")) {
                textToPDF(inputFile, outputFile);
                LOGGER.info("Text to PDF conversion completed.");
            } else if (conversionType.equalsIgnoreCase("pdfToText")) {
                pdfToText(inputFile, outputFile);
                LOGGER.info("PDF to Text conversion completed.");
            } else {
                LOGGER.severe("Invalid conversion type. Use 'textToPDF' or 'pdfToText'.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    public static void TextToPDF(String inputFilePath, String outputFilePath) throws IOException {
        PDDocument document = new PDDocument();
        FileInputStream inputStream = new FileInputStream(inputFilePath);

        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        document.addPage(new PDPage());
        PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0));
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(20, 750);
        contentStream.showText(text);
        contentStream.close();

        document.save(outputFilePath);
        document.close();
        inputStream.close();
    }

    public static void PdfToText(String inputFilePath, String outputFilePath) throws IOException {
        PDDocument document = PDDocument.load(new File(inputFilePath));
        PDFTextStripper textStripper = new PDFTextStripper();
        String text = textStripper.getText(document);

        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
            outputStream.write(text.getBytes());
        }

        document.close();
    }
}
