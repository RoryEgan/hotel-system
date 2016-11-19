import java.util.ArrayList;
public class Hotel {

  private String type;
  private ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();

  Hotel(String type) {

    this.type  = type;

  }

  public String getType() {

    return type;

  }

  public ArrayList<RoomType> getRoomTypes() {

    return roomTypes;

  }

  public void addRoomType(RoomType newRoom) {

    roomTypes.add(newRoom);

  }
}
