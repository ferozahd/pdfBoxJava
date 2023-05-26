package fz.pdfbox.extended;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HeaderFooter;

import java.awt.*;
import java.io.IOException;

public class FParagraph {
    public static FParagraphBuilder content(PDPageContentStream contents) {
        return new FParagraphBuilder(contents);
    }


    public static class FParagraphBuilder {
        protected PDPageContentStream content;
        private PDFont font;
        private int fontSize;
        private Color color;

        public FParagraphBuilder(PDPageContentStream contents) {
            this.content = contents;
            this.font = PDType1Font.HELVETICA_BOLD;
            this.fontSize = 14;
        }

        public FParagraphBuilder fontSetup(PDFont font, int fontSize) {
            return fontSetup(font, fontSize, new Color(0, 0, 0));
        }

        public FParagraphBuilder fontSetup(PDFont font, int fontSize, Color color) {
            this.font = font;
            this.fontSize = fontSize;
            this.color = color;
            return this;
        }


        public FParagraphBuilder putText(int x, int y, String s, Color color) throws IOException {
            content.setNonStrokingColor(color);
            content.beginText();
            content.setFont(font, fontSize);
            content.newLineAtOffset(x, y);
            content.showText(s);
            content.endText();
            return this;
        }

        public FParagraphBuilder putText(int x, int y, String s, BeforeEnd end) throws IOException {

            ExplicitContentStream explic = new ExplicitContentStream();
            end.setContentExplicitly(explic);
            Color c = color;
            if(explic.getColor()!=null){
                c=explic.getColor();
            }
            content.setNonStrokingColor(c);
            content.beginText();



            PDFont f = this.font;
            int size = this.fontSize;



            if (explic.getFontSize() > 0) {
                size = explic.getFontSize();
            }

            if (explic.getFont() != null) {
                f = explic.getFont();
            }
            if (explic.getColor() != null) {
                f = explic.getFont();
            }


            content.setFont(f, size);
            content.newLineAtOffset(x, y);
            content.showText(s);

            content.endText();
            return this;
        }

        public FParagraphBuilder putText(int x, int y, String s) throws IOException {
            return putText(x, y, s, color);
        }

        public interface BeforeEnd {
            void setContentExplicitly(ExplicitContentStream content);
        }
    }

    public static class ExplicitContentStream {
        private int fontSize;
        private PDFont font;
        private Color color;

        public int getFontSize() {
            return fontSize;
        }

        public ExplicitContentStream fontSize(int f) {
            this.fontSize = f;
            return this;
        }

        public PDFont getFont() {
            return this.font;
        }

        public ExplicitContentStream font(PDFont font) {
            this.font = font;
            return this;
        }


        public ExplicitContentStream color(int red, int green, int blue) {
            this.color=new Color(red,green,blue);
            return this;
        }

        public Color getColor() {
            return this.color;
        }
    }
}
