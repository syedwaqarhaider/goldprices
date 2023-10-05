package com.example.goldprices.Model;

import com.sun.istack.NotNull;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Dates {
    @NotEmpty
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Date should be in YYYY-MM-DD format.")
    private String startDate;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Date should be in YYYY-MM-DD format.")
    private String endDate;

    public Dates(@NotEmpty @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Date should be in YYYY-MM-DD format.") String startDate, @NotEmpty @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Date should be in YYYY-MM-DD format.") String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
