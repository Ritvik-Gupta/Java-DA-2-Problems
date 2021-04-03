package src.Events;

public final class EventsWorker extends Thread {
   private final EventsBank eventsBank;
   private final int startCounter;
   private final int endCounter;

   public EventsWorker(EventsBank eventsBank, int startCounter, int endCounter) {
      this.eventsBank = eventsBank;
      this.startCounter = startCounter;
      this.endCounter = endCounter;
   }

   public void run() {
      for (int studentId = this.startCounter; studentId < this.endCounter; ++studentId) {
         Integer eventId = this.eventsBank.getChosenEventId(studentId);
         if (eventId == null)
            this.eventsBank.addNotRegisteredStudent(studentId);
         else
            this.eventsBank.addEventChoiceToBank(eventId);
      }
   }
}
