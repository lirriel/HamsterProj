// Weather.java
// Maintains one day's weather information
package app.hse.myapplication.api;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Weather {
   public final String dayOfWeek;
   public final String minTemp;
   public final String maxTemp;
   public final String humidity;
   public final String weatherMain;
   public final String description;
//   public final String iconURL;

   // constructor
   public Weather(long timeStamp, double minTemp, double maxTemp,
                  double humidity, String description, String rain) {
      // NumberFormat to format double temperatures rounded to integers
      NumberFormat numberFormat = NumberFormat.getInstance();
      numberFormat.setMaximumFractionDigits(0);

      this.dayOfWeek = convertTimeStampToDay(timeStamp);
      minTemp = (minTemp - 273.15);
      maxTemp = (maxTemp-273.15);
      this.minTemp = numberFormat.format(maxTemp) + "\u00B0C";
      this.maxTemp = numberFormat.format(minTemp) + "\u00B0C";
      this.humidity =
         NumberFormat.getPercentInstance().format(humidity / 100.0);
      this.description = description;
      weatherMain = rain;
   }

   // convert timestamp to a day's name (e.g., Monday, Tuesday, ...)
   private static String convertTimeStampToDay(long timeStamp) {
      Calendar calendar = Calendar.getInstance(); // create Calendar
      calendar.setTimeInMillis(timeStamp * 1000); // set time
      TimeZone tz = TimeZone.getDefault(); // get device's time zone

      // adjust time for device's time zone
      calendar.add(Calendar.MILLISECOND,
         -1);

      // SimpleDateFormat that returns the day's name
      SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE");
      return dateFormatter.format(calendar.getTime());
   }
}
