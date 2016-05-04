package org.github.barbero.spotippos.dto.web;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Marcos Barbero
 */
@Data
public class PropertySearchDTO implements Serializable {
    private static final long serialVersionUID = 487360462624493640L;

    private int foundProperties;
    private List<ResponsePropertyDTO> properties;
}
