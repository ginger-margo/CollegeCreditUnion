package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.EmissionDTO;
import org.example.model.*;
import org.example.repository.EmissionRepository;
import org.example.repository.YearReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmissionService {

    private final YearReportRepository yearReportRepository;
    private final CategoryService categoryService;

    private final EmissionRepository emissionRepository;

    @Transactional
    public void addAllCategories() {
        YearReport yearReport = readYearReportFromUrl("http://cdr.eionet.europa.eu/ie/eu/mmr/art04-13-14_lcds_pams_projections/projections/envvxoklg/MMR_IRArticle23T1_IE_2016v2.xml");
        yearReportRepository.save(yearReport);
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
            }
            List<Emission> emissions = handler.getRows().stream()
                    .map(row -> Emission.builder()
                            .year(row.getYear())
                            .scenario(row.getScenario())
                            .gasUnits(row.getGasUnits())
                            .nk(row.getNk())
                            .value(row.getValue())
                            .category(createCategoryIfNotExists(row))
                            .build()
                    ).collect(Collectors.toList());
            return new YearReport(null, handler.getInventorySubmissionYear(), handler.getMs(), emissions);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Category createCategoryIfNotExists(Row row) {
        return categoryService.createCategoryIfNotExists(row.getCategory());
    }

    public List<Emission> getAllEmissions() {
       return (List<Emission>) emissionRepository.findAll(); // findAll returns Iterable, casting it to List
    }

    public Optional<Emission> findEmissionById(UUID id) {
        return emissionRepository.findById(id); // no casting because findById returns Optional
    }

    @Transactional
    public Emission addNewEmission(EmissionDTO emissionDTO) {
        Emission emission = Emission.builder()
                .year(emissionDTO.year())
                .scenario(emissionDTO.scenario())
                .gasUnits(emissionDTO.gasUnits())
                .nk(emissionDTO.nk())
                .value(emissionDTO.value())
                .category(categoryService.createCategoryIfNotExists(emissionDTO.category()))
                .build();
        return emissionRepository.save(emission);
    }

    public Emission updateEmission(UUID id, EmissionDTO emissionDTO) {
        Optional<Emission> emissionOPT = emissionRepository.findById(id);
        if (emissionOPT.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            Emission emission = emissionOPT.get();
            emission.setYear(emissionDTO.year());
            emission.setCategory(categoryService.createCategoryIfNotExists(emissionDTO.category()));
            emission.setGasUnits(emissionDTO.gasUnits());
            emission.setNk(emissionDTO.nk());
            emission.setValue(emissionDTO.value());
            emission.setScenario(emissionDTO.scenario());
            return emissionRepository.save(emission);
        }
    }

    public void deleteEmission(UUID id) {
        emissionRepository.deleteById(id);
    }
}
