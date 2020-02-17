package DayzExtractPrices;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;

public class SaveToCSV {
    public static void writeToCSV(ArrayList<Record> recordsList, String pathToCSV, String fileName)
    {
        File file = new File(pathToCSV + fileName);
        try {
            FileWriter outputCSV = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputCSV);

            String [] header = "Name;Buy Price;Sell Price".split(";");
            writer.writeNext(header);

            ArrayList<String[]> data = new ArrayList<>();
            for (Record elem : recordsList) {
                data.add(new String[] { elem.getName(), elem.getBuyPrice(), elem.getSellPrice() });
            }

            writer.writeAll(data);
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
