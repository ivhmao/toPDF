package com.ivhmao;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length != 3){
            System.out.println("usage:");
            System.out.println("toPDF <out.pdf> <form.pdf> <data.xpdf>");
            return;
        }
        System.out.println( "Hello World!" );

        try {
            new App().fillXfdf(args[1],args[2],args[0]);
        }catch (Exception e ){
            e.printStackTrace();
        }



    }

    protected void fillXfdf(String SRC, String XFDF, String DEST) throws IOException, DocumentException {
        // We receive the XML bytes
        //считаем данные из xfdf-файла
        XfdfReader xfdf = new XfdfReader(new FileInputStream(XFDF));
        // We get the corresponding form
        //считываем пдф-форму
        PdfReader reader = new PdfReader(SRC);
        // We create an OutputStream for the new PDF
        FileOutputStream baos = new FileOutputStream(DEST);
        // Now we create the PDF
        PdfStamper stamper = new PdfStamper(reader, baos);
        //создадим итоговый пдф-файл
        //PdfStamper stamper = new PdfStamper(pdfReader,new FileOutputStream(args[0]));

        // We alter the fields of the existing PDF
        AcroFields fields = stamper.getAcroFields();
        fields.setFields(xfdf);
        // take away all interactivity
        stamper.setFormFlattening(true);
        // close the stamper
        stamper.close();
        reader.close();
    }
}
