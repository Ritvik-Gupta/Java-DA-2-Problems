package src.ThreadStack;

public final class PopWorker extends Thread {
   private final int id;
   private final ThreadStack stack;

   public PopWorker(int id, ThreadStack stack) {
      this.id = id;
      this.stack = stack;
   }

   public void run() {
      while (true) {
         try {
            Thread.sleep(500);
         } catch (Exception err) {
            System.out.println("\n");
            err.printStackTrace();
            System.out.println("\n");
         }

         synchronized (this.stack) {
            if (!stack.isEmpty())
               System.out.println("\nElement Removed from Pop Worker ( " + this.id + " ) :\t" + stack.pop());
            else
               System.out.println("\nStack Empty. Pop Worker ( " + this.id + " ) has to wait");
         }
      }
   }
}
