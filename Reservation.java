import java.util.ArrayList;
public class Reservation {

  private String number;
  private String hotel;
  private String type;
  private String name;
  private String date;
  private String numNights;
  private String numRooms;
  private String deposit;
  private String cost;
  private ArrayList<String> roomTypes;

  Reservation(String number, String hotel, String type, String name, String date, String numNights, String numRooms, String deposit, String cost, ArrayList<String> roomTypes) {

    this.number = number;
    this.hotel = hotel;
    this.type = type;
    this.name = name;
    this.date = date;
    this.numNights = numNights;
    this.numRooms = numRooms;
    this.deposit = deposit;
    this.cost = cost;
    this.roomTypes = roomTypes;

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

  public String getCost() {

    return cost;
    
  }

}
