/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topk.util;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Harris Bastin
 */
public class FileToText {

    public String wordToText(File file) throws FileNotFoundException, IOException {
        String content = "";
        FileInputStream fis = new FileInputStream(file);
        XWPFDocument document = new XWPFDocument(fis);
        XWPFWordExtractor docExtractor = new XWPFWordExtractor(document);
        content = docExtractor.getText();
        return content;
    }

    public String parsePdf(String pdf) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        TextExtractionStrategy strategy;
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            content.append(strategy.getResultantText());
        }

        reader.close();
        return new String(content);
    }
}
