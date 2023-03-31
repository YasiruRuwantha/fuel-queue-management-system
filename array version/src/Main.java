import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static java.io.File.*;
import static java.nio.file.Files.readAllLines;

class Main {
    static int fuel = 6600;
    public static void main(String[] args) {

        int fuel2 = 0;
        //Creating Arrays for each 3 queues
        String option = "";

        String[] queue1 = new String[6];
        for (int x = 0; x < 6; x++) {
            queue1[x] = " empty";
        }
        String[] queue2 = new String[6];
        for (int x = 0; x < 6; x++) {
            queue2[x] = " empty";
        }
        String[] queue3 = new String[6];
        for (int x = 0; x < 6; x++) {
            queue3[x] = " empty";
        }

        loop:
        while (true) {

            System.out.print("100 or VFQ: View all Fuel Queues.\n101 or VEQ: View all empty Queues.\n102 or ACQ: Add customer to a Queue.\n103 or RCQ: Remove a customer from a Queue. (From a specific location)\n104 or PCQ: Remove a served customer.\n105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)\n106 or SPD: Store Program Data into file.\n107 or LPD: Load Program Data from file.\n108 or STK: View Remaining Fuel Stock.\n109 or AFS: Add Fuel Stock.\n999 or EXT: Exit the Program.\n>>>>");
            Scanner scanner = new Scanner(System.in);
            option = scanner.next();
            switch (option) {
                case "100":
                case "VFQ":
                    System.out.println("Queue 1 - " + Arrays.toString(queue1));
                    System.out.println("Queue 2 - " + Arrays.toString(queue2));
                    System.out.println("Queue 3 - " + Arrays.toString(queue3));
                    break;

                case "101":
                case "VEQ":
                    Viewempty(queue1, queue2, queue3);
                    break;

                case "102":
                case "ACQ":
                    AddCustomer(queue1, queue2, queue3, fuel);
                    fuel2 = fuel;

                    break;

                case "103":
                case "RCQ":
                   RemoveCustomer(queue1,queue2,queue3,fuel2);
                    break;

                case "104":
                case "PCQ":
                    RemoveServed(queue1,queue2,queue3);
                    break;

                case "105":
                case "VCS":
                    Sort(queue1,queue2,queue3);
                    break;

                case "106":
                case "SPD":
                    StoreFile(queue1,queue2,queue3,fuel,fuel2);
                    break;

                case "107":
                case "LPD":
                    LoadFile(queue1,queue2,queue3);
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


                case "999":
                case "EXT":
                    System.out.println("Exited the Program.");
                    break loop;

                default:
                    System.out.println("Invalid Input!!");
            }}}
    public static void Viewempty(String queue1[], String queue2[], String queue3[]) {
        int x = 0;
        int y = 0;
        int z = 0;
        //checking the queue1 if the array equals empty, if empty display those positions as empty
        while (x < 6) {
            if (queue1[x].equals(" empty")) {
                System.out.println("position " + x + " in pump 1 is empty");
            }
            x++;
        }
        while (y < 6) {
            if (queue2[y].equals(" empty")) {
                System.out.println("position " + y + " in pump 2 is empty");
            }
            y++;
        }
        while (z < 6) {
            if (queue3[z].equals(" empty")) {
                System.out.println("position " + z + " in pump 3 is empty");
            }
            z++;
        }}
    public static void AddCustomer(String queue1[], String queue2[], String queue3[],int fuel) {
        Scanner sc = new Scanner(System.in);
        Scanner name = new Scanner(System.in);
        Scanner decision = new Scanner(System.in);
        String pumpnum = " ";
        String again = " ";
        //asking whether the user want to add the customer to 1st,2nd or 3rd queue
        System.out.println(fuel);
        System.out.println("Which pump queue, you want to add? [1/2/3/] Press 0 to exit : ");
        pumpnum = sc.nextLine();
       //if the user enters 1, program asks the name then will ask if you want to add again to this queue if not exit.
        if (pumpnum.equals("1")) {
            for (int i = 0; i < queue1.length; i++) {
                if (queue1[i].equals(" empty")) {

                    System.out.print("Enter name of customer");
                    queue1[i] = name.nextLine();
                    fueldecrease();
                    fuelcheck();
                    System.out.println("Do you want to add another customer to this queue [Y/N]");
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

                } }}else if (pumpnum.equals("2")) {
            for (int j = 0; j < queue1.length; j++) {
                if (queue2[j].equals(" empty")) {
                    System.out.print("Enter name of customer");
                    queue2[j] = name.nextLine();
                    fueldecrease();
                    fuelcheck();
                    System.out.println("Do you want to add another customer to this queue [Y/N]");
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

                }}} else if (pumpnum.equals("3")) {
            for (int k = 0; k < queue1.length; k++) {
                if (queue3[k].equals(" empty")) {
                    System.out.print("Enter name of customer");
                    queue3[k] = name.nextLine();
                    fueldecrease();
                    fuelcheck();
                    System.out.println("Do you want to add another customer to this queue [Y/N]");
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
                } }}else if (pumpnum.equals("0")){
            System.out.println("Program exited Successfully");
        }
        else{
            System.out.println("Invalid option");
            AddCustomer(queue1,queue2,queue3,fuel);
        }
    }
    public static void fueldecrease() {
        int fuelc = fuel;
        fuelc = fuelc - 10;
        fuel = fuelc;
        System.out.println("Remaining Fuel is "+ fuelc +" L");
    }

