package datastructure;

/**
 * @date : 2019/05/27 11:17
 * @author: liangenmao
 */
public class MyCircularQueue {
        Integer first = null;
    Integer last = null;
    int[] values = null;
    int length = 0;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        values = new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(length == values.length){
            return false;
        }
        if(last == null){
            first = 0;
            last = 0;
        }else if (last == values.length - 1){
            last = 0;
        }else{
            last ++;
        }
        values[last] = value;
        length ++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(length == 0){
            return false;
        }
        if (length == 1){
            first = last = null;
        }else{
            first = first == values.length - 1 ? 0 : first + 1;
        }
        length --;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return first == null ? -1 : values[first];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        return last == null ? -1 : values[last];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return length == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return length == values.length;
    }
}
