package app.Donor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import services.Constrained.Constrained;
import services.Constrained.IConstrainedFor;

public final class BloodDonorInfo {
   public static final IConstrainedFor<PatternSyntaxException> constrainedContactNo;
   public static final IConstrainedFor<PatternSyntaxException> constrainedDonationDate;
   public static final SimpleDateFormat dateFormat;

   static {
      constrainedContactNo = Constrained.compile(
         "^\\d{3}(?<D>[-.])\\d{3}\\k<D>\\d{4}$",
         new PatternSyntaxException(
            "Invalid Contact Number format",
            "XXX-XXX-XXXX or XXX.XXX.XXXX ( where X is a digit [0-9] )",
            -1
         )
      );

      constrainedDonationDate = Constrained.compile(
         "^(3[0-1]|[1-2]\\d|0[1-9])-(1[0-2]|0[1-9])-(19\\d\\d|20([01]\\d|202[01]))$", 
         new PatternSyntaxException(
            "Invalid Donation Date format",
            "MM-DD-YYYY ( supported years are [1900-2021] )", 
            -1
         )
      );

      dateFormat = new SimpleDateFormat("dd-MM-yyyy");
   }

   private final String name;
   private final int age;
   private final String address;
   private final Constrained contactNo;
   public final BloodGroup bloodGroup;
   public final Date lastDonationDate;

   public BloodDonorInfo(
      String name, 
      int age, 
      String address, 
      Constrained contactNo, 
      BloodGroup bloodGroup,
      Date lastDonationDate
   ) {
      this.name = name;
      this.age = age;
      this.address = address;
      this.contactNo = contactNo;
      this.bloodGroup = bloodGroup;
      this.lastDonationDate = lastDonationDate;
   }

   public static Date formatDonationDate(String dateString) throws ParseException {
      return dateFormat.parse(constrainedDonationDate.with(dateString).value);
   }

   private String createEntry(String entryDelimeeter) {
      return this.name + entryDelimeeter
         + this.age + entryDelimeeter
         + this.address + entryDelimeeter
         + this.contactNo + entryDelimeeter
         + this.bloodGroup + entryDelimeeter
         + dateFormat.format(this.lastDonationDate);
   }

   public String toString() {
      return this.createEntry(" | ");
   }

   public void writeToFile(BufferedWriter donorList) throws IOException {
      donorList.write(this.createEntry(",") + "\n");
   }

   public static BloodDonorInfo parseEntry(String entry) throws Exception {
      String[] tokens = entry.split(",");
      String name = tokens[0];
      int age = Integer.parseInt(tokens[1]);
      String address = tokens[2];
      Constrained contactNo = constrainedContactNo.with(tokens[3]);
      BloodGroup bloodGroup = BloodGroup.parseGroup(tokens[4]);
      Date lastDonationDate = formatDonationDate(tokens[5]);

      return new BloodDonorInfo(name, age, address, contactNo, bloodGroup, lastDonationDate);
   }
}
