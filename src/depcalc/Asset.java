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
 * Beginning Balance in year 1 equals cost. Annual depreciation is rate * cost.
 *
 */
public class Asset {

    // Globals
    private String assetName;
    private double assetCost;
    private double salvageValue;
    private int lifeOfAsset;

    // Data arrays for depreciation table
    private double[][] beginningBalance;
    private double[][] annualDepreciation;
    private double[][] endingBalance;

    private boolean built;
    private String errorMessage;  // what is this?

    public Asset() {

    }

    public Asset(String name, double cost, double salvage, int life) {

        this.assetName = name;
        this.assetCost = cost;
        this.salvageValue = salvage;
        this.lifeOfAsset = life;
    }

    // Calculate annual depreciation with the straight line method
    public double getAnnualDepreciation() {

        int year = 1;
        double annualDepreciation = 0;

        if (year == 1) {

        }

        annualDepreciation = (assetCost - salvageValue) / lifeOfAsset;

        //      System.out.println("annual depreciation = " + annualDepreciation);
        return annualDepreciation;
    } // end getAnnualDep()

    public double getAnnualDepreciation(int y) {

        double annualDepreciation = 0.0;
        double depreciationRate = 0.0;

        depreciationRate = 2 * (getAnnualDepreciation() / assetCost);

        annualDepreciation = getBeginningBalance(lifeOfAsset, 1) * depreciationRate;

        System.out.println("annualDepreciation = " + annualDepreciation);

        return annualDepreciation;
    } // end getAnnualDep(y)

    public double getBeginningBalance(int y, int m) {

        double beginningBalance = 0;
        int year = y - 1;
        beginningBalance = assetCost - (getAnnualDepreciation() * year);
        
        return beginningBalance;
    } // end getBegBal()

    public double getEndingBalance(int y, int m) {

        double endingBalance = 0;

        if ((m == 1) && (y == 1)) {
            endingBalance = assetCost;
        } else {
            endingBalance = assetCost - (getAnnualDepreciation() * y);
        }

        return endingBalance;
    }

    public String getAssetName() {
        return assetName;
    }

    public double getAssetCost() {
        return assetCost;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public double getSalvageValue() {
        return salvageValue;
    }

    public int getLifeOfItem() {
        return lifeOfAsset;
    }

    // Validate data
    public boolean isValid() {

        boolean valid = false;

        System.out.println("validName " + assetName + "\n" + assetCost + "\n" + salvageValue + "\n" + lifeOfAsset);

        if (assetName != null && !assetName.isEmpty()) {
            if (assetCost > 0) {
                if (salvageValue > 0 && salvageValue < assetCost) {
                    if (lifeOfAsset > 0) {
                        valid = true;
                    } else {
                        errorMessage = "The life of the asset must be a postive integer.";
                    }
                } else {
                    errorMessage = "The salvage value must be greater than zero and less than the asset's cost.";
                }
            } else {
                errorMessage = "The asset's cost must be a positive number";
            }
        } else {
            errorMessage = "You have not entered a valid name.";
        }

        return valid;

    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setAssetCost(double assetCost) {
        this.assetCost = assetCost;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public void setLifeOfItem(int lifeOfItem) {
        this.lifeOfAsset = lifeOfItem;
    }

}
