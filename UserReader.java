import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class to read in info from the user info file.
 */
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

/**
 * Method to read in an arraylist of users from the file
 * @param  fileName string of the file name to be read in from
 * @return          arraylist of users read in from the file
 */
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
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return info;

  }

/**
 * Method to check an id against those of the users read in from the file.
 * @param  id string of the id to be checked
 * @return    position of the number in the user arraylist
 */
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
