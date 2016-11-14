public class Room {

  private String type;
  private int numAdults;
  private int numChildren;
  private int[] rates;
  private boolean breakfastIncluded;

  Room(String type, int numAdults, int numChildren, boolean breakfastIncluded) {

    this.type              = type;
    this.numAdults         = numAdults;
    this.numChildren       = numChildren;
    this.breakfastIncluded = breakfastIncluded;
  }

  public String getRoomType() {

    return type;

  }

  public int returnNumAdults() {

    return numAdults;

  }

  public int returnNumChildren() {

    return numChildren;

  }

  public boolean getBreakfastIncluded() {

    return breakfastIncluded;

  }
}
