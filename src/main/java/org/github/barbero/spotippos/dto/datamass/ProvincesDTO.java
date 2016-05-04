package org.github.barbero.spotippos.dto.datamass;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.github.barbero.spotippos.dto.web.ProvinceDTO;

/**
 * @author Marcos Barbero
 */
@Data
public class ProvincesDTO {

    @JsonProperty("Gode")
    private ProvinceDTO gode;

    @JsonProperty("Ruja")
    private ProvinceDTO ruja;

    @JsonProperty("Jaby")
    private ProvinceDTO jaby;

    @JsonProperty("Scavy")
    private ProvinceDTO scavy;

    @JsonProperty("Groola")
    private ProvinceDTO groola;

    @JsonProperty("Nova")
    private ProvinceDTO nova;
}
