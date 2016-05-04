package org.github.barbero.spotippos.services;

import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.dto.web.PropertySearchDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service layer of ${@link ResponsePropertyDTO}.
 *
 * @author Marcos Barbero
 */
public interface PropertyService {

    /**
     * Persist a ${@link ResponsePropertyDTO} into the database.
     *
     * @param propertyDTO The ${@link RequestPropertyDTO} to be persisted
     * @return The ${@link ResponsePropertyDTO} persisted into the database
     */
    ResponsePropertyDTO save(RequestPropertyDTO propertyDTO);

    /**
     * Persist a list of ${@link ResponsePropertyDTO} into the database.
     *
     * @param propertyDTOs A list of ${@link ResponsePropertyDTO}
     */
    void save(List<RequestPropertyDTO> propertyDTOs);

    /**
     * Return an ${@link Optional< ResponsePropertyDTO >} for given id.
     *
     * @param id The id
     * @return An ${@link Optional< ResponsePropertyDTO >} from database
     */
    Optional<ResponsePropertyDTO> get(Long id);

    /**
     * Return a ${@link PropertySearchDTO} for the given coordinates.
     *
     * @param ax The Upper left x
     * @param ay The Upper left y
     * @param bx The bottom right x
     * @param by The bottom right y
     * @return ${@link Optional<PropertySearchDTO>}
     */
    Optional<PropertySearchDTO> get(Integer ax, Integer ay, Integer bx, Integer by);
}
