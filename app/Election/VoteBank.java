package app.Election;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

import services.Random.Random;

public final class VoteBank {
   public final Map<String, Integer> votesCounter;
   private final ArrayList<String> castedVotes;

   public VoteBank(ArrayList<String> participants, int totalVotes) {
      this.votesCounter = participants.stream().collect(toMap(key -> key, value -> 0));
      this.castedVotes = new ArrayList<>();
      for (int pos = 0; pos < totalVotes; ++pos)
         this.castedVotes.add(Random.choose(participants));
   }

   public synchronized String getCastedVote(int pos) {
      return this.castedVotes.get(pos);
   }

   public synchronized void addVoteToBank(String participant) {
      Integer prevVotes = this.votesCounter.get(participant);
      if (prevVotes == null)
         throw new NoSuchElementException("Participant does not exist");
      this.votesCounter.put(participant, prevVotes + 1);
   }
}
