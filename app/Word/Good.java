package app.Word;

import java.util.HashSet;

//* Good Final Class to check if a Word is Good or Bad
public final class Good {
   public final String word;

   public Good(String word) {
      //* By default assume Word to be in Upper Case for Case Insensitivity
      this.word = word.toUpperCase();
   }

   //* Instance Method
   public WordType checkIfGood() {
      //? Uses HashSet (Set) to store previous encountered Characters
      HashSet<Character> tokens = new HashSet<>();

      for (int pos = 0; pos < this.word.length(); ++pos) {
         char chr = this.word.charAt(pos);
         //* If a Character has been encountered previously
         //? Then the Character will be inside the Token HashSet
         if (tokens.contains(chr))
            //* Character has been encountered twice so return BAD
            return WordType.BAD;

         //* If not then add the Character
         tokens.add(chr);
      }

   //* Characters never repeated so return GOOD
      return WordType.GOOD;
   }

   //* Static Method
   public static boolean checkIfGood(String word) {
      //* By default assume Word to be in Lower Case for Case Insensitivity
      word = word.toLowerCase();

      //* Run a Loop from the first Character to the Penultimate
      for (int posA = 0; posA < word.length() - 1; ++posA)
         //* Run a Nested Loop from the next Character to the Last
         for (int posB = posA + 1; posB < word.length(); ++posB)
            //* If two Characters are same
            //? Then the Character appeared twice at PosA and PosB where PosA > PosB in the Word
            if (word.charAt(posA) == word.charAt(posB))
            //* Character has been encountered twice so return FALSE 
            return false;
            
      //* Characters never repeated so return TRUE
      return true;
   }
}
