package src.Events;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public final class Events {
   private static final Scanner S = new Scanner(System.in);

   private static void printArray(ArrayList<Integer> list) {
      for (Integer elm : list)
         System.out.println("\tStudent ID ( " + elm + " )");
   }

   private static void printMap(Map<Integer, Integer> map) {
      map.forEach((key, value) -> {
         System.out.println("\tEvent ID ( " + key + " )\t->\t" + value);
      });
   }

   public static void main(String[] args) throws InterruptedException {
      System.out.print("\nEnter the toal number of students :\t");
      int totalStudents = S.nextInt();

      System.out.print("\nEnter total number of events :\t");
      int totalEvents = S.nextInt();

      EventsBank eventsBank = new EventsBank(totalEvents, totalStudents);
      int totalThreads = 6;
      Function<Integer, Integer> partition = pos -> (int) Math.ceil(pos * totalStudents / totalThreads);

      ArrayList<EventsWorker> workers = new ArrayList<>();
      for (int pos = 0; pos < totalThreads; ++pos)
         workers.add(new EventsWorker(eventsBank, partition.apply(pos), partition.apply(pos + 1)));

      workers.forEach(worker -> worker.start());

      for (EventsWorker worker : workers)
         worker.join();

      System.out.println("\nNumber of Students registered for Events");
      printMap(eventsBank.eventsRegister);
      System.out.println("\nStudent IDs that have not Registered");
      printArray(eventsBank.notRegisteredStudents);
   }
}
