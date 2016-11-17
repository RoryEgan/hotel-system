import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
public class HotelReader {

  private String fileName;
  private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

  HotelReader(String fileName) {

    this.fileName = fileName;
    readIn(fileName);

  }

  public ArrayList<Hotel> getHotelInfo() {

    return hotels;

  }

  private void readIn(String fileName) {

    try {

      File hotelFile = new File(fileName);
      Scanner fileIn = new Scanner(hotelFile);
      int numHotels = 0;
      ArrayList<String> info = getInfo(fileIn);
      double [] roomCosts = new double[7];
      String [] lineSplit;

      for(int i = 0; i < info.size(); i++) {
        lineSplit = info.get(i).split(",");
        roomCosts = getRoomCosts(lineSplit);
        if(!lineSplit[0].equals("")) {
          hotels.add(new Hotel(lineSplit[0]));
          numHotels++;
        }
        hotels.get(numHotels - 1).addRoomType(new RoomType(lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3], lineSplit[4], roomCosts));
      }
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  private double[] getRoomCosts(String [] lineSplit) {

    double [] roomCosts = new double[7];
    for(int i = 0, j = 5; i < roomCosts.length; i++ , j++) {
      roomCosts[i] = Double.parseDouble(lineSplit[j]);
    }
    return roomCosts;

  }

  private ArrayList<String> getInfo(Scanner fileIn) {

    ArrayList<String> fileInfo = new ArrayList<String>();
    while(fileIn.hasNext()) {
      fileInfo.add(fileIn.nextLine());
    }
    return fileInfo;
  }

}
