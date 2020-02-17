import DayzExtractPrices.ExtractText;
import DayzExtractPrices.Record;
import DayzExtractPrices.SaveToCSV;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws TesseractException, NullPointerException {
        ArrayList<String> namesTab;
        ArrayList<String> buyPricesTab;
        ArrayList<String> sellPricesTab;
        ArrayList<Record> Rec = new ArrayList<>();

        String pathToCSV = "/home/user/Documents/Java/DayZ_Extract-TraderPrices/src/"; // Path to csv file
        String fileName = "CSVdata.csv"; // Name of CSV file
        int k = 0;

        ExtractText.setDataSet("/home/user/Documents/Java/JavaJars/Tess4J/tessdata"); // PAth to Tesseract DataSet
        String dirPath = "/home/user/Documents/Java/DayZ_Extract-TraderPrices/src/exampleimg"; // Path to img dir

        SaveToCSV.addHeader(pathToCSV, fileName);

        File dir = new File(dirPath);
        File[] directoryListing = dir.listFiles();
        {
            if(directoryListing!=null) {
                for (File child : directoryListing) {

                    System.out.println(child.getName());
                    ExtractText.setImage(child.getPath()); // PAth to Image


                    namesTab = ExtractText.fetchNames();
                    buyPricesTab = ExtractText.fetchBuyPrices();
                    sellPricesTab = ExtractText.fetchSellPrices();

                    for (String elem : namesTab) {
                        //System.out.println(elem);
                        Rec.add(new Record(elem, buyPricesTab.get(k), sellPricesTab.get(k)));
                        k++;
                    }
                    for (Record elem : Rec) {
                        System.out.println("Name: " + elem.getName() + " Buy: " + elem.getBuyPrice() + " Sell: " + elem.getSellPrice());
                    }
                    SaveToCSV.writeToCSV(Rec, pathToCSV, fileName);

                    Rec.clear();
                    namesTab.clear();
                    buyPricesTab.clear();
                    sellPricesTab.clear();

                    k = 0;
                }
            }
            else {
                System.out.println("Directory doesnt exist");
            }
        }
    }
}
