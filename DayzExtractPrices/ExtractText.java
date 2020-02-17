package DayzExtractPrices;

import net.sourceforge.tess4j.Tesseract;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;

public class ExtractText {

    private static Image image;
    private static BufferedImage crop;
    private static Tesseract tesseract = new Tesseract();

    public static void setImage(String pathToImage) {
        try {
            image = ImageIO.read(new File(pathToImage));
            ExtractText.convertToGray();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void setDataSet(String pathToDataSet) {
        tesseract.setDatapath(pathToDataSet);
    }
    private static void convertToGray() {
        BufferedImage crop = (BufferedImage) image;

        for(int i=0; i<crop.getHeight(); i++) {
            for(int j=0; j<crop.getWidth(); j++) {

                Color c = new Color(crop.getRGB(j, i));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                Color newColor = new Color(red+green+blue,
                        red+green+blue,red+green+blue);

                crop.setRGB(j,i,newColor.getRGB());
            }
        }
    }
    public static ArrayList<String> fetchNames() throws TesseractException{
        BufferedImage crop = (BufferedImage) image;
        String names = tesseract.doOCR(((BufferedImage) image).getSubimage(0, 0, 860, crop.getHeight()));

        ArrayList<String> namesTab = new ArrayList<String>(Arrays.asList(names.split("\n")));
        namesTab.removeAll(Arrays.asList(" ", null));

        for (int i=0; i<namesTab.size(); i++)
        {
            if (namesTab.get(i).length() == 0 || namesTab.get(i).equals("—"))
            {
                namesTab.remove(i);
            }
        }

        return namesTab;
    }
    public static ArrayList<String> fetchBuyPrices() throws TesseractException{
        BufferedImage crop = (BufferedImage) image;
        String buyPrices = tesseract.doOCR(((BufferedImage) image).getSubimage(860, 0, 150, crop.getHeight()));

        ArrayList<String> buyPricesTab = new ArrayList<String>(Arrays.asList(buyPrices.split("\n")));
        buyPricesTab.removeAll(Arrays.asList(" ", null));

        for (int i=0; i<buyPricesTab.size(); i++)
        {
            if (buyPricesTab.get(i).length() == 0 || buyPricesTab.get(i).equals("—"))
            {
                buyPricesTab.remove(i);
            }
        }

        return buyPricesTab;
    }
    public static ArrayList<String> fetchSellPrices() throws TesseractException{
        BufferedImage crop = (BufferedImage) image;
        String sellPrices = tesseract.doOCR(((BufferedImage) image).getSubimage(1010, 0, crop.getWidth()-1010, crop.getHeight()));

        ArrayList<String> sellPricesTab = new ArrayList<String>(Arrays.asList(sellPrices.split("\n")));
        sellPricesTab.removeAll(Arrays.asList(" ", null));

        for (int i=0; i<sellPricesTab.size(); i++)
        {
            if (sellPricesTab.get(i).length() == 0 || sellPricesTab.get(i).equals("—"))
            {
                sellPricesTab.remove(i);
            }
        }

        return sellPricesTab;
    }

}
