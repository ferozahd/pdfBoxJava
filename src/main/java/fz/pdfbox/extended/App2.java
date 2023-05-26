package fz.pdfbox.extended;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;

public class App2 {
    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contents = new PDPageContentStream(document, page);


        PDImageXObject pdImage = PDImageXObject.createFromFile("sittap.png", document);
        final float width = 60f;
        final float scale = width / pdImage.getWidth();
        contents.drawImage(pdImage, 50, 720, width, pdImage.getHeight() * scale);

        FParagraph.content(contents)
            .fontSetup(PDType1Font.HELVETICA, 10)
            .putText(120, 740, "Sittap co.",
                (v) -> v.font(PDType1Font.HELVETICA_BOLD)
                    .fontSize(16)
                    .color(208, 0, 140))
            .putText(120, 720, "Rajshahi,Durgapur")
            .putText(120, 708, "Dhaka, Bangladesh")
            .putText(120, 696, "2542 feroz.shah.940@gmail.com")
            .putText(450, 740, "INVOICE")
            .putText(400, 710, "Invoice date:")
            .putText(400, 698, "Invoice number:")
            .putText(500, 710, "2023-05-15")
            .putText(500, 698, "54642322344");


        FParagraph.content(contents)
            .fontSetup(PDType1Font.HELVETICA, 10, new Color(80, 80, 80))
            .putText(120, 660, "From :")
            .putText(120, 648, "Feroz Ahmmed")
            .putText(120, 636, "Nandigram, Belghoria.")
            .putText(120, 624, "6240, Nawpara")
            .putText(120, 612, "Dhaka , Bangladesh");

        FParagraph.content(contents)
            .fontSetup(PDType1Font.HELVETICA, 10, new Color(80, 80, 80))
            .putText(400, 660, "Deliver:")
            .putText(400, 648, "Miss:Sultana Khatun")
            .putText(400, 636, "Nandihgram  , Belghoria 6240.")
            .putText(400, 624, "Durgapur Rajshahi")
            .putText(400, 612, "Dhaka Bangladesh");


        contents.setStrokingColor(new Color(100, 100, 100));
        contents.setNonStrokingColor(new Color(230, 230, 230));
        contents.addRect(50, 570, 520, 20);
        contents.fillAndStroke();
        contents.addRect(50, 550, 520, 20);
        contents.stroke();

        FParagraph.content(contents)
            .fontSetup(PDType1Font.HELVETICA, 12)
            .putText(60, 577, "Id")
            .putText(160, 577, "Name.")
            .putText(280, 577, "Group")
            .putText(340, 577, "tax")
            .putText(450, 577, "Quantity")
            .putText(510, 577, "Price");

        FParagraph.content(contents)
            .fontSetup(PDType1Font.HELVETICA, 8)
            .putText(60, 557, "1Z0939459699")
            .putText(160, 557, "7Up")
            .putText(280, 557, "Drinks")
            .putText(340, 557, "3%")
            .putText(450, 557, "2")
            .putText(510, 557, "20");


        Color fillColor = new Color(230, 230, 230);
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        contents.setNonStrokingColor(fillColor);
        contents.addRect(50, 520, 520, 20);
        contents.fillAndStroke();


        FParagraph.content(contents)
            .fontSetup(PDType1Font.HELVETICA, 12)
            .putText(60, 520 + 7, "Shop.")
            .putText(160, 520 + 7, "City")
            .putText(380, 520 + 7, "code")
            .putText(440, 520 + 7, "Due")
            .putText(510, 520 + 7, "Total");


        contents.close();
        document.save("demo.pdf");
        document.close();
    }


}
