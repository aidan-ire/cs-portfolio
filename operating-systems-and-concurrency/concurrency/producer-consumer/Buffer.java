public class Buffer {
   private static final int BUFFER_SIZE = 5;
   private Object[] buffer;
   private int in, out;
   private Semaphore mutex;
   private Semaphore empty;
   private Semaphore full;

   public Buffer() {
      in = 0;
      out = 0;
      buffer = new Object[BUFFER_SIZE];

      mutex = new Semaphore(1);
      empty = new Semaphore(BUFFER_SIZE);
      full = new Semaphore(0);
   }

   /*
   empty counts the free slots and full counts the items, so a producer blocks
   on empty when the buffer is full and a consumer blocks on full when there is
   nothing to take. mutex is only held for the two lines that touch the array.
   the counting semaphore has to be acquired before mutex, otherwise a producer
   could go to sleep on a full buffer while holding mutex and no consumer could
   ever get in to make space
   */
   public void insert(Object item) {
      empty.acquire();                // wait for a free slot
      mutex.acquire();

      buffer[in] = item;
      in = (in + 1) % BUFFER_SIZE;    // wraps around, the array is used as a circle

      mutex.release();
      full.release();                 // one more item for the consumers
   }

   public Object remove() {
      full.acquire();                 // wait for an item
      mutex.acquire();

      Object item = buffer[out];
      out = (out + 1) % BUFFER_SIZE;

      mutex.release();
      empty.release();                // one more free slot for the producers
      return item;
   }
}
