import java.util.Scanner;
public class Menu {

  private UserAction actions = new UserAction();
  private Scanner in = new Scanner(System.in);

  public void mainMenu() {

    System.out.print("\n****************************" +
    "\n*** Welcome to L4 Hotels ***" +
    "\n****************************\n");
    System.out.print("\nPress enter to continue to login screen or press any other key to quit: ");

    if(in.nextLine().equals(""))
      getUser();
  }

  public void getUser() {

    User currentUser = getCurrentUser();
    String type = currentUser.getUserType();

    if(type.equals("customer"))
      customerMenu();
    else if(type.equals("hotel desk employee"))
      employeeMenu();
    else if(type.equals("supervisor"))
      supervisorMenu();

  }

  public User getCurrentUser() {

    UserReader reader = new UserReader("UserDetails.csv");

    return checkUserId(reader);

  }

  public User checkUserId(UserReader reader) {

    String id;
    int index;

    System.out.print("\nPlease enter your user id: ");
    id = in.nextLine();

    while(((index = reader.checkId(id)) == -1)) {
      System.out.print("Invalid id. Please try again: ");
      id = in.nextLine();
    }

    return checkUserPassword(index, reader);
  }

  public User checkUserPassword(int index, UserReader reader) {

    User currentUser = reader.getUserInfo().get(index);
    String password, userType;

    System.out.print("\nPlease enter your password: ");
    password = in.nextLine();

    while(!password.equals(currentUser.getPassword())) {
      System.out.print("\nInvalid password. Please try again: ");
      password = in.nextLine();
    }

    userType = checkUserType(index, reader);
    System.out.println("\nSuccess! You have been logged in as a " + userType + ".");

    return currentUser;

  }

  public String checkUserType(int index, UserReader reader) {

    User currentUser = reader.getUserInfo().get(index);

    return currentUser.getUserType();

  }

  public void supervisorMenu() {

    int input;

    System.out.print("\nWould you like to: \n1: make a reservation \n2: make a cancellation" +
    "\n3: check a customer in \n4: check a customer out \n5: apply a booking discount" +
    "\n6: get data analysis \n7: purge system of old reservations \n8: exit to main menu" +
    "\nPlease enter your selection: ");

    if(in.hasNextInt()) {
      input = in.nextInt();
      if(input == 1) {
        System.out.println("Reservation selected.");
        actions.makeReservation();
        supervisorMenu();
      }
      else if(input == 2) {
        System.out.println("Cancellation selected.");
        actions.makeCancellation();
        supervisorMenu();
      }
      else if(input == 3) {
        System.out.println("Checkin selected.");
        actions.checkIn();
        supervisorMenu();
      }
      else if(input == 4) {
        System.out.println("Checkout selected.");
        actions.checkOut();
        supervisorMenu();
      }
      else if(input == 5) {
        System.out.println("Discount selected.");
      }
      else if(input == 6) {
        System.out.println("Data analysis selected.");
      }
      else if(input == 7) {
        System.out.println("System purge selected.");
        actions.purgeSystem();
        supervisorMenu();
      }
      else if(input == 8) {
        System.out.println("Returning to main menu.");
        mainMenu();
      }
      else {
        System.out.println("Invalid input, please try again.");
      }
    }
  }

  public void employeeMenu() {

    int input;

    System.out.print("\nWould you like to: \n1: make a reservation \n2: make a cancellation" +
    "\n3: check a customer in \n4: check a customer out \n5: exit to main menu \nPlease enter your selection: ");

    if(in.hasNextInt()) {
      input = in.nextInt();
      if(input == 1) {
        System.out.println("Reservation selected.");
        actions.makeReservation();
        employeeMenu();
      }
      else if(input == 2) {
        System.out.println("Cancellation selected.");
        actions.makeCancellation();
        employeeMenu();
      }
      else if(input == 3) {
        System.out.println("Checkin selected.");
        actions.checkIn();
        employeeMenu();
      }
      else if(input == 4) {
        System.out.println("Checkout selected.");
        actions.checkOut();
        employeeMenu();
      }
      else if(input == 5) {
        System.out.println("Returning to main menu.");
        mainMenu();
      }
      else {
        System.out.println("Invalid input, please try again.");
      }
    }

  }

  public void customerMenu() {

    int input;

    System.out.print("Would you like to: \n1: make a reservation \n2: make a cancellation \n3: exit to main menu" +
    "\nPlease enter your selection: ");

    if(in.hasNextInt()) {
      input = in.nextInt();
      if(input == 1) {
        System.out.println("Reservation selected.");
        actions.makeReservation();
        customerMenu();
      }
      else if(input == 2) {
        System.out.println("Cancellation selected.");
        actions.makeCancellation();
        customerMenu();
      }
      else if(input == 3) {
        System.out.println("Returning to main menu.");
        mainMenu();
      }
      else {
        System.out.println("Invalid input, please try again.");
      }
    }
  }
}
