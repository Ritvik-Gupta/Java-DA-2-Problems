package src.ThreadStack;

import java.util.ArrayList;
import java.util.Scanner;

public final class Main {
   private static final Scanner S = new Scanner(System.in);

   public static void main(String[] args) {
      System.out.print("\nEnter the size of stack :\t");
      int stackSize = S.nextInt();
      ThreadStack stack = new ThreadStack(stackSize);

      System.out.print("\nEnter the number of Pop Workers :\t");
      int totalPopWorkers = S.nextInt();

      ArrayList<PopWorker> popWorkers = new ArrayList<>();
      for (int pos = 0; pos < totalPopWorkers; ++pos)
         popWorkers.add(new PopWorker(pos + 1, stack));
      PushWorker pushWorker = new PushWorker(stack);

      pushWorker.start();
      popWorkers.forEach(worker -> worker.start());
   }
}
