import static org.junit.jupiter.api.Assertions.*;

public class ZerosMinimizerTest {
    @org.junit.jupiter.api.Test
    public void normalValuesTest(){
        ZerosMinimizer zm = new ZerosMinimizer();
        int test1Res = zm.findOptimalSimpleNumber(11);
        assertEquals(test1Res, 2);
        int test2Res = zm.findOptimalSimpleNumber(19);
        assertEquals(test2Res, 17);
        int test3Res = zm.findOptimalSimpleNumber(1);
        assertEquals(test3Res, 1);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void wrongValuesTest(){
        ZerosMinimizer zm = new ZerosMinimizer();
        int numTest1 = zm.findOptimalSimpleNumber(-100);
    }
}



// Useful commands
// compile: javac -cp "lib/junit-platform-console-standalone-1.10.0.jar;src" -d out src/*.java  
// test: java -jar lib/junit-platform-console-standalone-1.10.0.jar -cp out --scan-classpath
