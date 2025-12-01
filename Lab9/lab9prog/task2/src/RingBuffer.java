import java.util.LinkedList;

class RingBuffer<T> {
    private final LinkedList<T> buffer;
    private final int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new LinkedList<>();
        for (int i = 0; i < capacity; i++) buffer.add(null);
    }

    public synchronized void put(T item) throws InterruptedException {
        while (size == capacity) wait();
        
        buffer.set(tail, item);
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (size == 0) wait();

        T item = buffer.get(head);
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return item;
    }
}
