package org.example.service;

import org.example.model.*;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmissionService {

    public void addAllCategories() {
        YearReport yearReport = readYearReportFromUrl("http://cdr.eionet.europa.eu/ie/eu/mmr/art04-13-14_lcds_pams_projections/projections/envvxoklg/MMR_IRArticle23T1_IE_2016v2.xml");
    }

    private YearReport readYearReportFromUrl(String fileUrl) {

        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS) // Handle redirects
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fileUrl))
                    .GET()
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to fetch file: HTTP code " + response.statusCode());
            }

            ProjectionsTableHandler handler = new ProjectionsTableHandler();
            try (InputStream inputStream = response.body()) {

                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                saxParser.parse(new InputSource(inputStream), handler);
                List<Row> rows = handler.getRows();
                System.out.println("Parsed " + rows.size() + " rows.");
                rows.forEach(System.out::println);
            }
            List<Emission> emissions = handler.getRows().stream()
                    .map(row -> new Emission(
                            UUID.randomUUID(),
                            row.getYear(),
                            row.getScenario(),
                            row.getGasUnits(),
                            row.getNk(),
                            row.getValue(),
                            new Category(UUID.randomUUID(), row.getCategory(), "")
                    )).collect(Collectors.toList());
            return new YearReport(UUID.randomUUID(), handler.getInventorySubmissionYear(), handler.getMs(), emissions);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
