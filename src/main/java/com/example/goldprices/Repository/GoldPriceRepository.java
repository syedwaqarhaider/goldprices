package com.example.goldprices.Repository;

import com.example.goldprices.Model.GoldPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoldPriceRepository extends JpaRepository<GoldPrice, Long> {
    GoldPrice findOneByDate(@Param("date") String date);

    @Modifying
    @Query("DELETE FROM GoldPrice WHERE date BETWEEN :startDate AND :endDate")
    void deleteByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Modifying
    @Query("DELETE FROM GoldPrice WHERE price = :price")
    void deleteByPrice(@Param("price") double price);

}
