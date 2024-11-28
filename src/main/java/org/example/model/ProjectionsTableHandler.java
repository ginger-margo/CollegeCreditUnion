package org.example.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ProjectionsTableHandler extends DefaultHandler {

    private List<Row> rows = new ArrayList<>();
    private Row currentRow;
    private StringBuilder currentValue = new StringBuilder();

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("Row".equals(qName)) {
            currentRow = new Row();
        }
        currentValue.setLength(0); // Clear the buffer
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentRow != null) {
            switch (qName) {
                case "Category__1_3":
                    currentRow.setCategory(currentValue.toString());
                    break;
                case "Year":
                    currentRow.setYear(Integer.parseInt(currentValue.toString()));
                    break;
                case "Scenario":
                    currentRow.setScenario(currentValue.toString());
                    break;
                case "Gas___Units":
                    currentRow.setGasUnits(currentValue.toString());
                    break;
                case "NK":
                    currentRow.setNk(currentValue.toString());
                    break;
                case "Value":
                    currentRow.setValue(Double.parseDouble(currentValue.toString().isBlank() ? "0" : currentValue.toString()));
                    break;
                case "Row":
                    if (currentRow.shouldBeAddedToAnalytics()) {
                        rows.add(currentRow);
                    }
                    currentRow = null;
                    break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }
}