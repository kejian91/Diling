package com.diling.model;

public class Result {
    private int passNumber;
    private int identifiedNumber;
    private int unidentifiedNumber;
    private double identifiedRate;

    public int getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(int passNumber) {
        this.passNumber = passNumber;
    }

    public int getIdentifiedNumber() {
        return identifiedNumber;
    }

    public void setIdentifiedNumber(int identifiedNumber) {
        this.identifiedNumber = identifiedNumber;
    }

    public int getUnidentifiedNumber() {
        return unidentifiedNumber;
    }

    public void setUnidentifiedNumber(int unidentifiedNumber) {
        this.unidentifiedNumber = unidentifiedNumber;
    }

    public double getIdentifiedRate() {
        return identifiedRate;
    }

    public void setIdentifiedRate(double identifiedRate) {
        this.identifiedRate = identifiedRate;
    }

    public void addPassNumber() {
        this.passNumber++;
    }

    public void addIdentifiedNumber() {
        this.identifiedNumber++;
    }

    public void addUnidentifiedNumber() {
        this.unidentifiedNumber++;
    }
}
