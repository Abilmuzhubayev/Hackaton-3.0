package com.asphyxia.routList.service;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.tagging.StandardRoles;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

@Service
public class ReportService {


    public void generateReport(String[][] generalInfo, String[][] driverWorkInfo, String[][] intermediateInfo, String[][] locoInfo) throws Exception {

        String path = "C:\\Users\\user\\Desktop\\instructions\\Second.pdf";

        PdfWriter pdfWriter = new PdfWriter(path);

        // creating title
        Paragraph title = new Paragraph("ITINERARY FOR TRAIN DRIVER")
                .setFontSize(20f)
                .setFontColor(new DeviceRgb(0, 0, 0)).setTextAlignment(TextAlignment.CENTER);
        title.getAccessibilityProperties().setRole(StandardRoles.H1);


        //creating general title
        Paragraph general = new Paragraph("GENERAL INFORMATION FOR THE ROUTE")
                .setFontSize(12f)
                .setFontColor(new DeviceRgb(0, 0, 0)).setTextAlignment(TextAlignment.CENTER);

        // creating general table
        Table generalTable = new Table(new float[] {100f, 100f, 100f, 100f, 100f, 100f});
        String[] generalHeader = {"route id", "driver", "departure station", "destination station", "departure date", "arrival date"};

        for (int i = 0; i < 6; i++) {
            generalTable.addCell(generalHeader[i]);
        }

        generalTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //creating driver work title

        Paragraph driverWork = new Paragraph("INFORMATION ABOUT DRIVER WORK")
                .setFontSize(12f)
                .setFontColor(new DeviceRgb(0, 0, 0)).setTextAlignment(TextAlignment.CENTER);

        //creating driver work table

        Table driverWorkTable = new Table(new float[] {100f, 100f, 100f, 100f});
        String[] driverWorkHeader = {"arrival to work", "departure station operator", "finish time", "destination station operator"};

        for (int i = 0; i < 4; i++) {
            driverWorkTable.addCell(driverWorkHeader[i]);
        }

        driverWorkTable.setHorizontalAlignment(HorizontalAlignment.CENTER);


        //creating intermediate stations title

        Paragraph intermediateStations = new Paragraph("INFORMATION ABOUT INTERMEDIATE STATIONS")
                .setFontSize(12f)
                .setFontColor(new DeviceRgb(0, 0, 0)).setTextAlignment(TextAlignment.CENTER);

        //creating intermediate stations table

        Table intermediateStationsTable = new Table(new float[] {100f, 100f, 100f, 100f, 100f});
        String[] intermediateStationsHeader = {"station", "arrival", "departure", "massa netto", "massa brutto"};

        for (int i = 0; i < 5; i++) {
            intermediateStationsTable.addCell(intermediateStationsHeader[i]);
        }

        intermediateStationsTable.setHorizontalAlignment(HorizontalAlignment.CENTER);



        //creating locodata title

        Paragraph locoData = new Paragraph("INFORMATION ABOUT LOCOMOTIVE ACCEPTANCE AND SUBMISSION")
                .setFontSize(12f)
                .setFontColor(new DeviceRgb(0, 0, 0)).setTextAlignment(TextAlignment.CENTER);

        //creating locodata table

        Table locoDataTable = new Table(new float[] {100f, 100f, 100f, 100f, 100f, 100f, 100f});
        String[] locoDataHeader = {"acceptance time", "electric/acceptance", "recuperation/acceptance", "submission time", "electric/submission", "recuperation/submission", "remark codes"};

        for (int i = 0; i < 7; i++) {
            locoDataTable.addCell(locoDataHeader[i]);
        }

        locoDataTable.setHorizontalAlignment(HorizontalAlignment.CENTER);



        //generating document
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);

        document.add(title);
        document.add(general);
        document.add(generalTable);
        document.add(driverWork);
        document.add(driverWorkTable);
        document.add(intermediateStations);
        document.add(intermediateStationsTable);
        document.add(locoData);
        document.add(locoDataTable);


        document.close();
    }

}
