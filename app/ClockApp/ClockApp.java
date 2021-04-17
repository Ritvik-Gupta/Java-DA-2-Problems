package app.ClockApp;

import java.util.Scanner;

import services.Console.Console;
import java.time.LocalTime;

public final class ClockApp {
   private static final Scanner S = new Scanner(System.in);

   public static void main(String[] args) {
      Console.log("\nReminder Clock App");
      Console.log("\nEnter the Current Time Hour (Hr.) :\t");
      double hour = S.nextDouble();

      if (hour < 1 || hour > 24) {
         if (hour < 1)
            Console.log("\nHour cannot be smaller than 1. Using Current System Time Hr.");
         else
            Console.log("\nHour cannot be larger than 24. Using Current System Time Hr.");
         hour = ((double) LocalTime.now().toSecondOfDay()) / (60 * 60) + 1;
      }
      String timeOfDay;

      try {
         if (5 <= hour && hour < 12)
            throw new Reminder("Morning, Have Fresh Vegetable Juice and then Sugar Tablet with mild walking");
         else if (12 <= hour && hour < 17)
            throw new Reminder("Its Day Time, after lunch have tablet to avoid sleep");
         else if (17 <= hour && hour < 18.50)
            throw new Reminder("Hello, Good Evening have a dinner");
         else
            throw new Reminder("Night, Go for sleep");
      } catch (Reminder reminder) {
         timeOfDay = reminder.toString();
      }

      Console.log("\nTime of the Day :\t", timeOfDay);

   }
}
