package com.invoiceapp.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.invoiceapp.android.R;
import com.invoiceapp.android.view.model.CreateInvoiceModel;
import com.invoiceapp.android.view.model.ProductModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RWS 6 on 8/18/2017.
 */

public class ProductListAllPDF {

    //creating a PdfWriter variable. PdfWriter class is available at com.itextpdf.text.pdf.PdfWriter
    private PdfWriter pdfWriter;
    String PATH_PRODUCT_REPORT = "ppp";
    static Font FONT_SUBTITLE = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    static Font FONT_TITLE = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    static Font FONT_BODY = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    static Font FONT_TABLE_HEADER = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    static Font FONT_HEADER_FOOTER = new Font(Font.FontFamily.HELVETICA, 7, Font.ITALIC);

    //we will add some products to arrayListRProductModel to show in the PDF document
    private static ArrayList<ProductModel> arrayListRProductModel = new ArrayList<ProductModel>();

    public boolean createPDF(Context context, CreateInvoiceModel createInvoiceModel, String reportName) {

        try {
            //creating a directory in SD card
            File mydir = new File(Environment.getExternalStorageDirectory()
                    + "/" + PATH_PRODUCT_REPORT); //PATH_PRODUCT_REPORT="/SIAS/REPORT_PRODUCT/"
            if (!mydir.exists()) {
                mydir.mkdirs();
            }

            //getting the full path of the PDF report name
            String mPath = Environment.getExternalStorageDirectory().toString()
                    + "/" + PATH_PRODUCT_REPORT //PATH_PRODUCT_REPORT="/SIAS/REPORT_PRODUCT/"
                    + "/" + reportName + ".pdf"; //reportName could be any name

            //constructing the PDF file
            File pdfFile = new File(mPath);

            if (!pdfFile.exists()) {
                pdfFile.createNewFile();
            }

            //Creating a Document with size A4. Document class is available at  com.itextpdf.text.Document
            Document document = new Document(PageSize.A4);

            //assigning a PdfWriter instance to pdfWriter
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

            //PageFooter is an inner class of this class which is responsible to create Header and Footer
            PageHeaderFooter event = new PageHeaderFooter();
            pdfWriter.setPageEvent(event);

            //Before writing anything to a document it should be opened first
            document.open();

            //Adding meta-data to the document
            addMetaData(document);
            //Adding Title(s) of the document
            addTitlePage(context, document, createInvoiceModel);
            //Adding main contents of the document
            addContent(document);
            //Closing the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    /**
     * iText allows to add metadata to the PDF which can be viewed in your Adobe Reader. If you right click
     * on the file and to to properties then you can see all these information.
     *
     * @param document
     */
    private static void addMetaData(Document document) {
        document.addTitle("All Product Names");
        document.addSubject("none");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("SIAS ERP");
        document.addCreator("testimhhh");
    }

    /**
     * In this method title(s) of the document is added.
     *
     * @param document
     * @throws DocumentException
     */
    private static void addTitlePage(Context context, Document document, CreateInvoiceModel createInvoiceModel)
            throws DocumentException {
        Paragraph paragraph = new Paragraph();

        try {
            Drawable d = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = createInvoiceModel.getLogo();
            Image image = null;
            if (bmp != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image = Image.getInstance(stream.toByteArray());
                image.setAlignment(Element.ALIGN_RIGHT);
                image.scaleAbsolute(100,100);
            }

            // Adding several title of the document. Paragraph class is available in  com.itextpdf.text.Paragraph
            Paragraph childParagraph = new Paragraph(createInvoiceModel.getEmail(), FONT_SUBTITLE); //public static Font FONT_TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 22,Font.BOLD);
            Paragraph emailPara = new Paragraph(createInvoiceModel.getBusinessName(), FONT_TITLE);
            paragraph.add(emailPara);
            if (bmp != null) {
                childParagraph.add(image);
            }
            paragraph.add(childParagraph);

            document.add(paragraph);

            addEmptyLine(paragraph, 2);
            addEmptyLine(paragraph, 2);
            addEmptyLine(paragraph, 2);
            addEmptyLine(paragraph, 2);

            Paragraph ph = new Paragraph(new Phrase("", FONT_TITLE));
            PdfPCell cell = new PdfPCell(ph);
            cell.setBorder(Rectangle.BOTTOM);
            /*cell.BorderColor = new BaseColor(44, 67, 144);
            cell.BorderWidth = 2f;
*/
            PdfPTable table = new PdfPTable(1);
            table.addCell(cell);

            document.add(table);

            addEmptyLine(paragraph, 5);


        } catch (IOException e) {
            e.printStackTrace();
        }


        //End of adding several titles

    }

    /**
     * In this method the main contents of the documents are added
     *
     * @param document
     * @throws DocumentException
     */
    private static void addContent(Document document) throws DocumentException {

        Paragraph reportBody = new Paragraph();
        reportBody.setFont(FONT_BODY); //public static Font FONT_BODY = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL);

        try {
            addHorizontalLine(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Paragraph paragraph = new Paragraph();


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.addCell(getCell("Bill To", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("INVOICE0002", PdfPCell.ALIGN_RIGHT));
        document.add(table);

        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100);
        table2.addCell(getCell("Deepak", PdfPCell.ALIGN_LEFT));
        table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
        document.add(table2);

        addEmptyLine(paragraph, 2);
        document.add(paragraph);
        // Creating a table
        createTable(reportBody);

        // now add all this to the document
        document.add(reportBody);


        try {
            Paragraph paragraph1 = new Paragraph();
            addEmptyLine(paragraph1, 2);
            document.add(paragraph1);
            addHorizontalLine(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Paragraph reportBody2 = new Paragraph();
        reportBody2.setFont(FONT_BODY); //public static Font FONT_BODY = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL);

        createTablePaymentInfo(reportBody2);

        // now add all this to the document
        document.add(reportBody2);

        try {
            Paragraph paragraph3 = new Paragraph();
            addHorizontalLine(document);
            addEmptyLine(paragraph3, 3);
            document.add(paragraph3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Paragraph reportBody3 = new Paragraph();
        reportBody3.setFont(FONT_BODY); //public static Font FONT_BODY = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL);

        createTablePaymentTotal(reportBody3);

    }

    public static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    /**
     * This method is responsible to add table using iText
     *
     * @param reportBody
     * @throws BadElementException
     */
    private static void createTable(Paragraph reportBody)
            throws BadElementException {

        float[] columnWidths = {10, 3, 3, 3}; //total 4 columns and their width. The first three columns will take the same width and the fourth one will be 5/2.
        PdfPTable table = new PdfPTable(columnWidths);

        table.setWidthPercentage(100); //set table with 100% (full page)
        table.getDefaultCell().setUseAscender(true);

        Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        FONT_TABLE_HEADER.setColor(BaseColor.WHITE);
        //Adding table headers
        PdfPCell cell = new PdfPCell(new Phrase("Description", //Table Header
                FONT_TABLE_HEADER)); //Public static Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);

        cell.setBackgroundColor(BaseColor.DARK_GRAY); //cell background color
        cell.setFixedHeight(30); //cell height
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Qty",
                FONT_TABLE_HEADER));

        cell.setBackgroundColor(new GrayColor(0.75f));
        cell.setFixedHeight(30);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rate",
                FONT_TABLE_HEADER));

        cell.setBackgroundColor(new GrayColor(0.75f));
        cell.setFixedHeight(30);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount",
                FONT_TABLE_HEADER));

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBackgroundColor(new GrayColor(0.75f));
        cell.setFixedHeight(30);
        table.addCell(cell);

        //End of adding table headers

        //This method will generate some static data for the table
        generateTableData();

        //Adding data into table
        for (int i = 0; i < arrayListRProductModel.size(); i++) { //
            cell = new PdfPCell(new Phrase(arrayListRProductModel.get(i).getDescription()));
            cell.setFixedHeight(28);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(arrayListRProductModel.get(i).getQuantity()));
            cell.setFixedHeight(28);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(arrayListRProductModel.get(i).getRate()));
            cell.setFixedHeight(28);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(arrayListRProductModel.get(i).getAmount()));
            cell.setFixedHeight(28);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
        }

