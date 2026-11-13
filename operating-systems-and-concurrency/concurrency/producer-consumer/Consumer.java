import java.util.Date;
public class Consumer implements Runnable {
   private Buffer buffer;
   private int id;
   private int sleepTime;   // in milliseconds

   public Consumer(Buffer buffer, int id, int sleepTime) {
      this.buffer = buffer;
      this.id = id;
      this.sleepTime = sleepTime;
   }

   public void run() {
      Date message;

      while (true) {
         try {
            Thread.sleep(sleepTime);
         } catch (InterruptedException e) {}
         message = (Date) buffer.remove();
         // consume the item
         System.out.println("Consumer " + id + " removed "+ message);
      }
   }
}
