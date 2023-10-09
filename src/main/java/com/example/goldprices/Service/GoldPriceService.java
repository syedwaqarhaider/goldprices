package com.example.goldprices.Service;

// GoldPriceService.java
import com.example.goldprices.Model.GoldPrice;
import com.example.goldprices.Repository.GoldPriceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoldPriceService {
    private final GoldPriceRepository goldPriceRepository;
    @Autowired
    public GoldPriceService(GoldPriceRepository goldPriceRepository) {
        this.goldPriceRepository = goldPriceRepository;

    }
    @Value("${url.gold.prices}")
    private String apiUrl;




    public List<GoldPrice> fetchAndPersistGoldPrices(String startDate, String endDate) throws Exception {
         apiUrl += startDate + "/" + endDate;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON array into an array of GoldPrice objects
        GoldPrice[] goldPriceArray = objectMapper.readValue(jsonResponse, GoldPrice[].class);

        // Convert the array to a List
        List<GoldPrice> goldPrices = Arrays.asList(goldPriceArray);

        // Save all GoldPrice objects to the database
        goldPriceRepository.saveAll(goldPrices);

        return goldPrices;
    }

    public GoldPrice getGoldPriceByDate(String date)
    {
        return this.goldPriceRepository.findOneByData(date);
    }

    public void deleteByDateRange(String startDate, String endDate) {
        // Implement logic to delete prices within the specified date range
        goldPriceRepository.deleteByDataBetween(startDate, endDate);
    }

    public void deleteByPrice(double price) {
        // Implement logic to delete prices with the specified price value
        goldPriceRepository.deleteByCena(price);
    }
}

