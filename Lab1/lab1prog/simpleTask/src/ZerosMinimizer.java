// 7. Серед простих чисел, які не перевищують заданий n, знайти таке, в двійковій формі 
// якого максимальна кількість нулів. Просте число – це натуральне число, яке ділиться на 1 
// та на себе. 

public class ZerosMinimizer {
    public int findOptimalSimpleNumber(int n) throws IllegalArgumentException{
        if(n < 1){
            throw new IllegalArgumentException("The number must be natural");
        }
        int bestNum = 1, maxZerosNum = 0;

        for(int num = 1; num <= n; num++){
            boolean isSimple = true;
            for(int divisor = 2; divisor < num; divisor++){
                if(num % divisor == 0){
                    isSimple = false;
                    break;
                }
            }
            if(isSimple){
                String currentBinaryString = numToBinaryString(num);
                int currentZerosNum = countZeros(currentBinaryString);
                if(currentZerosNum > maxZerosNum){
                    maxZerosNum = currentZerosNum;
                    bestNum = num;
                }
            }
        }
        return bestNum;
    }

    private String numToBinaryString(int n){
        int currentResult = n;
        String result = "";
        while(currentResult > 1){
            int rest = currentResult % 2;
            currentResult = (currentResult - rest)/2;
            result = rest + result;
        }
        result = currentResult + result;
        return result;
    }
    private int countZeros(String binaryString){
        int zerosNum = 0;
        for(int i = 0; i < binaryString.length(); i++){
            if(binaryString.charAt(i) == '0'){
                zerosNum++;
            }                       
        }
        return zerosNum;
    }
}
