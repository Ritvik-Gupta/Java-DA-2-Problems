package src.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public final class EventsBank {
   public final HashMap<Integer, Integer> eventsRegister;
   private final ArrayList<Integer> studentsChoiceOfEvents;
   public final ArrayList<Integer> notRegisteredStudents;

   public EventsBank(Integer totalEvents, int totalStudents) {
      this.eventsRegister = new HashMap<>();
      for (int pos = 0; pos < totalEvents; ++pos)
         this.eventsRegister.put(pos, 0);

      this.studentsChoiceOfEvents = new ArrayList<>();
      for (int pos = 0; pos < totalStudents; ++pos)
         this.studentsChoiceOfEvents.add(chooseEvent(totalEvents));

      this.notRegisteredStudents = new ArrayList<>();
   }

   private static Integer chooseEvent(int totalEvents) {
      int eventId = (int)Math.round(Math.random() * totalEvents);
      return eventId == totalEvents ? null : eventId;
   }

   public synchronized Integer getChosenEventId(int pos) {
      return this.studentsChoiceOfEvents.get(pos);
   }

   public synchronized void addEventChoiceToBank(int eventId) {
      Integer registeredStudents = this.eventsRegister.get(eventId);
      if (registeredStudents == null)
         throw new NoSuchElementException("Event does not exist");
      this.eventsRegister.put(eventId, registeredStudents + 1);
   }

   public synchronized void addNotRegisteredStudent(int studentId) {
      this.notRegisteredStudents.add(studentId);
   }
}
