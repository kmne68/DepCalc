/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depcalc;

/**
 *
 * @author Keith
 * 
 * Beginning Balance in year 1 equals cost.
 * Annual depreciation is rate * cost.
 * 
 */
public class Asset {
    
    private String assetName;
    private double assetCost;
    private double salvageValue;
    private int lifeOfItem;
    
    public Asset() {
        
    }
    
    public Asset(String name, double cost, double salvage, int life) {
        
        this.assetCost = cost;
        this.salvageValue = salvage;
        this.lifeOfItem = life;
    }
    
    
    public double getAnnualDep() {
        
        double annualDepreciation = 0;
        
        
        return annualDepreciation;        
    } // end getAnnualDep()
    
    
    public double getAnnualDep(int y) {
        
        double annualDepreciation = 0;
        
        return annualDepreciation;
    } // end getAnnualDep(y)
    
    
    public double getBegBal(int y, char m) {
         
        double beginningBalance = 0;
        
        return beginningBalance;        
    } // end getBegBal()
    
    
    public double getEndBal(int y, char m) {
        
        double endingBalance = 0;
        
        return endingBalance;
    }
}
