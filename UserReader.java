import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class UserReader {

  private String fileName;
  private ArrayList<User> userInfo;

  UserReader(String fileName) {

    this.fileName = fileName;
    userInfo = readIn(fileName);

  }

  public String getFileName() {

    return fileName;

  }

  public ArrayList<User> getUserInfo() {

    return userInfo;

  }

  private ArrayList<User> readIn(String fileName) {

    ArrayList<User> info = new ArrayList<User>();

    try {

      File userFile = new File(fileName);
      Scanner fileIn = new Scanner(userFile);

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        info.add(new User(lineSplit[0], lineSplit[1], lineSplit[2]));
      }
    }
    catch(IOException e) {
      System.out.println("Error: File could not be found.");
    }

    return info;

  }

  public int checkId(String id) {

    int position = -1;
    for(int i = 0; i < userInfo.size() && position == -1; i++) {
      if(userInfo.get(i).getId().equals(id)) {
        position = i;
      }
    }
    return position;
  }
}
