package org.github.barbero.spotippos.dto.web;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Marcos Barbero
 */
@Data
public abstract class AbstractPropertyDTO {

    @Min(0)
    @Max(1400)
    private int x;

    @Min(0)
    @Max(1000)
    private int y;

    @Min(1)
    @Max(5)
    private int beds;

    @Min(1)
    @Max(4)
    private int baths;

    @Min(20)
    @Max(240)
    private int squareMeters;
}
