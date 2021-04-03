package app.Election;

public final class VotesWorker extends Thread {
   private final VoteBank voteBank;
   private final int startCounter;
   private final int endCounter;

   public VotesWorker(VoteBank voteBank, int startCounter, int endCounter) {
      this.voteBank = voteBank;
      this.startCounter = startCounter;
      this.endCounter = endCounter;
   }

   public void run() {
      for (int pos = this.startCounter; pos < this.endCounter; ++pos) {
         String participant = this.voteBank.getCastedVote(pos);
         this.voteBank.addVoteToBank(participant);
      }
   }
}
