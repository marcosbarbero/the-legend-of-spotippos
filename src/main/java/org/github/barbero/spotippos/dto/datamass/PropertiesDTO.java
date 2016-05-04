package org.github.barbero.spotippos.dto.datamass;

import lombok.Data;
import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcos Barbero
 */
@Data
public class PropertiesDTO {
    private int totalProperties;
    private List<RequestPropertyDTO> properties = new ArrayList<>();
}
