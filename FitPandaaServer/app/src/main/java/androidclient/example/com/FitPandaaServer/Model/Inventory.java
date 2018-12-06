package androidclient.example.com.FitPandaaServer.Model;

public class Inventory {
    //Date LastUdateddat;
    private String burgersQty;
    String burgersPrice;
    String chickensQty;
    String chickensPrice;
    String frenchfriesQty;
    String FrenchfriesPrice;
    String onionringsQty;
    String onionringsPrice;

    public String getBurgersQty() {
        return burgersQty;
    }

    public void setBurgersQty(String burgersQty) {
        this.burgersQty = burgersQty;
    }

    public String getBurgersPrice() {
        return burgersPrice;
    }

    public void setBurgersPrice(String burgersPrice) {
        this.burgersPrice = burgersPrice;
    }

    public String getChickensQty() {
        return chickensQty;
    }

    public void setChickensQty(String chickensQty) {
        this.chickensQty = chickensQty;
    }

    public String getChickensPrice() {
        return chickensPrice;
    }

    public void setChickensPrice(String chickensPrice) {
        this.chickensPrice = chickensPrice;
    }

    public String getFrenchfriesQty() {
        return frenchfriesQty;
    }

    public void setFrenchfriesQty(String frenchfriesQty) {
        this.frenchfriesQty = frenchfriesQty;
    }

    public String getFrenchfriesPrice() {
        return FrenchfriesPrice;
    }

    public void setFrenchfriesPrice(String frenchfriesPrice) {
        FrenchfriesPrice = frenchfriesPrice;
    }

    public String getOnionringsQty() {
        return onionringsQty;
    }

    public void setOnionringsQty(String onionringsQty) {
        this.onionringsQty = onionringsQty;
    }

    public String getOnionringsPrice() {
        return onionringsPrice;
    }

    public void setOnionringsPrice(String onionringsPrice) {
        this.onionringsPrice = onionringsPrice;
    }

    public String toString()
    {
        return "Burger " + "Qty " + burgersQty + "Price " + burgersPrice  + "\n"
                +"Chickens " + "Qty " + chickensQty + "Price " + chickensPrice  + "\n"
                +"French Fries " + "Qty " + frenchfriesQty + "Price " + FrenchfriesPrice  + "\n"
                +"Onion Rings " + "Qty " + onionringsQty + "Price " + onionringsPrice  + "\n";
    }
}
