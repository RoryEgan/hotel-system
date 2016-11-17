import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
public class UserAction {

  private GeneralUtility utility = new GeneralUtility();

  public void purgeSystem() {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String currentDate = utility.convertDateToString(date);
    String index;
    double days;
    Date reservationDate;
    ArrayList<Reservation> reservationInfo = reader.getReservationInfo();

    for(int i = 1; i < reservationInfo.size(); i++) {
      reservationDate = utility.convertStringToDate(reservationInfo.get(i).getDate());
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
    String stringDate = utility.convertDateToString(date);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    checkInWriter.write(number, stringDate);

  }

  public void checkOut() {

    CheckOutWriter checkOutWriter = new CheckOutWriter("CheckOuts.csv");
    CheckInWriter checkInWriter = new CheckInWriter("CheckIns.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = utility.convertDateToString(date);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    checkOutWriter.write(number, stringDate);
    checkInWriter.deleteLine(number);

  }

  public void makeReservation() {

    Reservation newReservation;
    ReservationWriter writer = new ReservationWriter("ReservationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = utility.convertDateToString(date);
    String choice, name, nights, rooms, deposit;
    Scanner in = new Scanner(System.in);
    boolean matchesDesired = true;
    int input;

    String hotel = utility.getHotel();

    String type = utility.getType();

    String number = utility.checkNumber(matchesDesired);

    System.out.print("Enter reservation name: ");
    name = in.nextLine();

    System.out.print("Enter number of nights: ");
    nights = in.nextLine();

    System.out.print("Enter number of rooms: ");
    rooms = in.nextLine();

    System.out.print("Enter the deposit: ");
    deposit = in.nextLine();

    newReservation = new Reservation(number, hotel, type, name, nights, rooms, deposit, stringDate);
    writer.write(number, hotel, type, name, nights, rooms, deposit, stringDate);
  }

  public void makeCancellation() {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Date date = Calendar.getInstance().getTime();
    String stringDate = utility.convertDateToString(date);
    String choice, name, nights, rooms, deposit;
    Scanner in = new Scanner(System.in);
    boolean matchesDesired = true;

    String number = utility.checkNumber(matchesDesired);

    Date reservationDate = utility.convertStringToDate(reader.getDate(number));
    double hours = (date.getTime() - reservationDate.getTime()) / 1000 / 60 / 60;

    if(reader.checkType(number).equals("advanced")) {
      System.out.print("Advanced purchase reservations are non-refundable.");
    }
    else {
      if(hours > 48) {
        reader.cancelReservation (number, stringDate);
      }
      else {
        System.out.print("Reservations cannot be cancelled less than 48 hours before arrival.");
      }
    }

  }
}
