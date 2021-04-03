package app.Employee;

public final class InvalidEmployeeCode extends Exception {
   static final long serialVersionUID = 0;

   public InvalidEmployeeCode(String message) {
      super(message);
   }

   public InvalidEmployeeCode(InvalidEmployeeCode err) {
      super(err.getMessage());
   }

   public String toString() {
      return "Invalid Employee ID :\t" + this.getMessage();
   }
}
