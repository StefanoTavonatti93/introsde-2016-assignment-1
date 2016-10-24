import java.util.Calendar;

public class RandomDate {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
        System.out.println(randomDOB());
    }
  }

  public static String randomDOB() {

    int yyyy = random(1940, 2000);
    int mm = random(1, 12);
    int dd = 0; // will set it later depending on year and month

    switch(mm) {
      case 2:
        if (isLeapYear(yyyy)) {
          dd = random(1, 29);
        } else {
          dd = random(1, 28);
        }
        break;

      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        dd = random(1, 31);
        break;

      default:
        dd = random(1, 30);
      break;
    }

    String year = Integer.toString(yyyy);
    String month = Integer.toString(mm);
    String day = Integer.toString(dd);

    if (mm < 10) {
        month = "0" + mm;
    }

    if (dd < 10) {
        day = "0" + dd;
    }

    return year+"-"+month+"-"+day;
  }

  public static int random(int lowerBound, int upperBound) {
    return (lowerBound + (int) Math.round(Math.random()
            * (upperBound - lowerBound)));
  }

  public static boolean isLeapYear(int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

    if (noOfDays > 365) {
        return true;
    }

    return false;
  }
}
