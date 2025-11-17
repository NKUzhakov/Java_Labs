package model;
import java.util.ArrayList;
import java.util.concurrent.*;


public class ParallelMonteCarloPi {
    private final int totalPointsCount = 1_000_000_000;
    private final int threadCount;
    
    public ParallelMonteCarloPi(int threadCount) throws IllegalArgumentException{
        if(threadCount < 1) throw new IllegalArgumentException("The count of threads must be natural");
        this.threadCount = threadCount;
    }

    public CalculationResult calculate() throws RuntimeException{
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        int taskSize = totalPointsCount / threadCount;
        // If the totalPointsCount cannot be devided without the rest
        int lastTaskSize = totalPointsCount - taskSize * (threadCount-1); 

        final Callable<Integer> pointsInSectorCounter = createPointsInSectorCounter(taskSize);
        final Callable<Integer> lastPointsInSectorCounter = createPointsInSectorCounter(lastTaskSize);
        ArrayList<Future<Integer>> futures = new ArrayList<>(threadCount);
        for(int threadNum = 0; threadNum < threadCount-1; threadNum++)
            futures.add(executor.submit(pointsInSectorCounter));
        futures.add(executor.submit(lastPointsInSectorCounter));

        int totalPointsInCircle = 0;
        for(Future<Integer> future : futures){
            try{
                totalPointsInCircle += future.get();
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
                throw new RuntimeException(ie);
            }catch(ExecutionException ee){}
        }
        executor.shutdown();
        return new CalculationResult(
            totalPointsInCircle / (double)totalPointsCount*4,
            threadCount,
            totalPointsCount,
            (System.nanoTime() - startTime)/ 1_000_000D
        );        
    }

    private Callable<Integer> createPointsInSectorCounter(int generatedPointsCount){
        return () -> {
            double x = 0, y = 0;
            int pointsInSector = 0;
            for(int pointNum = 0; pointNum < generatedPointsCount; pointNum++){
                x = ThreadLocalRandom.current().nextDouble();
                y = ThreadLocalRandom.current().nextDouble();
                if(pointIsInSector(x, y)) pointsInSector++;
            }
            return pointsInSector;
        };
    }
    private boolean pointIsInSector(double x, double y){
        return Math.sqrt(x*x + y*y) <= 1;
    }

    public static record CalculationResult(
        double pi,
        int threads,
        int iterations,
        double timeInMilliSeconds
    ){}
}
