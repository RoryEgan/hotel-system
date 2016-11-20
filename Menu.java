import java.util.Scanner;
/**
 * Class to handle Menu interactions with the end user.
 */
public class Menu {

  private UserAction actions = new UserAction();
  private Scanner in = new Scanner(System.in);

  /**
   * Main menu class to start the login process.
   */
  public void mainMenu() {

    System.out.print("\n****************************" +
    "\n*** Welcome to L4 Hotels ***" +
    "\n****************************\n");

    getUser();
  }

  /**
   * Calls the method to get the current user and user type.
   */
  public void getUser() {

    User currentUser = getCurrentUser();
    String type = currentUser.getUserType();

    userMenu(type);

  }

  /**
   * Initialises a user reader object and calls the check user id class
   * @return user object
   */
  public User getCurrentUser() {

    UserReader reader = new UserReader("UserDetails.csv");

    return checkUserId(reader);

  }

  /**
   * Checks the user given id aginst ids read in by the user reader.
   * @param  reader user reader to read in from the user info file
   * @return        a user object
   */

  public User checkUserId(UserReader reader) {

    String id;
    int index = 0;

    System.out.print("\nPlease enter your user id: ");
    id = in.nextLine();

    while(((index = reader.checkId(id)) == -1)) {
      System.out.print("Invalid id. Please try again: ");
      id = in.nextLine();
    }

    return checkUserPassword(index, reader);
  }

  /**
   * Checks the users password based on id
   * @param   position in the users file to check password
   * @param   reader to read in from user info file.
   * @return  a user object
   */

  public User checkUserPassword(int index, UserReader reader) {

    User currentUser = reader.getUserInfo().get(index);
    String password, userType;

    System.out.print("\nPlease enter your password: ");
    password = in.nextLine();

    while(!password.equals(currentUser.getPassword())) {
      System.out.print("Invalid password. Please try again: ");
      password = in.nextLine();
    }

    userType = checkUserType(index, reader);
    System.out.println("\nSuccess! You have been logged in as a " + userType + ".");

    return currentUser;

  }

  /**
   * Checks the user type based on id
   * @param   position in the user info file
   * @param   reader to read in from the user info file
   * @return  user type
   */

  public String checkUserType(int index, UserReader reader) {

    User currentUser = reader.getUserInfo().get(index);

    return currentUser.getUserType();

  }

  /**
   * Method to get what action to perform from the user and implement that action through
   * the user action class.
   * @param type user type
   */

  public void userMenu(String type) {

    GeneralUtility utility = new GeneralUtility();
    String input;

    System.out.print("\nWould you like to: \n1. exit to main menu \n2. make a reservation \n3. make a cancellation");
    if(type.equals("hotel desk employee") || type.equals("supervisor"))
    System.out.print("\n4. check a customer in \n5. check a customer out");
    if(type.equals("supervisor"))
    System.out.print("\n6. get data analysis \n7. purge old reservations");
    System.out.print("\nPlease enter your selection: ");
    input = in.nextLine();

    boolean complete = false;

    while(!complete) {
    if(input.equals("1")) {
      System.out.print("Returning to main menu...");
      mainMenu();
    }
    else if(input.equals("2")) {
      actions.makeReservation();
      userMenu(type);
    }
    else if(input.equals("3")) {
      actions.makeCancellation();
      userMenu(type);
    }
    else if(input.equals("4") && !type.equals("customer")) {
      actions.checkIn();
      userMenu(type);
    }
    else if(input.equals("5") && !type.equals("customer")) {
      actions.checkOut();
      userMenu(type);
    }
    else if(input.equals("6") && type.equals("supervisor")) {
      actions.getDataAnalysis();
      userMenu(type);
    }
    else if(input.equals("7") && type.equals("supervisor")) {
      actions.purgeSystem();
      userMenu(type);
    }
    else {
      System.out.print("Invalid input, please try again: ");
      input = in.nextLine();
    }
  }

  }

}
