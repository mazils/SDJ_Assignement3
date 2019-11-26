package Logger;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Logger
{
    private static Logger logger;
    private static Lock lock = new ReentrantLock();
    private Queue<String> queue;
    private Logger()
    {
        if (queue == null)
        {
            queue = new LinkedList<>();
        }
    }

    public static Logger getInstance()
    {
        if(logger == null)
        {
            synchronized (lock)
            {
                if (logger == null)
                {
                   logger = new Logger();
                }
            }
        }
        return logger;
    }

    public void log(String log)
    {
//        queue.add(log);
        System.out.println(log);
    }
}
