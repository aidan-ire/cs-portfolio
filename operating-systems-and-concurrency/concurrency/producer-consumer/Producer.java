import java.util.Date;
public class Producer implements Runnable {
   private Buffer buffer;
   private int id;
   private int sleepTime;   // in milliseconds

   public Producer(Buffer buffer, int id, int sleepTime) {
      this.buffer = buffer;
      this.id = id;
      this.sleepTime = sleepTime;
   }

   public void run() {
      Date message;

      while (true) {
         message = new Date(); // produce an item
         try {
            Thread.sleep(sleepTime);
         } catch (InterruptedException e) {}
         buffer.insert(message);
         System.out.println("Producer " + id + " inserted "+ message);
      }
   }
}
