package com.github.diegofernandodasilva.covid19tracker.config;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class WuhanCoronavirusProps {

    public static String URI_BASE = "/jhu-edu";
    public static String STATISTICS = URI_BASE + "/brief";
    public static String LATEST = URI_BASE + "/latest";
    public static String ISO2_NAME_PARAM = "iso2";
    public static String ONLY_COUNTRIES_NAME_PARAM = "onlyCountries";


    @NotEmpty
    private String url;
}
