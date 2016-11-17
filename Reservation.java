public class Reservation {

  private String number;
  private String hotel;
  private String type;
  private String name;
  private String numNights;
  private String numRooms;
  private String deposit;
  private String date;

  Reservation(String number, String hotel, String type, String name, String numNights, String numRooms, String deposit, String date) {

    this.number = number;
    this.hotel = hotel;
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
