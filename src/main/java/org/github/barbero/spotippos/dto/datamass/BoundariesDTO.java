package org.github.barbero.spotippos.dto.datamass;

import lombok.Data;
import org.github.barbero.spotippos.dto.web.AxisDTO;

/**
 * @author Marcos Barbero
 */
@Data
public class BoundariesDTO {

    private AxisDTO upperLeft;
    private AxisDTO bottomRight;

}
