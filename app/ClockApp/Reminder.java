package app.ClockApp;

public final class Reminder extends Exception {
   static final long serialVersionUID = 0;
   private final String reminderMessage;

   public Reminder(String reminderMessage) {
      this.reminderMessage = reminderMessage;
   }

   public String toString() {
      return this.reminderMessage;
   }
}
