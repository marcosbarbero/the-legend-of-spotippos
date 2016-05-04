package org.github.barbero.spotippos.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Marcos Barbero
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "x", "y", "beds", "baths", "provinces", "squareMeters"})
public class ResponsePropertyDTO extends AbstractPropertyDTO {

    private Long id;

    private String[] provinces;

}
