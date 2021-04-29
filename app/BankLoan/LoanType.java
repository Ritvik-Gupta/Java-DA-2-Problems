package app.BankLoan;

import java.util.Arrays;

public enum LoanType {
   HOUSING("housing"), VEHICLE("vehicle"), PERSONAL("personal");

   public final String type;

   private LoanType(String type) {
      this.type = type;
   }

   public static LoanType parseLoan(String loanName) throws Exception {
      for (LoanType loan : Arrays.asList(LoanType.values()))
         if (loanName.equals(loan.type))
            return loan;
      throw new Exception("Invalid Loan Type Chosen");
   }
}
