package ie.gmit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestResuiltGUI {

    ResultFrame r;

    private static final String
            GEAR_SELL_PRICE = "Gear Price",
            TOTAL_MATERIAL_COST = "Total Material Cost (-)",
            GEAR_SELL_COMMISSION = "Gear Sell Commission (-)",
            GEAR_SELL_DEPOSIT = "Gear Sell Deposit (-)",
            PROFIT_LOSS = "Profit / Loss";


    @BeforeEach
    void setupGUI() {
        r = new ResultFrame(200,180,15,0,5);
    }

    @Test
    void testSettingDoubles() {
        double salePrice = r.getGearSalePrice();
        assertEquals(200, salePrice);

        double materialCost = r.getTotalMaterialSaleCost();
        assertEquals(180, materialCost);

        double commission = r.getSaleCommission();
        assertEquals(15, commission);

        double deposit = r.getSaleDeposit();
        assertEquals(0, deposit);

        double profit = r.getTotalProfit();
        assertEquals(5, profit);
    }

    @Test
    void testGuiTextDoubles() {
        String salePrice = r.getField(GEAR_SELL_PRICE);
        assertEquals("200.0", salePrice);

        String materialCost = r.getField(TOTAL_MATERIAL_COST);
        assertEquals("180.0", materialCost);

        String commission = r.getField(GEAR_SELL_COMMISSION);
        assertEquals("15.0", commission);

        String deposit = r.getField(GEAR_SELL_DEPOSIT);
        assertEquals("0.0", deposit);

        String profit = r.getField(PROFIT_LOSS);
        assertEquals("5.0 Shekels", profit);
    }
}
