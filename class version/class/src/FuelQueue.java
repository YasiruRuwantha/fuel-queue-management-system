public class FuelQueue {

    public Passenger[] getPassengernum;
    public Passenger[] getPassenger;

    private int queueNum;

    private Passenger cus0;
    private Passenger cus1;
    private Passenger cus2;
    private Passenger cus3;
    private Passenger cus4;
    private Passenger cus5;



    public FuelQueue() {

        this.cus0 = new Passenger();
        this.cus1 = new Passenger();
        this.cus2 = new Passenger();
        this.cus3 = new Passenger();
        this.cus4 = new Passenger();
        this.cus5 = new Passenger();


        this.passengersobj = new Passenger[]{cus0,cus1,cus2,cus3,cus4,cus5};

    }

    private Passenger[] passengersobj;





    public int getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(int queueNum) {
        this.queueNum = queueNum;
    }

    public Passenger[] getPassengersobj() {
        return passengersobj;
    }

    public void setPassengersobj(Passenger[] passengersobj) {
        this.passengersobj = passengersobj;
    }









    public Passenger getPassengernum (int number){
        return passengersobj[number];


    }

   public Passenger[] getPassenger (){
        return passengersobj;
    }



}
