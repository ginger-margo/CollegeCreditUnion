package org.example.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Row")
public class Row {
    private String category;
    private int year;
    private String scenario;
    private String gasUnits;
    private String nk;
    private double value;

    @XmlElement(name = "Category__1_3")
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    @XmlElement(name = "Year")
    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    @XmlElement(name = "Scenario")
    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
    public String getScenario() {
        return scenario;
    }

    @XmlElement(name = "Gas___Units")
    public void setGasUnits(String gasUnits) {
        this.gasUnits = gasUnits;
    }
    public String getGasUnits() {
        return gasUnits;
    }

    @XmlElement(name = "NK")
    public void setNk(String nk) {
        this.nk = nk;
    }
    public String getNk() {
        return nk;
    }

    @XmlElement(name = "Value")
    public void setValue(double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Row [category=" + category + ", year=" + year + ", scenario=" + scenario +
                ", gasUnits=" + gasUnits + ", nk=" + nk + ", value=" + value + "]";
    }

    public boolean shouldBeAddedToAnalytics() {
        return year == 2023
                && "WEM".equals(scenario)
                && value > 0d;
    }
}