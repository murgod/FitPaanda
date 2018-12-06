package androidclient.example.com.FitPandaaServer.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    private static double tax = 0.06;
    private static double profit = 0.3;
    private static int count = 0;
    private int orderId;
    //public Customer customer = new Customer();
    private int burgerCount = 0;
    private int chickensCount=0;
    private int friesCount=0;
    private int ringsCount=0;
    private final double burgerPrice = 3.5, chickensPrice = 4.0, friesPrice = 1.5, ringsPrice = 2.0;
    private double totalBill = 0;
    private double finalBill = 0;
    private String orderStatus = "Created";

    Order (){
        orderId = ++count;
    }

    public void generateTotalBill(){
        this.totalBill = this.burgerCount*this.burgerPrice + this.chickensCount*this.chickensPrice + this.friesCount*this.friesPrice + this.ringsCount*this.ringsPrice;
    }
    public void generateFinalBill(){
        this.finalBill = (totalBill)*(1+tax+profit);
    }

    public Order (Parcel in){
        orderId = in.readInt();
        burgerCount = in.readInt();
        chickensCount = in.readInt();
        friesCount = in.readInt();
        ringsCount = in.readInt();
        totalBill = in.readDouble();
        finalBill = in.readDouble();
        orderStatus = in.readString();
        //customer = in.readParcelable(Customer.class.getClassLoader());
    }

    private void Bill(){
        generateTotalBill();
        generateFinalBill();
    }
    public int getOrderId() {
        return orderId;
    }

    public int getBurgerCount() {
        return burgerCount;
    }

    public void setBurgerCount(int burgerCount) {
        this.burgerCount = burgerCount;
        Bill();
    }
    public int getChickensCount() {
        return chickensCount;
    }

    public void setChickensCount(int chickensCount) {
        this.chickensCount = chickensCount;
        Bill();
    }

    public int getFriesCount() {
        return friesCount;
    }

    public void setFriesCount(int friesCount) {
        this.friesCount = friesCount;
        Bill();
    }

    public int getRingsCount() {
        return ringsCount;
    }

    public void setRingsCount(int ringsCount) {
        this.ringsCount = ringsCount;
        Bill();
    }
    public double getTotalBill() {
        return totalBill;
    }
    public double getFinalBill() {
        return finalBill;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(orderId);
        dest.writeInt(burgerCount);
        dest.writeInt(chickensCount);
        dest.writeInt(friesCount);
        dest.writeInt(ringsCount);
        dest.writeDouble(totalBill);
        dest.writeDouble(finalBill);
        dest.writeString(orderStatus);
       // dest.writeParcelable(this.customer, flags);
    }

    public static final Parcelable.Creator<Order>CREATOR = new Parcelable.Creator<Order>(){

        public Order createFromParcel(Parcel in){
            return new Order(in);
        }

        public Order[] newArray(int size){
            return new Order[size];
        }
    };

}