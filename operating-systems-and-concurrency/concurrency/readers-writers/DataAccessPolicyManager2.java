/**
 * Second readers/writers policy: writers get priority. Once a writer is
 * waiting, no new reader is let in until every waiting writer has finished.
 *
 * It extends DataAccessPolicyManager and overrides all four lock methods, so
 * Reader and Writer can be handed either policy without being changed.
 */
public class DataAccessPolicyManager2 extends DataAccessPolicyManager{
    private int readerCount;
    private int writerCount;        // writers that are writing or waiting to write
    private Semaphore readerMutex;  // protects readerCount
    private Semaphore writerMutex;  // protects writerCount
    private Semaphore readTry;      // readers have to get through this before they can start
    private Semaphore wrt;          // held by the writer, or by the readers as a group

    public DataAccessPolicyManager2(){
        readerCount = 0;
        writerCount = 0;
        readerMutex = new Semaphore(1);
        writerMutex = new Semaphore(1);
        readTry = new Semaphore(1);
        wrt = new Semaphore(1);
    }

    /*
    the reader side is the first policy with one extra gate in front of it. a
    reader takes readTry, does the usual count-and-maybe-lock, and lets readTry
    go again straight away, so readers still share the data with each other. the
    first writer to arrive takes readTry and keeps it until the last writer has
    left, which is what stops new readers getting in ahead of waiting writers.
    readers that were already reading are allowed to finish
    */
    public void acquireReadLock(){
        readTry.acquire();          // blocks here while any writer is waiting or writing
        readerMutex.acquire();
        ++readerCount;
        if(readerCount == 1) wrt.acquire();     // first reader locks writers out
        readerMutex.release();
        readTry.release();
    }

    public void releaseReadLock(){
        readerMutex.acquire();
        --readerCount;
        if(readerCount == 0) wrt.release();     // last reader lets a writer in
        readerMutex.release();
    }

    public void acquireWriteLock(){
        writerMutex.acquire();
        ++writerCount;
        if(writerCount == 1) readTry.acquire(); // first writer shuts the gate on new readers
        writerMutex.release();
        wrt.acquire();                          // still one writer at a time
    }

    public void releaseWriteLock(){
        wrt.release();
        writerMutex.acquire();
        --writerCount;
        if(writerCount == 0) readTry.release(); // no writers left, readers can come back
        writerMutex.release();
    }
}
