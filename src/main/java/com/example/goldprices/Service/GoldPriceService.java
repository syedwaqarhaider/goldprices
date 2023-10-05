package com.example.goldprices.Service;

// GoldPriceService.java
import com.example.goldprices.Model.GoldPrice;
import com.example.goldprices.Repository.GoldPriceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Service
public class GoldPriceService {
    private final GoldPriceRepository goldPriceRepository;



    @Autowired
    public GoldPriceService(GoldPriceRepository goldPriceRepository) {
        this.goldPriceRepository = goldPriceRepository;

    }

    public List<GoldPrice> fetchAndPersistGoldPrices(String startDate, String endDate) throws Exception {
        String apiUrl = "https://api.nbp.pl/api/cenyzlota/" + startDate + "/" + endDate;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<GoldPrice> goldPrices= new ArrayList<>();

        try {
            // Parse the JSON array
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Iterate through the array elements
            for (JsonNode element : jsonNode) {
                String data = element.get("data").asText();
                double cena = element.get("cena").asDouble();
                GoldPrice goldPrice= new GoldPrice();

                goldPrice.setDate(data);
                goldPrice.setPrice(cena);


                goldPriceRepository.save(goldPrice);
                goldPrices.add(goldPrice);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //goldPriceRepository.saveAll(goldPrices);
        return goldPrices;
    }

    public GoldPrice getGoldPriceByDate(String date)
    {
        return this.goldPriceRepository.findOneByDate(date);
    }

    public void deleteByDateRange(String startDate, String endDate) {
        // Implement logic to delete prices within the specified date range
        goldPriceRepository.deleteByDateRange(startDate, endDate);
    }

    public void deleteByPrice(double price) {
        // Implement logic to delete prices with the specified price value
        goldPriceRepository.deleteByPrice(price);
    }
}

