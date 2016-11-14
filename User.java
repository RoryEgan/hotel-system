public class User {

  private String id;
  private String userType;
  private String password;

  User(String id, String userType, String password) {

    this.id       = id;
    this.userType = userType;
    this.password = password;

  }

  public String getId() {

    return id;

  }

  public String getUserType() {

    return userType;

  }

  public String getPassword() {

    return password;

  }
}
