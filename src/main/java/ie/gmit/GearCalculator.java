package ie.gmit;

/*Sergey Moiseenko 2020
This class stores variables for the calculation
and preforms calculation
*/

import java.util.ArrayList;
import java.util.List;

public class GearCalculator {
    private double sellPrice = 0;
    private double totalMaterialCost = 0;
    private List<Double> materialList = new ArrayList<>();
    private List<Integer> quantityList = new ArrayList<>();
    private int commission = 0;
    private double deposit = 0;
    private double totalMoneyReceived = 0;
    private double profitOrLoss = 0;

    public GearCalculator(){ }

    public void calculateTotalMaterialPrice(){
        if(!materialList.isEmpty() && !quantityList.isEmpty()){
            totalMaterialCost = 0;
            for(int i = 0; i < materialList.size(); i++){
                totalMaterialCost += materialList.get(i) * quantityList.get(i);
            }
            if(deposit != 0){
                totalMaterialCost += deposit;
            }
        } else {
            throw new IllegalArgumentException("List is not set");
        }
    }

    public void calculateTotalMoneyReceived(){
        if(sellPrice != 0){
            if(commission == 0 && deposit != 0){
                totalMoneyReceived = sellPrice + deposit;
            } else if(commission != 0 && deposit == 0) {
                double percentage = (double) commission/100;
                totalMoneyReceived =  (sellPrice * (1 - percentage));
            } else if(commission != 0 && deposit != 0) {
                double percentage = (double) commission/100;
                totalMoneyReceived = (sellPrice * (1 - percentage)) + deposit;
            } else {
                totalMoneyReceived = sellPrice;
            }
        } else {
            throw new IllegalArgumentException("Selling price cannot be 0");
        }
    }
    public void calculateProfitOrLoss(){
        if(totalMaterialCost != 0 && totalMoneyReceived != 0){
            profitOrLoss = totalMoneyReceived - totalMaterialCost;
        } else {
            throw new IllegalArgumentException("Value has not been set");
        }
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(double totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public List<Double> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Double> materialList) {
        this.materialList = materialList;
    }

    public List<Integer> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(List<Integer> quantityList) {
        this.quantityList = quantityList;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getTotalMoneyReceived() {
        return totalMoneyReceived;
    }

    public void setTotalMoneyReceived(double totalMoneyReceived) {
        this.totalMoneyReceived = totalMoneyReceived;
    }

    public double getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }
}
