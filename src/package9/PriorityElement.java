package package9;

/**
 * Created by admin on 2017/6/28.
 */
public class PriorityElement implements Comparable<PriorityElement> {
    private int priority;//定义优先级
    PriorityElement(int priority) {
        //初始化优先级
        this.priority = priority;
    }
    @Override
    public int compareTo(PriorityElement o) {
        //按照优先级大小进行排序
        return priority >= o.getPriority() ? 1 : -1;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Override
    public String toString() {
        return "PriorityElement [priority=" + priority + "]";
    }
}