        reportBody.add(table);


    }


    /**
     * This method is responsible to add table using iText
     *
     * @param reportBody
     * @throws BadElementException
     */
    private static void createTablePaymentInfo(Paragraph reportBody)
            throws BadElementException {

        float[] columnWidths = {7, 5, 3, 3}; //total 4 columns and their width. The first three columns will take the same width and the fourth one will be 5/2.
        PdfPTable table = new PdfPTable(columnWidths);

        table.setWidthPercentage(100); //set table with 100% (full page)
        table.getDefaultCell().setUseAscender(true);

        Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        FONT_TABLE_HEADER.setColor(BaseColor.BLACK);
        //Adding table headers
        PdfPCell cell = new PdfPCell(new Phrase("Payment Instruction", //Table Header
                FONT_TABLE_HEADER)); //Public static Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
        cell.setFixedHeight(20); //cell height
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",
                FONT_TABLE_HEADER));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Subtotal",
                FONT_TABLE_HEADER));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("500",
                FONT_TABLE_HEADER));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        //End of adding table headers

        //Adding data into table
        cell = new PdfPCell(new Phrase("Via Paypal"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Discount (5%)"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("25.15"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Send payment to : test@gmail.com"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("VAT(5.5%)"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("26.28"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Shipping"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("369.00"));
        cell.setFixedHeight(20);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        reportBody.add(table);

    }


    /**
     * This method is responsible to add table using iText
     *
     * @param reportBody
     * @throws BadElementException
     */
    private static void createTablePaymentTotal(Paragraph reportBody)
            throws BadElementException {

        float[] columnWidths = {7, 5, 3, 3}; //total 4 columns and their width. The first three columns will take the same width and the fourth one will be 5/2.
        PdfPTable table = new PdfPTable(columnWidths);

        table.setWidthPercentage(100); //set table with 100% (full page)
        table.getDefaultCell().setUseAscender(true);

        Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        FONT_TABLE_HEADER.setColor(BaseColor.BLACK);
        //Adding table headers
        PdfPCell cell = new PdfPCell(new Phrase("Payment Instruction", //Table Header
                FONT_TABLE_HEADER)); //Public static Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);

        //End of adding table headers

        //Adding data into table
        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total"));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("820.00"));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Balance Due"));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("26.28"));
        cell.setFixedHeight(28);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        reportBody.add(table);

    }

    /**
     * This method is used to add empty lines in the document
     *
     * @param paragraph
     * @param number
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * This is an inner class which is used to create header and footer
     *
     * @author XYZ
     */

    class PageHeaderFooter extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);

        public void onEndPage(PdfWriter writer, Document document) {

            /**
             * PdfContentByte is an object containing the user positioned text and graphic contents
             * of a page. It knows how to apply the proper font encoding.
             */
            PdfContentByte cb = writer.getDirectContent();

            /**
             * In iText a Phrase is a series of Chunks.
             * A chunk is the smallest significant part of text that can be added to a document.
             *  Most elements can be divided in one or more Chunks. A chunk is a String with a certain Font
             */
            Phrase footer_poweredBy = new Phrase("Powered By OrangeInvoice ERP", FONT_HEADER_FOOTER); //public static Font FONT_HEADER_FOOTER = new Font(Font.FontFamily.UNDEFINED, 7, Font.ITALIC);
            Phrase footer_pageNumber = new Phrase("Page " + document.getPageNumber(), FONT_HEADER_FOOTER);

            // Header
            // ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, header,
            // (document.getPageSize().getWidth()-10),
            // document.top() + 10, 0);

            // footer: show page number in the bottom right corner of each age
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                    footer_pageNumber,
                    (document.getPageSize().getWidth() - 10),
                    document.bottom() - 10, 0);
//			// footer: show page number in the bottom right corner of each age
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer_poweredBy, (document.right() - document.left()) / 2
                            + document.leftMargin(), document.bottom() - 10, 0);
        }
    }

    /**
     * Generate static data for table
     */

    private static void generateTableData() {
        ProductModel productModel = new ProductModel("Samsung Galaxy Note", "2", "₹25", "₹50");
        arrayListRProductModel.add(productModel);
        arrayListRProductModel.add(productModel);
        arrayListRProductModel.add(productModel);
        arrayListRProductModel.add(productModel);
        arrayListRProductModel.add(productModel);
        arrayListRProductModel.add(productModel);
        arrayListRProductModel.add(productModel);
    }

    private static void addHorizontalLine(Document document) throws DocumentException, IOException {
        PdfPTable myTable = new PdfPTable(1);
        myTable.setWidthPercentage(100.0f);
        PdfPCell cellOne = new PdfPCell();
        cellOne.setBorder(Rectangle.BOTTOM);
        document.add(new Paragraph(" "));
        document.add(myTable);
    }
}
