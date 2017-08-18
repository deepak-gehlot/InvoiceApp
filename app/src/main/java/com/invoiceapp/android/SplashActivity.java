package com.invoiceapp.android;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SplashActivity extends AppCompatActivity {
    //http://www.theappguruz.com/blog/android-text-pdf
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

   /*     startActivity(new Intent(SplashActivity.this, GetStartedActivity.class));
        finish();*/
        try {
            // Destination Folder and File name
            String FILE = Environment.getExternalStorageDirectory().toString()
                    + "/PDF/" + "Name.pdf";
// Add Permission into Manifest.xml
//

// Create New Blank Document
            Document document = new Document(PageSize.A4);

// Create Directory in External Storage
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/PDF");
            myDir.mkdirs();

// Create Pdf Writer for Writting into New Created Document
            PdfWriter.getInstance(document, new FileOutputStream(FILE));

// Open Document for Writting into document
            document.open();

// User Define Method
            addMetaData(document);
            addTitlePage(document);
// Close Document after writting all content
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Set PDF document Properties
    public void addMetaData(Document document)

    {
        document.addTitle("RESUME");
        document.addSubject("Person Info");
        document.addKeywords("Personal,Education, Skills");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }

    public void addTitlePage(Document document) throws DocumentException {
// Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                | Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

// Start New Paragraph
        Paragraph prHead = new Paragraph();
// Set Font in this Paragraph
        prHead.setFont(titleFont);
// Add item into Paragraph
        prHead.add("RESUME – Name\n");

// Create Table into Document with 1 Row
        PdfPTable myTable = new PdfPTable(1);
// 100.0f mean width of table is same as Document size
        myTable.setWidthPercentage(100.0f);

// Create New Cell into Table
        PdfPCell myCell = new PdfPCell(new Paragraph(""));
        myCell.setBorder(Rectangle.BOTTOM);

// Add Cell into Table
        myTable.addCell(myCell);

        prHead.setFont(catFont);
        prHead.add("\nName1 Name2\n");
        prHead.setAlignment(Element.ALIGN_CENTER);

// Add all above details into Document
        document.add(prHead);
        document.add(myTable);
        document.add(myTable);

// Now Start another New Paragraph
        Paragraph prPersinalInfo = new Paragraph();
        prPersinalInfo.setFont(smallBold);
        prPersinalInfo.add("Address 1\n");
        prPersinalInfo.add("Address 2\n");
        prPersinalInfo.add("City: SanFran.  State: CA\n");
        prPersinalInfo.add("Country: USA Zip Code: 000001\n");
        prPersinalInfo.add("Mobile: 9999999999 Fax: 1111111 Email: john_pit@gmail.com \n");

        prPersinalInfo.setAlignment(Element.ALIGN_CENTER);

        document.add(prPersinalInfo);
        document.add(myTable);
        document.add(myTable);

        Paragraph prProfile = new Paragraph();
        prProfile.setFont(smallBold);
        prProfile.add("\n \n Profile : \n ");
        prProfile.setFont(normal);
        prProfile.add("\nI am Mr. XYZ. I am Android Application Developer at TAG.");

        prProfile.setFont(smallBold);
        document.add(prProfile);


        /*Chunk c = new Chunk("Total Cost:", smallBold);
        c.setBackground(BaseColor.RED);
        Paragraph p = new Paragraph(c);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);*/

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100.0f);
        Paragraph elements = new Paragraph("Hello World");
        elements.setAlignment(Element.ALIGN_RIGHT);
        PdfPCell pppp = new PdfPCell();
        pppp.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(pppp);

        document.add(table);
// Create new Page in PDF
        document.newPage();
    }
}
