package src.ThreadStack;

public final class PushWorker extends Thread {
   private final ThreadStack stack;

   public PushWorker(ThreadStack stack) {
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

         double randomElm = Math.random();
         synchronized (this.stack) {
            if (stack.push(randomElm))
               System.out.println("\nElement Added :\t" + randomElm);
            else
               System.out.println("\nStack Full. Push Worker has to wait");
         }
      }
   }
}
