package app.BankLoan;

import java.util.ArrayList;
import java.util.Scanner;

public final class Loan {
   private static final Scanner S = new Scanner(System.in);

   private final String clientName;
   private final String address;
   private final int age;
   private final int salary;
   private final int loanAmount;
   private final LoanType loanType;

   public Loan(
      String clientName, 
      String address, 
      int age, 
      int salary, 
      int loanAmount, 
      LoanType loanType
   ) {
      this.clientName = clientName;
      this.address = address;
      this.age = age;
      this.salary = salary;
      this.loanAmount = loanAmount;
      this.loanType = loanType;
   }

   //? Checks if a Client is Eligible and can be Approved for a Loan
   public boolean canBeApproved() {
      //* Following all the params specified
      //* Loan Amount should atleast be 30% of Salary
      return this.age > 20 && this.age < 50 && this.loanAmount >= (this.salary * 30 / 100);
   }

   //? Prompt the User for a Loan object creation
   public static Loan promptForLoan() throws Exception {
      System.out.print("Enter the Name of the Client :\t");
      String clientName = S.nextLine();

      System.out.print("Enter their Address :\t");
      String address = S.nextLine();

      System.out.print("Enter the Age :\t");
      int age = S.nextInt();
      if (age < 0 || age > 100) {
         //? Necessary to flush the terminal prompt
         S.nextLine();
         throw new Exception("Age should be in range [0-100]");
      }

      System.out.print("Enter the Salary :\t");
      int salary = S.nextInt();

      System.out.print("Enter the Amount of Loan :\t");
      int loanAmount = S.nextInt();

      //? Necessary to flush the terminal prompt
      S.nextLine();

      System.out.print("Enter the Type of Loan ( housing, vehicle or personal ) :\t");
      LoanType loanType = LoanType.parseLoan(S.nextLine());

      return new Loan(clientName, address, age, salary, loanAmount, loanType);
   }

   public String toString() {
      //* Dictionary kind of reference for each attribute in the Loan object
      return "" + 
      "\n\tClient Name :\t" + this.clientName + 
      "\n\tAddress :\t" + this.address + 
      "\n\tAge :\t" + this.age + 
      "\n\tSalary :\t" + this.salary + 
      "\n\tAmount of Loan:\t" + this.loanAmount + 
      "\n\tType of Loan:\t" + this.loanType;
   }

   public static void main(String args[]) throws Exception {
      //* Helper Function for printing a margin in the terminal 
      Runnable printMargin = () -> {
         System.out.println("--------------------------------------------------");
      };
      ArrayList<Loan> approvedLoans = new ArrayList<>();
      ArrayList<Loan> nonApprovedLoans = new ArrayList<>();

      System.out.print("\nEnter the number of Clients that want to register for a Loan :\t");
      int numClients = S.nextInt();
      
      S.nextLine();

      int pos = 0;
      while (pos < numClients) {
         System.out.println("\nFill the Client Detail Form for Client { " + (pos + 1) + " }");
         try {
            Loan loan = promptForLoan();
            if (loan.canBeApproved())
               approvedLoans.add(loan);
            else
               nonApprovedLoans.add(loan);
            ++pos;
         } catch (Exception err) {
            System.out.println("\n----- Error :\t" + err + " -----");
         }
      }

      System.out.println();
      printMargin.run();
      System.out.println("-----\tApprovoed Loans\t-----");
      printMargin.run();
      printMargin.run();
      for (Loan loan : approvedLoans) {
         System.out.println(loan);
         printMargin.run();
      }

      System.out.println();
      printMargin.run();
      System.out.println("-----\tNon Approvoed Loans\t-----");
      printMargin.run();
      printMargin.run();
      for (Loan loan : nonApprovedLoans) {
         System.out.println(loan);
         printMargin.run();
      }

      System.out.println();
   }
}
