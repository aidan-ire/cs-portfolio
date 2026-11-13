public class BoundedBufferSimulation {
   public static void main (String args[]) {
      Buffer buffer = new Buffer();

      // two producers and two consumers sharing the one buffer.
      // arguments are the buffer, an id and the sleep period in ms
      Thread producer1 = new Thread(new Producer(buffer, 1, 1000));
      Thread producer2 = new Thread(new Producer(buffer, 2, 1000));
      Thread consumer1 = new Thread(new Consumer(buffer, 1, 1000));
      Thread consumer2 = new Thread(new Consumer(buffer, 2, 1000));

      producer1.start();
      producer2.start();
      consumer1.start();
      consumer2.start();
   }
}
