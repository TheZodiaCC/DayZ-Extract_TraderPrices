import DayzExtractPrices.ExtractText;
import DayzExtractPrices.Record;
import DayzExtractPrices.SaveToCSV;
import net.sourceforge.tess4j.TesseractException;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws TesseractException {
        ArrayList<String> namesTab;
        ArrayList<String> buyPricesTab;
        ArrayList<String> sellPricesTab;
        ArrayList<Record> Rec = new ArrayList<>();

        String pathToCSV = "/home/user/Documents/Java/DayZ_Extract-TraderPrices/src/"; // Example path to save csv file
        String fileName = "CSVdata.csv"; // CSV file name
        int k=0;

        ExtractText.setImage("/home/user/Documents/Java/DayZ_Extract-TraderPrices/src/test2c.png"); // Path to image
        ExtractText.setDataSet("/home/user/Documents/Java/JavaJars/Tess4J/tessdata"); // Path to Tesseract data set

        namesTab = ExtractText.fetchNames();
        buyPricesTab = ExtractText.fetchBuyPrices();
        sellPricesTab = ExtractText.fetchSellPrices();

        for (String elem: namesTab)
        {
            //System.out.println(elem);
            Rec.add(new Record(elem, buyPricesTab.get(k), sellPricesTab.get(k)));
            k++;
        }
        //for (Record elem: Rec)
        //{
        //    System.out.println("Name: " + elem.getName() + " Buy: " + elem.getBuyPrice() + " Sell: " + elem.getSellPrice());
        //}
        SaveToCSV.writeToCSV(Rec, pathToCSV, fileName);
    }
}
