package services.Constrained;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class Constrained {
   public final String value;

   private Constrained(String value) {
      this.value = value;
   }

   private static final Consumer<String> emptyFunc =  value -> {};

   public static <T extends Exception> IConstrainedFor<T> compile(
      Consumer<String> startupFunc, 
      Pattern regex, 
      T exception, 
      Consumer<String> cleanupFunc
   ) {
      return value -> {
         startupFunc.accept(value);
         if (!regex.matcher(value).matches())
            throw exception;
         cleanupFunc.accept(value);
         return new Constrained(value);
      };
   }

   public static <T extends Exception> IConstrainedFor<T> compile(
      Consumer<String> startupFunc, 
      String regex, 
      T exception, 
      Consumer<String> cleanupFunc
   ) {
      return compile(startupFunc, Pattern.compile(regex), exception, cleanupFunc);
   }

   public static <T extends Exception> IConstrainedFor<T> compile(String regex, T exception) {
      return compile(emptyFunc, regex, exception, emptyFunc);
   }

   public static <T extends Exception> IConstrainedFor<T> compile(
      Consumer<String> startupFunc, 
      String regex, 
      T exception
   ) {
      return compile(startupFunc, regex, exception, emptyFunc);
   }

   public static <T extends Exception> IConstrainedFor<T> compile(
      String regex, 
      T exception, 
      Consumer<String> cleanupFunc
   ) {
      return compile(emptyFunc, regex, exception, cleanupFunc);
   }

   public String toString() {
      return this.value;
   }
}
