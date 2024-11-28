package org.example.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "ProjectionsTable1")
public class ProjectionsTable {

    private int inventorySubmissionYear;
    private String ms;
    private List<Row> rows;

    @XmlElement(name = "Inventory_Submission_year")
    public int getInventorySubmissionYear() {
        return inventorySubmissionYear;
    }

    public void setInventorySubmissionYear(int inventorySubmissionYear) {
        this.inventorySubmissionYear = inventorySubmissionYear;
    }

    @XmlElement(name = "MS")
    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    @XmlElement(name = "Row")
    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ProjectionsTable1{" +
                "inventorySubmissionYear=" + inventorySubmissionYear +
                ", ms='" + ms + '\'' +
                ", rows=" + rows +
                '}';
    }
}
