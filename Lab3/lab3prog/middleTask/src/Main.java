import tourniquet.*;
import tourniquet.skiPass.WeekendClimbAmountUnlimitedSkiPass;
import tourniquet.skiPass.WorkdayClimbAmountLimitedSkiPass;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args){
        SkiPassRegister register = new SkiPassRegister();
        WorkdayClimbAmountLimitedSkiPass sp1 = new WorkdayClimbAmountLimitedSkiPass(10);
        WeekendClimbAmountUnlimitedSkiPass sp2 = new WeekendClimbAmountUnlimitedSkiPass(
            LocalDateTime.of(2025, 9, 29, 13, 0, 0), 
            LocalDateTime.of(2025, 9, 29, 17, 0, 0));
        WeekendClimbAmountUnlimitedSkiPass sp3 = new WeekendClimbAmountUnlimitedSkiPass(
            LocalDateTime.of(2013, 12, 26, 13, 0, 0), 
            LocalDateTime.of(2013, 12, 26, 17, 0, 0));
        
        System.out.println("Registering sp1: " + register.createSkiPass(sp1));
        System.out.println("Registering sp2: " + register.createSkiPass(sp2)); 
        try{
            System.out.println("Registering sp3: " + register.createSkiPass(sp3));
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Checking validity of sp1: " + register.validate(sp1));
        System.out.println("Checking validity of sp2: " + register.validate(sp2));
        System.out.println("Checking validity of sp3: " + register.validate(sp3));

        Tourniquet tourniquet = new Tourniquet(register);
        System.out.println("Applying sp1: " + tourniquet.applySkiPass(sp1));
        System.out.println("sp1 rest number of climbs: " + sp1.getClimbAmountLeft());
        for(int i = 0; i< 20; i++)tourniquet.applySkiPass(sp1);
        System.out.println("Applying empty sp1: " + tourniquet.applySkiPass(sp1));
        System.out.println("sp1 rest number of climbs: " + sp1.getClimbAmountLeft());

        System.out.println("Applying sp2: " + tourniquet.applySkiPass(sp2));
        System.out.println("Applying sp3: " + tourniquet.applySkiPass(sp3));

        register.blockSkiPass(sp2);
        System.out.println("Applying blocked sp2: " + tourniquet.applySkiPass(sp2));

        System.out.println(tourniquet.getGeneralSummary());
        System.out.println(tourniquet.getSummaryBySkiPassType());
    }
}
// compile: javac -d ./out/ ./src/*.java ./src/tourniquet/*.java ./src/tourniquet/skiPass/*.java ./src/tourniquet/summary/*.java
// start: java -cp ./out Main
