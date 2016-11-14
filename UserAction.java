import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.io.*;
public class UserAction {

  private ReservationWriter writer = new ReservationWriter("ReservationInfo.csv");
  private ReservationReader reader = new ReservationReader("ReservationInfo.csv");
  private Scanner in = new Scanner(System.in);

  public void checkIn() {

    Reservation newReservation;
    CheckInWriter checkInWriter = new CheckInWriter("CheckIns.csv");
    Date date = Calendar.getInstance().getTime();
    String currentDate = convertDateToString(date);
    String number;
    int index;

    System.out.print("Please enter the reservation number you are checking in: ");
    number = in.nextLine();
    while(((index = reader.checkNumber(number)) == -1)) {
      System.out.print("Number does not exist. Please try again: ");
      number = in.nextLine();
    }

    checkInWriter.write(number, currentDate);

  }

  public void checkOut() {

    Reservation newReservation;
    CheckOutWriter checkOutWriter = new CheckOutWriter("CheckOuts.csv");
    CheckInWriter checkInWriter = new CheckInWriter("CheckIns.csv");
    Date date = Calendar.getInstance().getTime();
    String currentDate = convertDateToString(date);
    String number;
    int index;

    System.out.print("Please enter the reservation number you are checking out: ");
    number = in.nextLine();
    while(((index = reader.checkNumber(number)) == -1)) {
      System.out.print("Number does not exist. Please try again: ");
      number = in.nextLine();
    }

    checkOutWriter.write(number, currentDate);
    checkInWriter.deleteLine(number);

  }

  public void makeReservation() {

    Hotel hotel;
    Reservation newReservation;
    Date date = Calendar.getInstance().getTime();
    String currentDate = convertDateToString(date);
    String choice, number, name, nights, rooms, deposit, type="";
    int index, input;

    System.out.print("Is this a Simple or Advanced purchase booking? \n1. Simple \n2. Advanced\nPlease enter: ");
    input = in.nextInt();
    if(input == 1) {
      type = "simple";
    }
    else if(input == 2) {
      type = "advanced";
    }
    else {
      System.out.println("Invalid input.");
    }

    System.out.print("Select a hotel: \n1. 5 Star \n2. 4 Star \n3. 3 Star" +
    "\nPlease enter your selection: ");
    hotel = new Hotel(in.nextLine());

    System.out.print("Enter reservation number: ");
    number = in.nextLine();

    while(((index = reader.checkNumber(number)) != -1)) {
      System.out.print("Reservation number already exists. Please try again: ");
      number = in.nextLine();
    }

    System.out.print("Enter reservation name: ");
    name = in.nextLine();

    System.out.print("Enter number of nights: ");
    nights = in.nextLine();

    System.out.print("Enter number of rooms: ");
    rooms = in.nextLine();

    System.out.print("Enter the deposit: ");
    deposit = in.nextLine();

    newReservation = new Reservation(number, type, name, nights, rooms, deposit, currentDate);
    writer.write(number, type, name, nights, rooms, deposit, currentDate);
  }

  public void makeCancellation() {

    int index;
    Date date = Calendar.getInstance().getTime();
    String currentDate = convertDateToString(date);
    String choice, number, name, nights, rooms, deposit;

    System.out.print("Enter the number of the reservation you wish to cancel: ");
    number = in.nextLine();
    while(((index = reader.checkNumber(number)) == -1)) {
      System.out.print("Number does not exist. Please try again: ");
      number = in.nextLine();
    }

    Date reservationDate = convertStringToDate(reader.getDate(number));
    double hours = (date.getTime() - reservationDate.getTime()) / 1000 / 60 / 60;

    if(reader.checkType(number).equals("advanced")) {
      System.out.print("Advanced purchase reservations are non-refundable.");
    }
    else {
      if(hours > 48) {
        reader.deleteLine(number, currentDate);
      }
      else {
        System.out.print("Reservations cannot be cancelled less than 48 hours before arrival.");
      }
    }

  }

  public Date convertStringToDate(String date) {

    Date aDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    try {
      aDate = (Date)dateFormat.parse(date);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return aDate;

  }

  public String convertDateToString(Date date) {

    String strFormat;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    strFormat =  dateFormat.format(date);

    return strFormat;

  }

}
