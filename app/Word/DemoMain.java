package app.Word;

import java.util.Scanner;

//* Demo Main Class to Import and call the Instance and Static Methods of the Good Class 
public final class DemoMain {
   private static final Scanner S = new Scanner(System.in);

   public static void main(String[] args) {
      System.out.print("\nEnter a Word to check its Parity :\t");
      String word = S.next();

      Good parity = new Good(word);
      System.out.print("\nWord is " + parity.checkIfGood() + " according to the Instance Method");
      System.out.print(
         "\nWord is " + 
         (Good.checkIfGood(word) ? "GOOD" : "BAD") + 
         " according to the Static Method\n\n"
      );
   }
}
