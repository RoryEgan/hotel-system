import java.util.ArrayList;

/**
 * Class to define a room type for a hotel.
 */
public class RoomType {

  private String type;
  private int numRooms;
  private ArrayList<Room> rooms = new ArrayList<Room>();

  RoomType(String type, int numRooms, String adultMax, String childMax, double[] roomCosts) {

    this.type     = type;
    this.numRooms = numRooms;
    rooms.add(new Room(roomCosts));
  }

  public String getRoomType() {

    return type;

  }

  public int returnNumRooms() {

    return numRooms;

  }

  public ArrayList<Room> getRooms() {

    return rooms;

  }
}
