package org.example;


import org.example.model.ProjectionsTableHandler;
import org.example.model.Row;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

//@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        String fileUrl = "http://cdr.eionet.europa.eu/ie/eu/mmr/art04-13-14_lcds_pams_projections/projections/envvxoklg/MMR_IRArticle23T1_IE_2016v2.xml";

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

            try (InputStream inputStream = response.body()) {

                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                ProjectionsTableHandler handler = new ProjectionsTableHandler();

                saxParser.parse(new InputSource(inputStream), handler);
                List<Row> rows = handler.getRows();
                System.out.println("Parsed " + rows.size() + " rows.");
                rows.forEach(System.out::println);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}