    public static void fuelincrease() {
        int fuelc = fuel;
        fuelc = fuelc + 10;
        fuel = fuelc;
        System.out.println("Remaining Fuel is "+ fuelc +" L");
    }
    public static void fuelcheck() {
        if (fuel == 500) {
            System.out.println("\nWARNING : Remaining Fuel stock is  500 l.\n");
        } else if (fuel < 500) {
            System.out.println("\nWARNING : Remaining Fuel stock is " + fuel + " l.\n");
        }
    }
    public static void fuelrestore(){
        fuel = 6600;
    }
    public static void RemoveCustomer(String queue1[], String queue2[], String queue3[],int fuel) {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        String pumpnum = " ";
        Scanner decision = new Scanner(System.in);
        String again = "";
        System.out.println("In which Queue you want to remove a customer [1/2/3/] Press 0 to exit : ");
        pumpnum = sc.nextLine();

        //if the user enters 1, program asks the name then will ask if you want to add again to this queue if not exit.
        if (pumpnum.equals("1")) {
            for (int x = 0; x < queue1.length; x++) {
                if (!queue1[x].equals(" empty")) {
                    System.out.println("Enter the Customer's number you want to delete?[0,1,2,3,4,5,] : ");
                    for(int i=0;i<6;i++) {
                        System.out.println(i + ": " + queue1[i]);
                    }
                    int customername = sc1.nextInt();
                    for(int i = customername+1; i<queue1.length; i++) {
                        queue1[i-1] = queue1[i];
                    }
                    fuelincrease();
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
                else{
                    System.out.println("This queue is empty");
                }
    }} else if (pumpnum.equals("2")) {
            for (int x = 0; x < queue2.length; x++) {
                if (!queue2[x].equals(" empty")) {
                    System.out.println("Enter the Customer's number you want to delete?[0,1,2,3,4,5,] : ");
                    for(int i=0;i<6;i++) {
                        System.out.println(i + ": " + queue2[i]);
                    }
                    int customername = sc1.nextInt();
                    for(int i = customername+1; i<queue2.length; i++) {
                        queue2[i-1] = queue2[i];
                    }
                    fuelincrease();
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
                else{
                    System.out.println("This queue is empty");
                }
    }} else if (pumpnum.equals("3")) {
            for (int x = 0; x < queue3.length; x++) {
                if (!queue3[x].equals(" empty")) {
                    System.out.println("Enter the Customer's number you want to delete?[0,1,2,3,4,5,] : ");
                    for(int i=0;i<6;i++) {
                        System.out.println(i + ": " + queue3[i]);
                    }
                    int customername = sc1.nextInt();
                    for(int i = customername+1; i<queue3.length; i++) {
                        queue3[i-1] = queue3[i];
                    }
                    fuelincrease();
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
                    }}
                else{
                    System.out.println("This queue is empty");
                }
    }
        }
        else{
            System.out.println("Invalid option");
            RemoveCustomer(queue1,queue2,queue3,fuel);
        }
    }
    public static void RemoveServed(String queue1[], String queue2[], String queue3[]){
    Scanner sc = new Scanner(System.in);
    int index = 0;
    int queueNum = 0;
    System.out.println("In which Queue you want to remove a served customer [1/2/3/] Press 0 to exit : ");
    queueNum = sc.nextInt();
    //if user selects 1, program will remove the first element from the queue1 and shift others to left by one,
    // if user selects 2 or 3 same thing will happen to queue 2 and queue 3
    if (queueNum == 1){
        for (int i = index; i < queue1.length - 1; i++) {
            queue1[i] = queue1[i + 1];
        }
        //replace the deleted one with " empty".
        queue1[queue1.length - 1] = " empty";

} else if (queueNum == 2) {
        for (int i = index; i < queue2.length - 1; i++) {
            queue2[i] = queue2[i + 1];
    }
        queue2[queue2.length - 1] = " empty";
} else if (queueNum == 3) {
        for (int i = index; i < queue3.length - 1; i++) {
            queue3[i] = queue3[i + 1];
        }
        queue3[queue3.length - 1] = " empty";
    }
    else{
        System.out.println("Invalid Option");
        RemoveServed(queue1,queue2,queue3);
    }
    }
    public static void Sort(String queue1[], String queue2[], String queue3[]){
     String[] temp1 = Arrays.copyOf (queue1, queue1.length);
     String[] temp2 = Arrays.copyOf (queue2, queue2.length);
     String[] temp3 = Arrays.copyOf (queue3, queue3.length);

    //logic for sorting
    for(int i = 0; i<6; i++){
        for (int j = i+1; j<6; j++)  //Check for remaining elements
        {//compares each elements of the array to all the remaining elements
            if(temp1[i].compareTo(temp1[j])>0)
            {//swapping array elements
                String temp = temp1[i];
                temp1[i] = temp1[j];
                temp1[j] = temp;
            }}}
    //prints the sorted array in alphabetical order
    System.out.println("Queue 1 = "+Arrays.toString(temp1));

        //queue2
        for(int i = 0; i<6; i++){
            for (int j = i+1; j<6; j++){
                if(temp2[i].compareTo(temp2[j])>0)
                {//swapping array elements
                    String temp = temp2[i];
                    temp2[i] = temp2[j];
                    temp2[j] = temp;
                }}}
        System.out.println("Queue 2 = "+Arrays.toString(temp2));

        //queue3
        for(int i = 0; i<6; i++){
            for (int j = i+1; j<6; j++) {
                if(temp3[i].compareTo(temp3[j])>0)
                {//swapping array elements
                    String temp = temp3[i];
                    temp3[i] = temp3[j];
                    temp3[j] = temp;
                }}}
        System.out.println("Queue 3 = "+Arrays.toString(temp3));

}

    public static void StoreFile(String queue1[], String queue2[], String queue3[],int fuel, int fuelc){
    File fout = new File("data.txt");
    try (FileWriter fileWriter = new FileWriter(fout);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

        for(String str: queue1)
        {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }
        for(String str: queue2)
        {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }
        for(String str: queue3)
        {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }
        bufferedWriter.write(Integer.toString(fuel));
        System.out.println("Data has been stored successfully.");
    } catch (FileNotFoundException e) {
        System.out.println("Unable to open file, file not found.");
    } catch (IOException e) {
        System.out.println("Unable to write to file." + fout.getName());
    }
}
    public static void LoadFile(String queue1[], String queue2[], String queue3[]){

        try {
            Scanner sc = new Scanner(new File("array version/data.txt"));
            for (int i = 0; i < 6; i++) {
                queue1[i] = sc.nextLine();
            }
            for (int i = 0; i < 6; i++) {
                queue2[i] = sc.nextLine();
            }
            for (int i = 0; i < 6; i++) {
                queue3[i] = sc.nextLine();
            }
            int fuels = Integer.parseInt(sc.nextLine());
            fuel = fuels;
            System.out.println("File has been loaded successfully.");
        }
     catch (FileNotFoundException e){
         System.out.println("Unable to open file, file not found.");
}}}







































