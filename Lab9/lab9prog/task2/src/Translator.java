class Translator implements Runnable {
    private final RingBuffer<String> source;
    private final RingBuffer<String> target;
    private final int number;

    public Translator(RingBuffer<String> source, RingBuffer<String> target, int number) {
        this.source = source;
        this.target = target;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = source.take();
                String translated = "Thread No" + number + " translated message: " + msg;
                target.put(translated);
                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}