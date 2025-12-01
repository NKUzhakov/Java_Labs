class Producer implements Runnable {
    private final RingBuffer<String> buffer;
    private final int number;

    public Producer(RingBuffer<String> buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    @Override
    public void run() {
        int msgCount = 0;
        try {
            while (true) {
                String message = "Thread #" + number + " generated message " + (++msgCount);
                buffer.put(message);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
