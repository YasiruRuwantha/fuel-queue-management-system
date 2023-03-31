import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public class FuelStation {
    //fuel count
    static int fuel = 6600;

    //arraylists for the waiting queue
    public static ArrayList<String> waitingQueueFirstName = new ArrayList<String>();
    public static ArrayList<String> waitingQueueSecondName = new ArrayList<String>();
    public static ArrayList<String> waitingQueueVehicleNo = new ArrayList<String>();
    public static ArrayList<Integer> waitingQueueNumOfLiters = new ArrayList<Integer>();

    //variables for calculating the income of each queue
    public static int total_amount,pump0Inc= 0,pump1Inc = 0,pump2Inc = 0,pump3Inc = 0,pump4Inc = 0;

    public static void main(String[] args) {

        //queue objects
       FuelQueue queue1 = new FuelQueue();
        FuelQueue queue2 = new FuelQueue();
       FuelQueue queue3 = new FuelQueue();
        FuelQueue queue4 = new FuelQueue();
       FuelQueue queue5 = new FuelQueue();

       
        int fuel2 = 0;
        //Creating Arrays for each 5 queues
        FuelQueue[] combinedArray  = new FuelQueue[]{queue1, queue2, queue3, queue4, queue5};


        loop:
        while (true) {

            System.out.print("100 or VFQ: View all Fuel Queues.\n101 or VEQ: View all empty Queues.\n102 or ACQ: Add customer to a Queue.\n103 or RCQ: Remove a customer from a Queue. (From a specific location)\n104 or PCQ: Remove a served customer.\n105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)\n106 or SPD: Store Program Data into file.\n107 or LPD: Load Program Data from file.\n108 or STK: View Remaining Fuel Stock.\n109 or AFS: Add Fuel Stock.\n110 or IFQ: View Income of each queue.\n999 or EXT: Exit the Program.\n>>>>");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();
            switch (option) {
                case "100":
                case "VFQ":
                     Viewall(combinedArray);
                    break;

                case "101":
                case "VEQ":
                    Viewempty(combinedArray);
                    break;

                case "102":
                case "ACQ":
                    AddCustomer(combinedArray);
                    fuel2 = fuel;

                    break;

                case "103":
                case "RCQ":
                    removeCustomer(combinedArray);
                    break;

                case "104":
                case "PCQ":
                    removeServed(combinedArray);
                    break;

                case "105":
                case "VCS":
                    sortingAlphabetic(combinedArray);
                    break;

                case "106":
                case "SPD":
                    StoreFile(combinedArray);
                    break;

                case "107":
                case "LPD":
                    LoadFile(combinedArray);
                    break;

                case "108":
                case "STK":
                    System.out.println("Remaining Fuel is " + fuel + " l");
                    break;

                case "109":
                case "AFS":
                    Scanner inp = new Scanner(System.in);
                    System.out.println("---------------------Add Fuel Stock.-------------------------\n");
                    //getting input from user
                    System.out.print("\nHow much fuel liters you want to add?");
                    int add = inp.nextInt();

                    int amount = fuel+add;
                    if((amount) >= 6600){
                        fuel = 6600;
                        System.out.println("The Maximum fuel stock has been reached. remaining has been removed. new fuel liter amount is "+ fuel+ " l" +"\n");
                    }
                    else{
                        fuel+=add;
                        System.out.println("The fuel stock has been added successfully. new stock is " + fuel + " l" + "\n");
                    }
                    break;

                case "110":
                case "IFQ":
                    //printing the income of each queue
                    System.out.println("Queue 0 : RS."+ pump0Inc);
                    System.out.println("Queue 1 : RS."+ pump1Inc);
                    System.out.println("Queue 2 : RS."+ pump2Inc);
                    System.out.println("Queue 3 : RS."+ pump3Inc);
                    System.out.println("Queue 4 : RS."+ pump4Inc);

                    break;

                case "999":
                case "EXT":
                    System.out.println("Exited the Program.");
                    break loop;

                default:
                    System.out.println("Invalid Input!!");
            }}}

    public static void Viewall(FuelQueue[] combinedArray){
        //view all the 6 customers in these 5 queues.
        for(int k=0; k<5;k++){
            if(k==0){System.out.println("---------------------Queue 1------------------------\n");}
            else if(k==1){System.out.println("---------------------Queue 2------------------------\n");}
            else if(k==2){System.out.println("---------------------Queue 3------------------------\n");}
            else if(k==3){System.out.println("---------------------Queue 4------------------------\n");}
            else{System.out.println("---------------------Queue 5------------------------\n");}
            for(int j=0; j<6;j++){
                System.out.println("Passenger" + j);
                System.out.println("First Name: "+ combinedArray[k].getPassengernum(j).getFirst_Name());
                System.out.println("Second Name: "+combinedArray[k].getPassengernum(j).getSec_Name());
                System.out.println("Vehicle No: "+combinedArray[k].getPassengernum(j).getVehicle_No());
                System.out.println("Liters requested: "+combinedArray[k].getPassengernum(j).getReq_fuel()+"\n");
            }
        }
    }



    public static void Viewempty(FuelQueue[] combinedArray) {
        //finding the empty positions of each queue
        for(int i = 0; i<combinedArray.length; i++) {
            for(int j = 0; j<6; j++) {
                if (combinedArray[i].getPassengernum(j).getFirst_Name().equals(" empty")) {
                    System.out.println("position " + j + " in pump "+ (i+1)+ " is empty");

                    }
                }
        }

        }

    public static void AddCustomer(FuelQueue[] combinedArray) {
        Scanner input = new Scanner(System.in);

        //calling the sort
        FindingLowestNum();

        //requesting inputs
        System.out.println("Enter First name of Passenger ");
        String passengerFirstname = input.nextLine();

        System.out.println("Enter Second name of Passenger ");
        String passengerSecondname = input.nextLine();

        System.out.println("Enter Vehicle number of passenger ");
        String passengerVehiclenum = input.nextLine();

        System.out.println("Enter the amount of fuel the passenger need ");
        String passengeReq = String.valueOf(input.nextLine());


       //checking if the final position of the final pump is empty if not the inputs are going to the waiting list
        if(!combinedArray[4].getPassengernum(5).getFirst_Name().equals(" empty")) {
            waitingQueueFirstName.add(passengerFirstname);
            waitingQueueSecondName.add(passengerSecondname);
            waitingQueueVehicleNo.add(passengerVehiclenum);
            waitingQueueNumOfLiters.add(Integer.valueOf(passengeReq));
            System.out.println("All the queues are full. Added to a waiting list.");

        }
            if (counter1 == below_number[0]) {
                for (int k = 0; k < 6; k++) {

                    if (combinedArray[0].getPassengernum(k).getFirst_Name().equals(" empty")) {

                        combinedArray[0].getPassengernum(k).setFirst_Name(passengerFirstname);
                        combinedArray[0].getPassengernum(k).setSec_Name(passengerSecondname);
                        combinedArray[0].getPassengernum(k).setVehicle_No(passengerVehiclenum);
                        combinedArray[0].getPassengernum(k).setReq_fuel(Double.parseDouble(passengeReq));
                        fueldecrease(Double.parseDouble(passengeReq));
                        pump0Inc+=430*Integer.parseInt(passengeReq);

                        System.out.println("Customer added successfully");
                        fuelcheck();

                        counter1++;
                        break;
                    }

                }
            } else if (counter2 == below_number[0]) {
                for (int k = 0; k < 6; k++) {

                    if (combinedArray[1].getPassengernum(k).getFirst_Name().equals(" empty")) {

                        combinedArray[1].getPassengernum(k).setFirst_Name(passengerFirstname);
                        combinedArray[1].getPassengernum(k).setSec_Name(passengerSecondname);
                        combinedArray[1].getPassengernum(k).setVehicle_No(passengerVehiclenum);
                        combinedArray[1].getPassengernum(k).setReq_fuel(Double.parseDouble(passengeReq));
                        fueldecrease(Double.parseDouble(passengeReq));
                        pump1Inc+=430*Integer.parseInt(passengeReq);

                        System.out.println("Customer added successfully");
                        fuelcheck();

                        counter2++;
                        break;
                    }

                }

            } else if (counter3 == below_number[0]) {
                for (int k = 0; k < 6; k++) {

                    if (combinedArray[2].getPassengernum(k).getFirst_Name().equals(" empty")) {

                        combinedArray[2].getPassengernum(k).setFirst_Name(passengerFirstname);
                        combinedArray[2].getPassengernum(k).setSec_Name(passengerSecondname);
                        combinedArray[2].getPassengernum(k).setVehicle_No(passengerVehiclenum);
                        combinedArray[2].getPassengernum(k).setReq_fuel(Double.parseDouble(passengeReq));
                        fueldecrease(Double.parseDouble(passengeReq));
                        pump2Inc+=430*Integer.parseInt(passengeReq);

                        System.out.println("Customer added successfully");
                        fuelcheck();

                        counter3++;
                        break;
                    }

                }

            } else if (counter4 == below_number[0]) {
                for (int k = 0; k < 6; k++) {

                    if (combinedArray[3].getPassengernum(k).getFirst_Name().equals(" empty")) {

                        combinedArray[3].getPassengernum(k).setFirst_Name(passengerFirstname);
                        combinedArray[3].getPassengernum(k).setSec_Name(passengerSecondname);
                        combinedArray[3].getPassengernum(k).setVehicle_No(passengerVehiclenum);
                        combinedArray[3].getPassengernum(k).setReq_fuel(Double.parseDouble(passengeReq));
                        fueldecrease(Double.parseDouble(passengeReq));
                        pump3Inc+=430*Integer.parseInt(passengeReq);

                        System.out.println("Customer added successfully");
                        fuelcheck();

                        counter4++;
                        break;
                    }

                }

            } else if (counter5 == below_number[0]) {
                for (int k = 0; k < 6; k++) {

                    if (combinedArray[4].getPassengernum(k).getFirst_Name().equals(" empty")) {

                        combinedArray[4].getPassengernum(k).setFirst_Name(passengerFirstname);
                        combinedArray[4].getPassengernum(k).setSec_Name(passengerSecondname);
                        combinedArray[4].getPassengernum(k).setVehicle_No(passengerVehiclenum);
                        combinedArray[4].getPassengernum(k).setReq_fuel(Double.parseDouble(passengeReq));
                        fueldecrease(Double.parseDouble(passengeReq));
                        pump4Inc+=430*Integer.parseInt(passengeReq);

                        System.out.println("Customer added successfully");
                        fuelcheck();

                        counter5++;
                        break;
                    }

                }

            }else{
                System.out.println("Error!!");
            }
    }

    public static void fueldecrease(double passengeReq) {
        int fuelc = fuel;
        fuelc = (int) (fuelc - passengeReq);
        fuel = fuelc;
        System.out.println("Remaining Fuel is "+ fuelc +" L");
    }

        public static void removeCustomer(FuelQueue[] combinedArray) {
            Scanner input = new Scanner(System.in);
            Scanner indexsc = new Scanner(System.in);
            String again = "";
            Scanner decision = new Scanner(System.in);

            System.out.println("in which queue you want to remove a customer? [0/1/2/3/4/ 0] : "); //asking the queue number to delete (each queue has 6 passengers)
            int pumpnum = input.nextInt();

                    if (pumpnum == 0 || pumpnum == 1 || pumpnum == 2 || pumpnum == 3 || pumpnum == 4) {
                        for(int k = 0; k<combinedArray.length; k++) {
                            for (int j = 0; j < 6; j++) {
                                if (!(combinedArray[pumpnum].getPassengernum(j).getFirst_Name()).equals(" empty")) {
                                System.out.println(j + ": " + (combinedArray[pumpnum].getPassengernum(j).getFirst_Name()));
                                }

                            }
                                System.out.println("\n");
                                System.out.println("Enter the Customer's number you want to delete?[0,1,2,3,4,5] : ");
                                int customerIndex = indexsc.nextInt();
                                //decreasing the income if someone got removed from the queue
                                if(pumpnum==0){
                                    pump0Inc-=430*combinedArray[0].getPassengernum(customerIndex).getReq_fuel();
                                    System.out.println(pump0Inc);
                                }
                                else if(pumpnum==1){
                                    pump1Inc-=430*combinedArray[1].getPassengernum(customerIndex).getReq_fuel();
                                    System.out.println(pump1Inc);
                                }
                                else if(pumpnum==2){
                                    pump2Inc-=430*combinedArray[2].getPassengernum(customerIndex).getReq_fuel();
                                    System.out.println(pump2Inc);
                                }
                                else if(pumpnum==3){
                                    pump3Inc-=430*combinedArray[3].getPassengernum(customerIndex).getReq_fuel();
                                    System.out.println(pump3Inc);
                                }
                                else if(pumpnum==4){
                                    pump4Inc-=430*combinedArray[4].getPassengernum(customerIndex).getReq_fuel();
                                    System.out.println(pump4Inc);
                                }
                       //increasing the fuel count if someone got removed from the queue
                                if(pumpnum==0) {
                                    fuel += combinedArray[0].getPassengernum(customerIndex).getReq_fuel();
                                }
                                else if(pumpnum==1) {
                                        fuel += combinedArray[1].getPassengernum(customerIndex).getReq_fuel();
                                    }
                                else if(pumpnum==2) {
                                        fuel += combinedArray[2].getPassengernum(customerIndex).getReq_fuel();
                                    }
                                else if(pumpnum==3) {
                                        fuel += combinedArray[3].getPassengernum(customerIndex).getReq_fuel();
                                    }
                                else if(pumpnum==4) {
                                        fuel += combinedArray[4].getPassengernum(customerIndex).getReq_fuel();
                                    }




                                combinedArray[pumpnum].getPassengernum(customerIndex).setFirst_Name(" empty");
                                combinedArray[pumpnum].getPassengernum(customerIndex).setSec_Name(" empty");
                                combinedArray[pumpnum].getPassengernum(customerIndex).setVehicle_No(" empty");
                                combinedArray[pumpnum].getPassengernum(customerIndex).setReq_fuel(0.0);

                           for (int i = customerIndex; i < 5; i++) {
                                combinedArray[pumpnum].getPassengernum(i).setFirst_Name(combinedArray[pumpnum].getPassengernum(i + 1).getFirst_Name());
                                combinedArray[pumpnum].getPassengernum(i).setSec_Name(combinedArray[pumpnum].getPassengernum(i + 1).getSec_Name());
                                combinedArray[pumpnum].getPassengernum(i).setVehicle_No(combinedArray[pumpnum].getPassengernum(i + 1).getVehicle_No());
                                combinedArray[pumpnum].getPassengernum(i).setReq_fuel(combinedArray[pumpnum].getPassengernum(i + 1).getReq_fuel());
                                }
                                combinedArray[pumpnum].getPassengernum(5).setFirst_Name(" empty");
                                combinedArray[pumpnum].getPassengernum(5).setSec_Name(" empty");
                                combinedArray[pumpnum].getPassengernum(5).setVehicle_No(" empty");
                                combinedArray[pumpnum].getPassengernum(5).setReq_fuel(0.0);

                                System.out.println("Do you want to remove another customer in this queue [Y/N]");
                                again = decision.nextLine();
                                if (again.equals("n")){
                                    break;
                                }
                                else if(again.equals("y")){
                                    continue;
                                }
                                else {
                                    System.out.println("Invalid input");
                                }

                            }

                        }else{
                            System.out.println("Invalid input");
                        }
                        }

 public static void removeServed(FuelQueue[] combinedArray){
     Scanner input = new Scanner(System.in);
     Scanner indexsc = new Scanner(System.in);
     String again = "";
     Scanner decision = new Scanner(System.in);
     Scanner sc = new Scanner(System.in);


     System.out.println("In which Queue you want to remove a served customer [0/1/2/3/4/ 0] : "); //asking the queue number to delete (each queue has 6 passengers)
     int pumpnum = input.nextInt();

     if (Objects.equals(combinedArray[pumpnum].getPassengernum(0).getFirst_Name(), " empty")) {
         System.out.println("there is no customer at the given queue");
     } else {



         if (pumpnum == 0 || pumpnum == 1 || pumpnum == 2 || pumpnum == 3 || pumpnum == 4) {



                 int customerIndex = 0;

                 combinedArray[pumpnum].getPassengernum(customerIndex).setFirst_Name(" empty");
                 combinedArray[pumpnum].getPassengernum(customerIndex).setSec_Name(" empty");
                 combinedArray[pumpnum].getPassengernum(customerIndex).setVehicle_No(" empty");
                 combinedArray[pumpnum].getPassengernum(customerIndex).setReq_fuel(0.0);

                 for (int i = customerIndex; i < 5; i++) {
                     combinedArray[pumpnum].getPassengernum(i).setFirst_Name(combinedArray[pumpnum].getPassengernum(i + 1).getFirst_Name());
                     combinedArray[pumpnum].getPassengernum(i).setSec_Name(combinedArray[pumpnum].getPassengernum(i + 1).getSec_Name());
                     combinedArray[pumpnum].getPassengernum(i).setVehicle_No(combinedArray[pumpnum].getPassengernum(i + 1).getVehicle_No());
                     combinedArray[pumpnum].getPassengernum(i).setReq_fuel(combinedArray[pumpnum].getPassengernum(i + 1).getReq_fuel());
                 }
                 combinedArray[pumpnum].getPassengernum(5).setFirst_Name(" empty");
                 combinedArray[pumpnum].getPassengernum(5).setSec_Name(" empty");
                 combinedArray[pumpnum].getPassengernum(5).setVehicle_No(" empty");
                 combinedArray[pumpnum].getPassengernum(5).setReq_fuel(0.0);




         }else{
             System.out.println("Invalid input");
         }
         for(int i=0;i<6;i++) {
             if (combinedArray[pumpnum].getPassengernum(i).getFirst_Name().equals(" empty") && waitingQueueFirstName.size() != 0) {
                 combinedArray[pumpnum].getPassengernum(i).setFirst_Name(waitingQueueFirstName.get(0));
                 combinedArray[pumpnum].getPassengernum(i).setSec_Name(waitingQueueSecondName.get(0));
                 combinedArray[pumpnum].getPassengernum(i).setVehicle_No(waitingQueueVehicleNo.get(0));
                 combinedArray[pumpnum].getPassengernum(i).setReq_fuel(waitingQueueNumOfLiters.get(0));
                 waitingQueueFirstName.remove(0);
                 waitingQueueSecondName.remove(0);
                 waitingQueueVehicleNo.remove(0);
                 waitingQueueNumOfLiters.remove(0);
                 break;
             }
         }


             }

 }

 public static void sortingAlphabetic(FuelQueue[] combinedArray) {
     ArrayList<String> alpha = new ArrayList<>();

     for(int k = 0; k<6;k++) {
         alpha.add(combinedArray[0].getPassengernum(k).getFirst_Name());
         String temp;
         for (int i = 0; i < alpha.size(); i++) {
             for (int j = i + 1; j < alpha.size(); j++) {
                 if (alpha.get(i).compareTo(alpha.get(j)) > 0) {
                     temp = alpha.get(i);
                     alpha.set(i, alpha.get(j));
                     alpha.set(j, temp);
                 }
             }
         }
     }
     System.out.println("Sorted Queue 0 : " + alpha.toString());

     ////////////////////////////////////////////1

     ArrayList<String> alpha1 = new ArrayList<>();

     for(int k = 0; k<6;k++) {
         alpha1.add(combinedArray[1].getPassengernum(k).getFirst_Name());
         String temp;
         for (int i = 0; i < alpha1.size(); i++) {
             for (int j = i + 1; j < alpha1.size(); j++) {
                 if (alpha1.get(i).compareTo(alpha1.get(j)) > 0) {
                     temp = alpha1.get(i);
                     alpha1.set(i, alpha1.get(j));
                     alpha1.set(j, temp);
                 }
             }
         }
     }
     System.out.println("Sorted Queue 1 : " + alpha1.toString());

     ///////////////////////////////////////////2

     ArrayList<String> alpha2 = new ArrayList<>();

     for(int k = 0; k<6;k++) {
         alpha2.add(combinedArray[2].getPassengernum(k).getFirst_Name());
         String temp;
         for (int i = 0; i < alpha2.size(); i++) {
             for (int j = i + 1; j < alpha2.size(); j++) {
                 if (alpha2.get(i).compareTo(alpha2.get(j)) > 0) {
                     temp = alpha2.get(i);
                     alpha2.set(i, alpha2.get(j));
                     alpha2.set(j, temp);
                 }
             }
         }
     }
     System.out.println("Sorted Queue 2 : " +alpha2.toString());

     ////////////////////////////////////////////////3

     ArrayList<String> alpha3 = new ArrayList<>();

     for(int k = 0; k<6;k++) {
         alpha3.add(combinedArray[3].getPassengernum(k).getFirst_Name());
         String temp;
         for (int i = 0; i < alpha3.size(); i++) {
             for (int j = i + 1; j < alpha3.size(); j++) {
                 if (alpha3.get(i).compareTo(alpha3.get(j)) > 0) {
                     temp = alpha3.get(i);
                     alpha3.set(i, alpha3.get(j));
                     alpha3.set(j, temp);
                 }
             }
         }
     }
     System.out.println("Sorted Queue 3 : " + alpha3.toString());
////////////////////////////////////////////////////////////////4
     ArrayList<String> alpha4 = new ArrayList<>();

     for(int k = 0; k<6;k++) {
         alpha4.add(combinedArray[4].getPassengernum(k).getFirst_Name());
         String temp;
         for (int i = 0; i < alpha4.size(); i++) {
             for (int j = i + 1; j < alpha4.size(); j++) {
                 if (alpha4.get(i).compareTo(alpha4.get(j)) > 0) {
                     temp = alpha4.get(i);
                     alpha4.set(i, alpha4.get(j));
                     alpha4.set(j, temp);
                 }
             }
         }
     }
     System.out.println("Sorted Queue 4 : " + alpha4.toString() + "\n");


 }
 
    public static int counter1 = 0;
    public static int counter2 = 0;
    public static int counter3 = 0;
    public static int counter4 = 0;
    public static int counter5 = 0;
    public static int below_number[] = new int[5];

    public static void FindingLowestNum() {
        int count = 0;
        int tempo = 0;
        below_number[0] = counter1;
        below_number[1] = counter2;
        below_number[2] = counter3;
        below_number[3] = counter4;
        below_number[4] = counter5;

        boolean fo = true;

//doing a bubble sort to figure out what's the lowest
        while (fo) {
            if (below_number[0] > below_number[1]) {
                tempo = below_number[0];
                below_number[0] = below_number[1];
                below_number[1] = tempo;
            }
            if (below_number[1] > below_number[2]) {
                tempo = below_number[2];
                below_number[2] = below_number[1];
                below_number[1] = tempo;
            }
            if (below_number[2] > below_number[3]) {
                tempo = below_number[2];
                below_number[2] = below_number[3];
                below_number[3] = tempo;
            }
            if (below_number[3] > below_number[4]) {
                tempo = below_number[3];
                below_number[3] = below_number[4];
                below_number[4] = tempo;
            }
            count++;
            if (25 == count) {
                fo = false;
            }

        }
    }


    public static void fuelcheck() {
        //checking the fuel count for make a warning
        if (fuel == 500) {
            System.out.println("\nWARNING : Remaining Fuel stock is  500 l.\n");
        } else if (fuel < 500) {
            System.out.println("\nWARNING : Remaining Fuel stock is " + fuel + " l.\n");
        }
    }


    public static void StoreFile(FuelQueue[] combinedArray) {
        //storing data to a text file
        try {
            File txt = new File("store.txt");
            PrintWriter TextWriter = new PrintWriter(txt);
            for (int i = 0; i < combinedArray.length; i++) {
                for (int k = 0; k < 6; k++) {
                    TextWriter.println(combinedArray[i].getPassengernum(k).getFirst_Name());
                    TextWriter.println(combinedArray[i].getPassengernum(k).getSec_Name());
                    TextWriter.println(combinedArray[i].getPassengernum(k).getVehicle_No());
                    TextWriter.println(combinedArray[i].getPassengernum(k).getReq_fuel());
                }
            }
            //adding waiting list
            TextWriter.println();
            int sizeNum = waitingQueueFirstName.size() - 1;
            TextWriter.println("Waiting list");
            for (int count = 0; count < sizeNum; count++) {
                TextWriter.println(count + ": " + waitingQueueFirstName.get(sizeNum) + " " + waitingQueueSecondName.get(sizeNum));
                TextWriter.println("Vehicle number :" + waitingQueueVehicleNo.get(sizeNum) + ", Number of liters ordered :" + waitingQueueNumOfLiters.get(sizeNum));
            }
            TextWriter.close();
            System.out.println("\nSuccessfuly Saved data into file");
         //exceptions
        } catch (FileNotFoundException e) {
            System.out.println("Unknown error");
        }
    }

    public static void LoadFile(FuelQueue[] combinedArray) {
        //read and load the textfile
        try {
            FileReader reader = new FileReader("store.txt");
                Scanner read = new Scanner(reader);
                for (int i = 0; i < combinedArray.length; i++) {
                    for (int k = 0; k < 6; k++) {
                    combinedArray[i].getPassengernum(k).setFirst_Name(read.nextLine());
                    combinedArray[i].getPassengernum(k).setSec_Name(read.nextLine());
                    combinedArray[i].getPassengernum(k).setVehicle_No(read.nextLine());
                    combinedArray[i].getPassengernum(k).setReq_fuel(Double.parseDouble(read.nextLine()));
                    }
                }
                    System.out.println("File has loaded successfully.");

                    reader.close();
           //exceptions
            } catch (FileNotFoundException e) {
                System.out.println("File not found");

            } catch (IOException e) {
                System.out.println("Unknown error");

            }
    }
}








































