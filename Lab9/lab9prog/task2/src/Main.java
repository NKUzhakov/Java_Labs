public class Main {
    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 10, producerCount = 5, translatirCount = 2, targetMsgCount = 100;
        RingBuffer<String> buffer1 = new RingBuffer<>(bufferSize);
        RingBuffer<String> buffer2 = new RingBuffer<>(bufferSize);

        
        for (int i = 1; i <= producerCount; i++) {
            Thread t = new Thread(new Producer(buffer1, i));
            t.setDaemon(true);
            t.start();
        }        
        for (int i = 1; i <= translatirCount; i++) {
            Thread t = new Thread(new Translator(buffer1, buffer2, i));
            t.setDaemon(true);
            t.start();
        }

        for (int i = 0; i < targetMsgCount; i++) {
            String msg = buffer2.take();
            System.out.println(msg);
        }

    }
}


// compile: javac -d ./out/ ./src/*.java
// start: java -cp ./out Main