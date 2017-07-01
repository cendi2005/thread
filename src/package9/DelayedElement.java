package package9;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/28.
 */
public class DelayedElement implements Delayed {
    private long expired;
    private long delay;
    private String name;

    DelayedElement(String elementName, long delay) {
        this. name = elementName;
        this. delay= delay;
        expired = ( delay + System. currentTimeMillis());
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedElement cached=(DelayedElement) o;
        return cached.getExpired()> expired?1:-1;
    }

    @Override
    public long getDelay(TimeUnit unit) {

        return ( expired - System. currentTimeMillis());
    }

    @Override
    public String toString() {
        return "DelayedElement [delay=" + delay + ", name=" + name + "]";
    }

    public long getExpired() {
        return expired;
    }

}