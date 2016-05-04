package org.github.barbero.spotippos.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Marcos Barbero
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePropertyDTO extends AbstractPropertyDTO {

    private Long id;

    private String[] provinces;

}
