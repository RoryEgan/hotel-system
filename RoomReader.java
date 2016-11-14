import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class RoomReader {

  private String fileName;
  private ArrayList<Room> roomInfo;

  RoomReader(String fileName) {

    this.fileName = fileName;
    roomInfo = readIn(fileName);

  }

  public String getFileName() {

    return fileName;

  }

  public ArrayList<Room> getRoomInfo() {

    return roomInfo;

  }

  private ArrayList<Room> readIn(String fileName) {

    ArrayList<Room> info = new ArrayList<Room>();

    try {

      File userFile = new File(fileName);
      Scanner fileIn = new Scanner(userFile);

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        info.add(new Room(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3]));
      }
    }
    catch(IOException e) {
      System.out.println("Error: File could not be found.");
    }

    return info;

  }
}
