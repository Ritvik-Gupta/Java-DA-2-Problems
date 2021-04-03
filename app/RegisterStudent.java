package app;

import java.util.NoSuchElementException;
import java.util.Scanner;

import services.Console.Console;
import services.Constrained.Constrained;
import services.Constrained.IConstrainedFor;

public final class RegisterStudent {
   private static final Scanner S;
   private static final IConstrainedFor<NoSuchElementException> constrainedRegNo;
   private static final IConstrainedFor<NumberFormatException> constrainedMobileNo;


   static {
      S = new Scanner(System.in);
      constrainedRegNo = Constrained.compile(
         regNo -> { 
            if (regNo.length() != 9)
               throw new IllegalArgumentException("Registration Number should have 9 characters");
         },
         "^(1[789]|2[01])(BCE|BEE|BIT)\\d{4}$",
         new NoSuchElementException("Registration Number should be [17-21] + branch alias + 4 digit ID")
      );
      constrainedMobileNo = Constrained.compile(
         mobileNo -> {
            if (mobileNo.length() != 10)
               throw new IllegalArgumentException("Mobile Number should have 10 characters");
         },
         "^\\d{10}$",
         new NumberFormatException("Mobile Number should have 10 digits")
      );
   }

   public static void main(String[] args) {
      try {
         Console.log("\nEnter a Valid Registration Number :\t");
         String regNo = S.nextLine();
         constrainedRegNo.with(regNo);

         Console.log("\nEnter a Valid Mobile Number :\t");
         String mobileNo = S.nextLine();
         constrainedMobileNo.with(mobileNo);
      } catch (NoSuchElementException err) {
         throw new NoSuchElementException(err.getMessage());
      } catch (NumberFormatException err) {
         throw new NumberFormatException(err.getMessage());
      }

      Console.log("\nValid");
   }
}
