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
    double days;
    Date reservationDate;
    ArrayList<Reservation> reservationInfo = reader.getReservationInfo();

    for(int i = 1; i < reservationInfo.size(); i++) {
      reservationDate = dateutility.convertStringToDate(reservationInfo.get(i).getDate());
      days = ((date.getTime() - reservationDate.getTime()) / (1000 * 60 * 60 * 24));
      if(days > 31) {
        reader.purgeReservations(i);
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
    Date date = Calendar.getInstance().getTime();
    String stringDate = dateutility.convertDateToString(date);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    checkOutWriter.write(number, stringDate);
    checkInWriter.deleteLine(number);

  }

  public void makeReservation() {

    ArrayList<String> toBeWritten = new ArrayList<String>();
    Reservation newReservation;
    ReservationWriter writer = new ReservationWriter("ReservationInfo.csv");
    ArrayList<String> roomTypes;
    String stringDate, choice, name, nights, rooms, deposit;
    Scanner in = new Scanner(System.in);
    boolean matchesDesired = true;

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

    //newReservation = new Reservation(number, hotel, bookingType, name, nights, rooms, deposit, stringDate,rooms);
    writer.write(toBeWritten);
  }

  public void makeCancellation() {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = dateutility.convertDateToString(date);
    String choice, name, nights, rooms, deposit;
    Scanner in = new Scanner(System.in);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    Date reservationDate = dateutility.convertStringToDate(reader.getDate(number));
    double hours = (date.getTime() - reservationDate.getTime()) / 1000 / 60 / 60;

    if(reader.checkType(number).equals("advanced")) {
      System.out.print("Advanced purchase reservations are non-refundable.");
    }

    if(hours < 48) {
      reader.cancelReservation (number, stringDate);
    }
    else {
      System.out.print("Reservations cannot be cancelled less than 48 hours before arrival.");
    }
  }

  public void applyDiscount() {

    boolean matchesDesired = true;
    String number = utility.checkNumber(matchesDesired);

    if(reader.checkType(number).equals("advanced"))
      reader.applyDiscount(number);

  }

  public void getDataAnalysis() {

    System.out.print("Data analysis selected.");

  }
}
