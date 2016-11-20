import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
public class UserAction {

  private GeneralUtility utility = new GeneralUtility();
  private DateUtility dateutility = new DateUtility();

  public void purgeSystem() {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String currentDate = dateutility.convertDateToString(date);
    String index;
    Date reservationDate;
    ArrayList<Reservation> reservationInfo = reader.getReservationInfo();

    for(int i = 1; i < reservationInfo.size(); i++) {
      reservationDate = dateutility.convertStringToDate(reservationInfo.get(i).getDate());
      long secs = (reservationDate.getTime() - date.getTime()) / 1000;
      long hours = secs / 3600;
      long days = hours / 24;
      if(days > 31) {
        reader.purgeReservation(reservationInfo.get(i).getNumber());
      }
    }

    System.out.println("\nSystem is up to date.");

  }

  public void checkIn() {

    CheckInWriter checkInWriter = new CheckInWriter("CheckIns.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = dateutility.convertDateToString(date);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    checkInWriter.write(number, stringDate);

  }

  public void checkOut() {

    CheckOutWriter checkOutWriter = new CheckOutWriter("CheckOuts.csv");
    CheckInWriter checkInWriter = new CheckInWriter("CheckIns.csv");
    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = dateutility.convertDateToString(date);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    checkOutWriter.write(number, stringDate);
    checkInWriter.deleteLine(number);

  }

  public void makeReservation() {

    ArrayList<String> toBeWritten = new ArrayList<String>();
    ReservationWriter writer = new ReservationWriter("ReservationInfo.csv");
    ArrayList<String> roomTypes;
    String stringDate, choice, name, nights, rooms, deposit;
    Scanner in = new Scanner(System.in);
    boolean matchesDesired = false;

    String number = utility.checkNumber(matchesDesired);
    toBeWritten.add(number);

    String hotel = utility.getHotel();
    toBeWritten.add(hotel);

    String bookingType = utility.getType();
    toBeWritten.add(bookingType);

    System.out.print("Enter your name: ");
    name = in.nextLine();
    toBeWritten.add(name);

    System.out.print("Enter start date of reservation(dd-MM-yyyy): ");
    stringDate = in.nextLine();

    while(!dateutility.validateDate(stringDate)) {
      System.out.print("Invalid date format, try again: ");
      stringDate = in.nextLine();
    }

    Date date = dateutility.convertStringToDate(stringDate);
    int day = dateutility.getDayOfWeek(date);

    while(dateutility.checkIfBefore(date)) {
      System.out.print("Date must be after current date, try again: ");
      stringDate = in.nextLine();
      date = dateutility.convertStringToDate(stringDate);
    }
    toBeWritten.add(stringDate);

    System.out.print("Enter number of nights: ");
    nights = in.nextLine();
    toBeWritten.add(nights);

    System.out.print("Enter number of rooms: ");
    rooms = in.nextLine();
    toBeWritten.add(rooms);

    System.out.print("Enter the deposit: ");
    deposit = in.nextLine();
    toBeWritten.add(deposit);

    roomTypes = utility.getRoomType(hotel, rooms, day);

    String totalCost = Double.toString(utility.getRoomCosts(day, Integer.parseInt(nights)));
    toBeWritten.add(totalCost);

    for(int i = 0; i < roomTypes.size(); i++) {
      toBeWritten.add(roomTypes.get(i));
    }

    writer.write(toBeWritten);
    toBeWritten.clear();
    roomTypes.clear();
  }

  public void makeCancellation() {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    CancellationWriter writer = new CancellationWriter("CancellationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = dateutility.convertDateToString(date);
    String choice, name, nights, rooms, deposit;
    Scanner in = new Scanner(System.in);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    Date reservationDate = dateutility.convertStringToDate(reader.getDate(number));

    long secs = (reservationDate.getTime() - date.getTime()) / 1000;
    long hours = secs / 3600;

    double bill = reader.getBill(number);
    if(reader.checkType(number).equals("advanced")) {
      System.out.println("\nAdvanced purchase reservations are non-refundable!" +
      "\nCustomer should be charged full amount which is: " + bill);
      writer.cancelReservation (number, stringDate);
    }
    else  {
      if(hours > 48) {
        writer.cancelReservation (number, stringDate);
      }
      else {
        System.out.println("\nReservations cancelled less than 48 hours in advance are non-refundable!" +
        "\nCustomer should be charged full amount which is: " + bill);
        writer.cancelReservation (number, stringDate);
      }
    }
  }

  public void getDataAnalysis() {

    String stringDateStart, interval = "";
    Scanner in = new Scanner(System.in);

    System.out.print("Data analysis selected.");
    AnalyticGenerator analytics  = new AnalyticGenerator();

    System.out.println("Please select the hotel you wish to display analytics for: ");
    String hotel = utility.getHotel();

    System.out.print("Please enter a start date: ");
    stringDateStart = in.nextLine();
    while(!dateutility.validateDate(stringDateStart)) {
      System.out.print("Invalid date format, try again: ");
      stringDateStart = in.nextLine();
    }

    Date startDate = dateutility.convertStringToDate(stringDateStart);

    System.out.println("Please enter desired time interval: ");
    System.out.println("1. weekly intervals \n2. monthly intervals \n3. daily intervals");
    System.out.println("\nPlease enter your selection: ");
    int input = in.nextInt();
    if(input == 1) interval = "weekly";
    else if(input == 2) interval = "monthly";
    else if(input == 3) interval = "daily";
    else System.out.println("Invalid input.");

    System.out.print("How many intervals would you like displayed: ");
    int numIntervals = in.nextInt();

    analytics.generateAnalytics(hotel, startDate, interval, numIntervals);

  }
}
