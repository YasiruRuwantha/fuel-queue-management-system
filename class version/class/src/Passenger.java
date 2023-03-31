public class Passenger {

    private String First_Name;
    private String Sec_Name;
    private String Vehicle_No;
    private double Req_fuel;
    private double Expenses;


    public Passenger(){
        First_Name = " empty";
        Sec_Name =" empty";
        Vehicle_No = " empty";
        Req_fuel = 0.0;



    }




    public String getFirst_Name() {
        return First_Name;
    }
    public void delFirst_Name() {First_Name = " empty";}


    public String getSec_Name() {
        return Sec_Name;
    }
    public void delSec_Name() {
        Sec_Name = " empty";
    }
    public String getVehicle_No() {
        return Vehicle_No;
    }
    public void delVehicle_No() {
        Vehicle_No = " empty";
    }

    public double getReq_fuel() {
        return Req_fuel;
    }
    public void delReq_fuel() {
        Req_fuel = 0.0;
    }

    public double getExpenses() {
        return Expenses;
    }
    public void delExpenses() {
        Expenses = 0.0;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setSec_Name(String sec_Name) {
        Sec_Name = sec_Name;
    }

    public void setVehicle_No(String vehicle_No) {
        Vehicle_No = vehicle_No;
    }

    public void setReq_fuel(double req_fuel) {
        Req_fuel = req_fuel;
    }

    public void setExpenses(double expenses) {
        Expenses = expenses;
    }

}
