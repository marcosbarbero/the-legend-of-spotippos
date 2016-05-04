package org.github.barbero.spotippos.dto.web;

import lombok.Data;
import org.github.barbero.spotippos.dto.datamass.BoundariesDTO;

/**
 * @author Marcos Barbero
 */
@Data
public class ProvinceDTO {

    private Integer id;

    private BoundariesDTO boundaries;
}
