package it.studenti.unisannio.gruppo7;

import org.faceless.pdf2.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PDFTypeMaker {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PDF pdf = new PDF();
    private PDFStyle style = new PDFStyle();
    private PDFStyle style2 = new PDFStyle();
    private PDFPage page = pdf.newPage("A4");

    public PDFTypeMaker (Date d1, Date d2, String tbType, String repType) throws IOException {
        if(repType.equalsIgnoreCase("Peso"))
            createPDFQuantity(d1,d2,tbType);
        else if (repType.equalsIgnoreCase("Qualita"))
            createPDFQuality(d1,d2,tbType);
    }

    private void createPDFQuality(Date d1, Date d2, String tbType) throws IOException {
        String title = "Report della qualità da: ";
        String ds1 = (sdf.format(d1.getTime()));
        String ds2 = (sdf.format(d2.getTime()));

        String tTitle =  title+ds1+" a: "+ds2;
        style.setFont(new StandardFont(StandardFont.TIMES),24);
        style.setFillColor(Color.black);
        page.setStyle(style);
        page.drawText(tTitle,100,page.getHeight()-100);
        PDFImage pie = createPDFPie(d1,d2,tbType);
        float w = pie.getWidth();
        float h = pie.getHeight();
        String x = "Report qualità"+".pdf";
        page.drawImage(pie,50,100,w,h);
        File f = new File("C:\\Users\\geros\\Desktop\\"+x);
        int i=1;
        while(f.exists() && (!f.isDirectory())){
            i++;
            x = "Report qualità("+i+").pdf";
            f = new File("C:\\Users\\geros\\Desktop\\"+x);
        }

        OutputStream out = new FileOutputStream(f);
        pdf.render(out);
        out.close();



    }

    private PDFImage createPDFPie(Date d1, Date d2, String tbType) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        //Set<Trashbag>
        //controllo il tipo
        //metto in un array do i valori al dataset

        dataset.setValue("Bassa",20);
        dataset.setValue("Medio-Bassa",10);
        dataset.setValue("Medio",40);
        dataset.setValue("Medio-alta",15);
        dataset.setValue("Alta",15);
        JFreeChart chart = ChartFactory.createPieChart("Sample pie chart",dataset,true,true,false);


        // stringValue = "La media è: "+(media%.2f)+"\n"+"Il valore più alto è: "+(massimo%2.f)+" nel giorno "+j;
        // page.drawText(stringValue,80,page.getHeight()-130);

        ChartUtils.saveChartAsPNG(new File("QualityPieChart.png"),chart,500,400);

        PDFImage image = new PDFImage(new FileInputStream("QualityPieChart.png"));

        return image;
    }

    public void createPDFQuantity(Date d1, Date d2, String tbType) throws IOException {
        String title = "Report del peso da: ";
        String ds1 = (sdf.format(d1.getTime()));
        String ds2 = (sdf.format(d2.getTime()));

        String tTitle = title+ds1+" a: "+ds2;
        style.setFont(new StandardFont(StandardFont.TIMES),24);
        style.setFillColor(Color.black);
        page.setStyle(style);
        page.drawText(tTitle,100,page.getHeight()-100);
        PDFImage hist = createPDFLinechart(d1,d2,tbType);
        float w = hist.getWidth();
        float h = hist.getHeight();
        String x = "Report quantità"+".pdf";
        page.drawImage(hist,10,10,w,h);
        File f = new File("C:\\Users\\geros\\Desktop\\"+x);
        int i=1;
        while(f.exists() && (!f.isDirectory())){
            i++;
            x = "Report quantità("+i+").pdf";
            f = new File("C:\\Users\\geros\\Desktop\\"+x);
        }

        OutputStream out = new FileOutputStream(f);
        pdf.render(out);
        out.close();

    }

    private PDFImage createPDFLinechart(Date d1, Date d2, String tbType) throws IOException {

        /*
        prelevare dal database i trashbag, controllare il tipo se alluminio
        plastica, carta o altro
        prelevare il peso di quel tipo
        mettere in values i pesi
         */

        //valori da giorno 1 a giorno x, ce ne devono essere y
        String series1 = "Peso dei sacchetti in una data";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        long dv1 = (d1.getTime()/(1000*60*60*24))%365;
        long dv2 = (d2.getTime()/(1000*60*60*24))%365;
        int z = 0;
        for(long i=dv1;i<=dv2;i++) {
            z++;
        }

        double[] weights = new double[z];
        Random gen = new Random();
        for(int i=0;i<z;i++){
            weights[i] = gen.nextDouble();
        }

        double[] days = new double[z];
        for(int j=0;j<days.length;j++){
            dataset.addValue(weights[j],series1,(""+j));
        }

        //Calcolo media dei pesi
        String stringValue = null;
        double somma, media;
        somma = 0;
        for(int i=0;i<weights.length;i++){
            somma = somma + weights[i];
        }
        media = somma/weights.length;

        //Calcolo valore più alto in una certa data
        double massimo=0;
        int j = 0;
        for(int i=0;i<weights.length;i++){
            System.out.println(weights[i]);
            if(weights[i]>massimo){
                massimo=weights[i];
                j = i;
            }
        }

        style2.setFont(new StandardFont(StandardFont.TIMES),12);
        style2.setFillColor(Color.BLACK);
        page.setStyle(style2);
        stringValue = "La media è: "+(media%.2f)+"\n"+"Il valore più alto è: "+(massimo%2.f)+" nel giorno "+j;
        page.drawText(stringValue,80,page.getHeight()-130);

        JFreeChart chart = ChartFactory.createLineChart("Report su peso","Date","Peso",dataset);
        ChartUtils.saveChartAsPNG(new File("QuantityLineChart.png"),chart,500,400);

        PDFImage image = new PDFImage(new FileInputStream("QuantityLineChart.png"));

        return image;
    }
}
