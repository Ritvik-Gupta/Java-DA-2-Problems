package app.Election;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import services.Console.Console;

public final class Election {
   private static final Scanner S = new Scanner(System.in);

   public static void main(String[] args) throws InterruptedException {
      Console.log("\nEnter the toal number of votes :\t");
      int totalVotes = S.nextInt();

      Console.log("\nEnter total number of participants :\t");
      int totalParticipants = S.nextInt();

      ArrayList<String> participants = new ArrayList<>();
      Console.log("\nEnter the names of participants :\n");
      for (int pos = 0; pos < totalParticipants; ++pos) {
         Console.log("( ", pos + 1, " ) :\t");
         participants.add(S.next());
      }

      VoteBank voteBank = new VoteBank(participants, totalVotes);
      int totalThreads = 4;
      Function<Integer, Integer> partition = pos -> (int) Math.ceil(pos * totalVotes / totalThreads);

      ArrayList<VotesWorker> workers = new ArrayList<>();
      for (int pos = 0; pos < totalThreads; ++pos)
         workers.add(new VotesWorker(voteBank, partition.apply(pos), partition.apply(pos + 1)));

      workers.forEach(worker -> worker.start());

      for (VotesWorker worker : workers)
         worker.join();

      Console.println(voteBank.votesCounter);
   }
}
