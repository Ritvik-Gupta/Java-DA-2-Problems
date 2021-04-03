package src.ThreadStack;

import java.util.Stack;

public final class ThreadStack {
   private final Stack<Double> stack;
   private final int stackSize;

   public ThreadStack(int stackSize) {
      this.stack = new Stack<>();
      this.stackSize = stackSize;
   }

   public boolean push(Double value) {
      if(this.isFulll())
         return false;
      this.stack.push(value);
      return true;
   }

   public Double pop() {
      return this.stack.pop();
   }

   private boolean isFulll() {
      return this.stack.size() == this.stackSize;
   }

   public boolean isEmpty() {
      return this.stack.isEmpty();
   }
}
