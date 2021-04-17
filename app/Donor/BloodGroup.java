package app.Donor;

import java.util.Arrays;

public enum BloodGroup {
   A_PLUS("A", true), B_PLUS("B", true), AB_PLUS("AB", true), O_PLUS("O", true), 
   A_MINUS("A", false), B_MINUS("B", false), AB_MINUS("AB", false), O_MINUS("O", false);

   private final String bloodType;
   private final boolean bloodFactor;

   private BloodGroup(String bloodType, boolean bloodFactor) {
      this.bloodType = bloodType;
      this.bloodFactor = bloodFactor;
   }

   public static BloodGroup parseGroup(String bloodGroup) throws Exception {
      for (BloodGroup group : Arrays.asList(BloodGroup.values()))
         if (bloodGroup.equals(group.toString()))
            return group;
      throw new Exception("Valid Blood Groups only include A, B, AB, O ( with + or - as postfix )");
   }

   public String toString() {
      return this.bloodType + (this.bloodFactor ? "+" : "-");
   }
}
