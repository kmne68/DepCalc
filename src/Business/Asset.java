/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Keith Emery
 * Project 1
 * IS 287
 * Spring 2017
 * Beginning Balance in year 1 equals cost. Annual depreciation is rate * cost.
 *
 */
public class Asset {
// 36:15
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

    // Default constructor
    public Asset() {
        this.assetName = "";
        this.assetCost = 0;
        this.salvageValue = 0;
        this.lifeOfAsset = 0;
        this.built = false;
        this.errorMessage = "";
    }

    // Overloaded constructor
    public Asset(String name, double cost, double salvage, int life) {

        this.assetName = name;
        this.assetCost = cost;
        this.salvageValue = salvage;
        this.lifeOfAsset = life;
        this.built = false;
        this.errorMessage = "";

        if (isValid()) {
            build();
        }
    }

    public void build() {

        // depRate, depWork (temporary)
        try {
            this.beginningBalance = new double[this.lifeOfAsset][2];
            this.annualDepreciation = new double[this.lifeOfAsset][2];
            this.endingBalance = new double[this.lifeOfAsset][2];

            double depreciationStraightLine = (this.assetCost - this.salvageValue) / this.lifeOfAsset;
            double depreciationDoubleDeclining = (1.0 / this.lifeOfAsset) * 2.0;

            this.beginningBalance[0][0] = this.assetCost;   // beginning straight line           
            this.beginningBalance[0][1] = this.assetCost;   // beginning for double declining

            for (int year = 0; year < this.lifeOfAsset; year++) {
                // remember to right justify values.               
                if (year > 0) {
                    this.beginningBalance[year][0] = this.endingBalance[year - 1][0];
                }
                this.annualDepreciation[year][0] = depreciationStraightLine; // Straight line depricaition is the same in all years
                this.endingBalance[year][0] = this.beginningBalance[year][0] - depreciationStraightLine;
                
                // calculate with double declining method
                if(year > 0) {
                    this.beginningBalance[year][1] = this.endingBalance[year - 1][1];           // This line "breaks" the program
                }
                //       this.annualDepreciation[year][1] = beginningBalance[year][1] * depreciationDoubleDeclining;
                double depreciationWork = this.beginningBalance[year][1] * depreciationDoubleDeclining;
                if (depreciationWork < depreciationStraightLine) {
                    depreciationWork = depreciationStraightLine;
                }
                if ((this.beginningBalance[year][1] - depreciationWork) < this.salvageValue) {
                    depreciationWork = this.beginningBalance[year][1] - salvageValue;
                }
                this.annualDepreciation[year][1] = depreciationWork;
                this.endingBalance[year][1] = this.beginningBalance[year][1] - this.annualDepreciation[year][1];
           }
            built = true;
        
        } catch (Exception e) {
            this.errorMessage = "Build error: " + e.getMessage();
            this.built = false;
        }
    } // end build


    public double getAnnualDepreciation() {

        // Straight line depreciation
        if (isBuilt())
        return this.annualDepreciation[0][0];
        else
            return -1;
    }

    public double getAnnualDepreciation(int year) {

        // Double declining depreciation
        isBuilt();
        if (year < 1 || year > this.lifeOfAsset) {
            this.errorMessage = "Value requested for year out of range.";
            return -100;
        }
        return this.annualDepreciation[year - 1][1];
    } // end getAnnualDepreciation(y)

    public double getBeginningBalance(int year, String method) {

        isBuilt();

        if (year < 1 || year > this.lifeOfAsset) {
            this.errorMessage = "Value requested for year out of range.";
            return -1;
        }
        if (method.equalsIgnoreCase("S")) {
            return this.beginningBalance[year - 1][0];
        } else if (method.equalsIgnoreCase("D")) {
            return this.beginningBalance[year - 1][1];
        } else {
            this.errorMessage = "Value requested for illegal depreciation method.";
            return - 1;
        }
    } // end getBegBal()

    public double getEndingBalance(int year, String method) {

        isBuilt();
 
        if (year < 1 || year > this.lifeOfAsset) {
            this.errorMessage = "Value requested for year out of range.";
            return -1;
        }
        if (method.equalsIgnoreCase("S")) {
            return this.endingBalance[year - 1][0];
        } else if (method.equalsIgnoreCase("D")) {
            return this.endingBalance[year - 1][1];
        } else {
            this.errorMessage = "Value requested for illegal depreciation method.";
            return - 1;
        }
    } // end getEndingBalance()

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
    
    public boolean isBuilt() {
        
        if (!this.built) {
            if (isValid()) {
                build();
            }
            if (!this.built) {
                return false;
            }
        }
        return true;
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

    private boolean isValid() {
        this.errorMessage = "";
        if (this.assetName.isEmpty()) {
            this.errorMessage += "Asset name is missing. ";
        }
        if (this.assetCost <= 0) {
            this.errorMessage += "Cost is not positive. ";
        }
        if (this.salvageValue >= this.assetCost) {
            this.errorMessage += "Salvage is not less than cost. ";
        }
        if (this.salvageValue <= 0) {
            this.errorMessage += "Salvage is not positive. ";
        }
        if (this.lifeOfAsset <= 0) {
            this.errorMessage += "Life is not positive. ";
        }
        return this.errorMessage.isEmpty();
    }

    public String setSave(String path) {

        String rmsg = "";
        isBuilt();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));

            out.write(this.assetName);
            out.write(String.valueOf(this.assetCost + "\n"));
            out.newLine();
            out.write(String.valueOf(this.salvageValue));
            out.newLine();
            out.write(String.valueOf(this.lifeOfAsset));
            out.close();
            rmsg = "Files saved to: " + path;

        } catch (IOException e) {
            rmsg = "Unable to create save file.";
        } 
        return rmsg;
        
    }

    /* build process doesn't deal with double declining.
    try Dep = Beg. Balance * DDL Rate
    
    DDLRate = (1/life) * 2
    
    adjust dep 
        a) if dep < straight line, then dep can be switched to straight line
        b) if beginBalance - Dep < salvage then adjust dep so result = salvage
    
    do above or inside for statement inside build
     */
}
