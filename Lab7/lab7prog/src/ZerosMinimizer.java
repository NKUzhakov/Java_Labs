// 7. Серед простих чисел, які не перевищують заданий n, знайти таке, в двійковій формі 
// якого максимальна кількість нулів. Просте число – це натуральне число, яке ділиться на 1 
// та на себе. 

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ZerosMinimizer {
    private static IntPredicate isPrime = (int num) -> {
        if (num < 2) return false;
        return IntStream.rangeClosed(2, (int)Math.sqrt(num))
                .noneMatch(div -> num % div == 0);
    };
    private static Function<String, Integer> countZeros = (String bin) -> {
        return (int)bin.chars()
                .filter(ch -> ch == '0')
                .count();
    };
    private static Comparator<Integer> zerosCountComparator = (a, b) -> Integer.compare(
        countZeros.apply(Integer.toBinaryString(a)), 
        countZeros.apply(Integer.toBinaryString(b))
    );

    public int findOptimalSimpleNumber(int n) throws IllegalArgumentException {
        if (n < 1) throw new IllegalArgumentException("The number must be natural");
        return IntStream.rangeClosed(2, n)
                .filter(isPrime)
                .boxed()
                .max(zerosCountComparator)
                .orElse(1);
    }    
}