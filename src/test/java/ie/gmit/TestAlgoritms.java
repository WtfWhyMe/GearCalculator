package ie.gmit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAlgoritms {

    public GearCalculator gc;

    @BeforeEach
    void setupClass(){
        gc = new GearCalculator();
        List<Double> priceList = new ArrayList<>();
        priceList.add(100.10);
        priceList.add(50.50);
        List<Integer> quantityList = new ArrayList<>();
        quantityList.add(2);
        quantityList.add(3);
        gc.setSellPrice(1000);
        gc.setMaterialList(priceList);
        gc.setQuantityList(quantityList);
        gc.setCommission(10);
        gc.setDeposit(1.50);
    }

    @org.junit.jupiter.api.Test
    void testMaterialCostCalculation(){
        // test calculation when deposit is set
        gc.calculateTotalMaterialPrice();
        double  actualPrice = gc.getTotalMaterialCost();
        // syntax - expected, actual
        assertEquals(353.20, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testMaterialCostCalculationNoDeposit(){
        // test calculation when deposit is set
        gc.setDeposit(0);
        gc.calculateTotalMaterialPrice();
        double actualPrice = gc.getTotalMaterialCost();
        // syntax - expected, actual
        assertEquals(351.70, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testTotalMoneyReceivedCalculation(){
        // test when commission and deposit is set
        gc.calculateTotalMoneyReceived();
        double actualPrice = gc.getTotalMoneyReceived();
        assertEquals(901.5, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testTotalMoneyReceivedCalculationNoDeposit(){
        // test when commission set and deposit is not set
        gc.setDeposit(0);
        gc.calculateTotalMoneyReceived();
        double actualPrice = gc.getTotalMoneyReceived();
        assertEquals(900.0, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testTotalMoneyReceivedCalculationNoCommission(){
        // test when commission not set and deposit is set
        gc.setCommission(0);
        gc.calculateTotalMoneyReceived();
        double actualPrice = gc.getTotalMoneyReceived();
        assertEquals(1001.5, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testTotalMoneyReceivedCalculationNoCommissionAndDeposit(){
        // test when commission and deposit is not set
        gc.setCommission(0);
        gc.setDeposit(0);
        gc.calculateTotalMoneyReceived();
        double actualPrice = gc.getTotalMoneyReceived();
        assertEquals(1000.0, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testProfitCalculation(){
        //test when there are profit
        gc.calculateTotalMaterialPrice();
        gc.calculateTotalMoneyReceived();
        gc.calculateProfitOrLoss();
        double actualPrice = gc.getProfitOrLoss();
        assertEquals(548.3, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testLossCalculation(){
        //test when there are loss
        gc.setSellPrice(100);
        gc.calculateTotalMaterialPrice();
        gc.calculateTotalMoneyReceived();
        gc.calculateProfitOrLoss();
        double actualPrice = gc.getProfitOrLoss();
        assertEquals(-261.70, actualPrice);
    }

    @org.junit.jupiter.api.Test
    void testExceptionTrow1(){
        // test calculateTotalMaterialPrice to trow exception
        Executable ex = new Executable() {
            @Override
            public void execute() throws Throwable {
                List<Integer> l = new ArrayList<>();
                gc.setQuantityList(l);
                gc.calculateTotalMaterialPrice();
            }
        };
        assertThrows(IllegalArgumentException.class, ex);
    }

    @org.junit.jupiter.api.Test
    void testExceptionTrow2(){
        // test calculateTotalMoneyReceived to trow exception
        Executable ex = new Executable() {
            @Override
            public void execute() throws Throwable {
                gc.setSellPrice(0);
                gc.calculateTotalMoneyReceived();
            }
        };
        assertThrows(IllegalArgumentException.class, ex);
    }

    @org.junit.jupiter.api.Test
    void testExceptionTrow3(){
        // test calculateProfitOrLoss to trow exception
        Executable ex = new Executable() {
            @Override
            public void execute() throws Throwable {
                gc.calculateProfitOrLoss();
            }
        };
        assertThrows(IllegalArgumentException.class, ex);
    }
}
