import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
public class Reservation {

  private String number;
  private String type;
  private String name;
  private String numNights;
  private String numRooms;
  private String deposit;
  private String date;
  private Room room;
  private Hotel hotel;

  Reservation(String number, String type, String name, String numNights, String numRooms, String deposit, String date) {

    this.number = number;
    this.type = type;
    this.name = name;
    this.numNights = numNights;
    this.numRooms = numRooms;
    this.deposit = deposit;
    this.date = date;

  }

  public String getNumber() {

    return number;

  }

  public String getType() {

    return type;

  }

  public String getDate() {

    return date;

  }

}
