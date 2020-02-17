package DayzExtractPrices;

public class Record {
    private String name;
    private String buyPrice;
    private String sellPrice;

    public Record(String n, String b, String s) {
        name = n;
        buyPrice = b;
        sellPrice = s;
    }
    public String getName() {
        return name;
    }
    public String getBuyPrice() {
        return buyPrice;
    }
    public String getSellPrice() {
        return sellPrice;
    }
}
