package com.example.goldprices.Controller;

import com.example.goldprices.Model.Dates;
import com.example.goldprices.Model.GoldPrice;
import com.example.goldprices.Service.GoldPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "URL", description = "Microservice to get gold prices and persist it into Postgres")
public class GoldPriceController {
    @Autowired
    private final GoldPriceService goldPriceService;

    public GoldPriceController(GoldPriceService goldPriceService) {
        this.goldPriceService = goldPriceService;
    }

    @Operation(summary = "Fetch Data from third API and store it into Posgres DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Found Data",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Dates are invalid",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @RequestMapping(value = "/v1/goldprice", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<GoldPrice> getGoldPrice(@RequestBody Dates date)
    {
        try {
           return goldPriceService.fetchAndPersistGoldPrices(date.getStartDate(), date.getEndDate());

        }
        catch (Exception e)
        {
          e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/v1/goldprice/{date}", method = RequestMethod.POST)
    public GoldPrice getOneGoldPrice(@PathVariable String date)
    {

            return goldPriceService.getGoldPriceByDate(date);
    }
    @Transactional
    @RequestMapping(value = "/v1/goldprice/delete-date-range/{startDate}/{endDate}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteByDateRange(
            @PathVariable("startDate") String startDate,
            @PathVariable("endDate") String endDate) {
        // Call the service method to perform deletions
        goldPriceService.deleteByDateRange(startDate, endDate);
        return ResponseEntity.ok("Gold prices deleted within the specified date range.");
    }

    @Transactional
    // Endpoint to delete prices based on a specific price value
    @DeleteMapping("/v1/goldprice/delete-by-price/{price}")
    public ResponseEntity<String> deleteByPrice(
            @PathVariable("price") double price) {
        // Call the service method to perform deletions
        goldPriceService.deleteByPrice(price);
        return ResponseEntity.ok("Gold prices deleted with the specified price value.");
    }

}
