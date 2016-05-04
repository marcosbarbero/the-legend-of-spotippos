package org.github.barbero.spotippos.services;

import org.github.barbero.spotippos.dto.web.ProvinceDTO;
import org.github.barbero.spotippos.model.entity.Province;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer of ${@link ProvinceDTO}.
 *
 * @author Marcos Barbero
 */
public interface ProvinceService {

    /**
     * Persist a ${@link ProvinceDTO} into the database.
     *
     * @param name        The province name to be persisted
     * @param provinceDTO The ${@link ProvinceDTO} to be persisted
     */
    void save(String name, ProvinceDTO provinceDTO);

    /**
     * Return a list of ${@link Province} names for the given position.
     *
     * @param x The axis x
     * @param y The axis y
     * @return A list of ${@link Province} names.
     */
    String[] getProvinceNames(Integer x, Integer y);
}
