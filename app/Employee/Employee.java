package app.Employee;

import java.util.Scanner;

import services.Console.Console;
import services.Constrained.Constrained;
import services.Constrained.IConstrainedFor;

public final class Employee {
   private static final Scanner S;
   private static final IConstrainedFor<InvalidEmployeeCode> constrainedEmployeeId;
   private static final IConstrainedFor<IllegalArgumentException> constrainedDateOfBirth;

   static {
      S = new Scanner(System.in);
      constrainedEmployeeId = Constrained.compile(
         "^([01789]\\d|2[01])-[FS]-\\d{3}$",
         new InvalidEmployeeCode("ABC") 
      );
      constrainedDateOfBirth = Constrained.compile(
         "^(3[0-1]|[1-2]\\d|0[1-9])(?<D>[-|.])(1[0-2]|0[1-9])\\k<D>(19\\d|20[01])\\d$",
         new IllegalArgumentException("DEF")
      );
   }

   private Constrained employeeId;
   private String name;
   private Constrained dateOfBirth;

   private Employee(Constrained employeeId, String name, Constrained dateOfBirth) {
      this.employeeId = employeeId;
      this.name = name;
      this.dateOfBirth = dateOfBirth;
   }

   public String toString() {
      return "Employee ID :\t" + employeeId ;
   }

   public static void main(String[] args) throws InvalidEmployeeCode {
      try {
         Console.log("\nEnter Employee ID: \t");
         Constrained employeeId = constrainedEmployeeId.with(S.nextLine());

         Console.log("\nEnter Name: \t");
         String name = S.nextLine();

         Console.log("\nEnter Date of Birth: \t");
         Constrained dateOfBirth = constrainedDateOfBirth.with(S.nextLine());

         Employee employee = new Employee(employeeId, name, dateOfBirth);
         Console.log(employee);
      } catch (InvalidEmployeeCode err) {
         throw new InvalidEmployeeCode(err);
      } catch (IllegalArgumentException err) {
         throw new IllegalArgumentException(err);
      }
   }
}
