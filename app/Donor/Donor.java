package app.Donor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import services.Console.Console;
import services.Constrained.Constrained;

public final class Donor {
   private static final Scanner S = new Scanner(System.in);
   private static final String donorFilePath = "./app/Donor/DonorList.txt";

   private static BloodDonorInfo createDonor() throws Exception {
      Console.log("\nEnter the following details to create a Blood Donor :\n");

      Console.log("\tName :\t");
      String name = S.nextLine();

      Console.log("\tAge :\t");
      int age = S.nextInt();
      S.nextLine();

      Console.log("\tAddress :\t");
      String address = S.nextLine();

      Console.log("\tContact Number :\t");
      Constrained contactNo = BloodDonorInfo.constrainedContactNo.with(S.nextLine());

      Console.log("\tBlood Group :\t");
      BloodGroup bloodGroup = BloodGroup.parseGroup(S.nextLine());

      Console.log("\tLast Donation Date :\t");
      Date lastDonationDate = BloodDonorInfo.formatDonationDate(S.nextLine());

      return new BloodDonorInfo(name, age, address, contactNo, bloodGroup, lastDonationDate);
   }

   private static void filterDonors() throws Exception {
      Console.log("\nEnter the following details to create a Filter out Donors :\n");

      Console.log("\tBlood Group :\t");
      BloodGroup comparisionBloodGroup = BloodGroup.parseGroup(S.nextLine());

      Console.log("\tLast Donation Date :\t");
      Date comparisionDate = BloodDonorInfo.formatDonationDate(S.nextLine());

      BufferedReader donorList = new BufferedReader(new FileReader(donorFilePath));
      String entry = donorList.readLine();
      for (int pos = 1; entry != null; ++pos, entry = donorList.readLine()) {
         BloodDonorInfo donorInfo = BloodDonorInfo.parseEntry(entry);
         if (
            donorInfo.bloodGroup == comparisionBloodGroup 
            && donorInfo.lastDonationDate.compareTo(comparisionDate) < 0
         )
            Console.log("\n( ", pos, " ) ->\t", donorInfo);
      }
      donorList.close();
   }

   public static void main(String[] args) throws IOException {
      Console.log("\nBlood Donor Camp :\n");
      Console.log("\n\t(1) <CREATE> :\tCreate a new Donor");
      Console.log("\n\t(5) <FILTER> :\tFilter out donors based on Blood Group and Last Donation Date");
      Console.log("\n\t(6) <END> :\tEnd the Simulation");

      while (true) {
         Console.log("\n\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("END")) {
               Console.log("\nTerminating ...\n\n");
               break;
            }

            else if (command.equals("CREATE")) {
               BufferedWriter donorList = new BufferedWriter(new FileWriter(donorFilePath, true));
               BloodDonorInfo donorInfo = createDonor();
               donorInfo.writeToFile(donorList);
               donorList.close();
            }

            else if (command.equals("FILTER"))
               filterDonors();

            else
               throw new Exception("Invalid Command Specified");
         } catch (PatternSyntaxException err) {
            Console.log("\nPattern Mismatch Error :\t", err.getDescription());
            Console.log("\nAllowed Format :\t", err.getPattern());
         } catch (Exception err) {
            Console.log("\nError :\t", err.getMessage());
         }
      }
   }
}
