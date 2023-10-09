package com.example.goldprices.Repository;

import com.example.goldprices.Model.GoldPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface GoldPriceRepository extends JpaRepository<GoldPrice, Long> {
    GoldPrice findOneByData(@Param("data") String data);

    @Transactional
    void deleteByDataBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Transactional
    void deleteByCena(@Param("cena") double cena);

}
