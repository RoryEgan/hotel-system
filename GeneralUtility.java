import java.util.Scanner;
import java.util.ArrayList;
public class GeneralUtility {

  private String numeric = "\\d+";
  private ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();
  private ArrayList<String> roomList = new ArrayList<String>();

  public boolean validateNumeric(String number) {

    if(number.matches(numeric))
    return true;
    else
    return false;

  }

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

  private double getIndividualRoomCost(double[] rates, int day, int numNights) {

    double cost = 0;

    for(int i = 0; i < numNights; i++) {
      cost += rates[day];
    }

    return cost;

  }


  public String getType() {

    Scanner in = new Scanner(System.in);
    int input;
    String type = "";

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
    return type;
  }

}
