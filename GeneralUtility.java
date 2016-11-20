import java.util.Scanner;
import java.util.ArrayList;

/**
 * A general utility class to carry out a range of minor tasks.
 */
public class GeneralUtility {

  private String numeric = "\\d+";
  private ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();
  private ArrayList<String> roomList = new ArrayList<String>();

  /**
   * A method to check that a string is numeric.
   * @param  number string to be checked
   * @return        boolean dependent on result of checking
   */
  public boolean validateNumeric(String number) {

    if(number.matches(numeric))
    return true;
    else
    return false;

  }

  /**
   * A method to check if a reservation number exists
   * @param  matchesDesired boolean that states whether matches being present will
   * return an error or not
   * @return               string of the number to be checked
   */
  public String checkNumber(boolean matchesDesired) {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Scanner in = new Scanner(System.in);
    String number;
    int index;
    System.out.print("Enter the number of the reservation: ");
    number = in.nextLine();

    while(!validateNumeric(number)) {
      System.out.print("Input is invalid. Please try again: ");
      number = in.nextLine();
    }
    if(!matchesDesired) {
      while(((index = reader.checkNumber(number)) != -1)) {
        System.out.print("Number is invalid. Please try again: ");
        number = in.nextLine();
      }
    }
    else if(matchesDesired) {
      while(((index = reader.checkNumber(number)) == -1)) {
        System.out.print("Number is invalid. Please try again: ");
        number = in.nextLine();
      }
    }


    return number;

  }

  /**
   * A method to get the user to select a hotel.
   * @return string of the hotel that the user selected
   */
  public String getHotel() {

    HotelReader hreader = new HotelReader("l4Hotels.csv");
    ArrayList<Hotel> hotels = hreader.getHotelInfo();
    Scanner in = new Scanner(System.in);
    int input;

    System.out.print("Please select a hotel choice: ");
    for(int i = 0; i < hotels.size(); i++) {
      System.out.print("\n" + i + ". " + hotels.get(i).getType());
    }
    System.out.print("\nEnter your choice: ");
    input = in.nextInt();
    return hotels.get(input).getType();

  }

  /**
   * A method to get the user to select desired room types for a reservation.
   * @param   hotel to be used
   * @param   rooms in the hotel
   * @param   day of the week
   * @return  arraylist of rooms selected by the user
   */

  public ArrayList<String> getRoomType(String hotel, String rooms, int day) {

    HotelReader hreader = new HotelReader("l4Hotels.csv");
    ArrayList<Hotel> hotels = hreader.getHotelInfo();
    Scanner in = new Scanner(System.in);
    int input;
    double cost = 0;
    int numRooms = Integer.parseInt(rooms);

    for(int i = 0; i < hotels.size(); i++) {
      if((hotel.equals(hotels.get(i).getType()))) {
        roomTypes = hotels.get(i).getRoomTypes();
      }
    }
    for(int y = 0; y < numRooms; y++) {
      System.out.print("Please select a room type: ");
      for(int j = 0; j < roomTypes.size(); j++) {
        System.out.print("\n" + j + ". " + roomTypes.get(j).getRoomType());
      }
      System.out.print("\nPlease enter your selection: ");
      input = in.nextInt();
      roomList.add(roomTypes.get(input).getRoomType());

    }
    return roomList;
  }

  /**
   * A method to get the room costs based on the users selection of rooms.
   * @param   day of the week the reservation starts on
   * @param   number of nights the reservation will be
   * @return  cost of reserving all the desired rooms
   */

  public double getRoomCosts(int day, int numNights) {

    double cost = 0, totalCost = 0;
    ArrayList<Room> rooms = new ArrayList<Room>();
    String roomType;

    for(int i = 0; i < roomList.size(); i++) {
      for(int j = 0; j < roomTypes.size(); j++) {
        if(roomList.get(i).equals(roomTypes.get(j).getRoomType())) {
          rooms = roomTypes.get(j).getRooms();
          for(int z = 0; z < rooms.size(); z++) {
            cost = getIndividualRoomCost(rooms.get(z).getRates(), day, numNights);
            totalCost += cost;
          }
        }
      }
    }

    return totalCost;
  }

/**
 * Method to get the cost of a particular room type
 * @param   daily rates for type of room
 * @param   day of the week the reservation starts on
 * @param   number of nights the reservation will take
 * @return  cost of individual room type
 */
  private double getIndividualRoomCost(double[] rates, int day, int numNights) {

    double cost = 0;

    for(int i = 0; i < numNights; i++) {
      cost += rates[day];
    }

    return cost;

  }


  /**
   * Method to get the booking type from the user.
   * @return string of the type of booking required
   */
  public String getType() {

    Scanner in = new Scanner(System.in);
    boolean valid = false;
    String input, type = "";

    System.out.print("Is this a Simple or Advanced purchase booking? \n1. Simple \n2. Advanced\nPlease enter: ");
    input = in.nextLine();
    while(!valid) {
      if(input.equals("1")) {
        type = "simple";
        valid = true;
      }
      else if(input.equals("2")) {
        type = "advanced";
        valid = true;
      }
      else {
        System.out.println("Invalid input, try again: ");
        input = in.nextLine();
      }
    }
    return type;
  }

}
