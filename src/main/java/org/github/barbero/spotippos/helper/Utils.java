package org.github.barbero.spotippos.helper;

import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.dto.web.ProvinceDTO;
import org.github.barbero.spotippos.model.entity.Axis;
import org.github.barbero.spotippos.model.entity.Boundaries;
import org.github.barbero.spotippos.model.entity.Property;
import org.github.barbero.spotippos.model.entity.Province;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Helper class with method utils.
 *
 * @author Marcos Barbero
 */
public class Utils {

    public static Province copyProperties(final String name, final ProvinceDTO provinceDTO) {
        Province province = null;
        if (!StringUtils.isEmpty(name) && !ObjectUtils.isEmpty(provinceDTO)) {
            Assert.notNull(provinceDTO.getBoundaries(), "Boundaries cannot be null");
            Assert.notNull(provinceDTO.getBoundaries().getUpperLeft(), "Boundaries 'upperLeft' cannot be null");
            Assert.notNull(provinceDTO.getBoundaries().getBottomRight(), "Boundaries 'bottomRight' cannot be null");

            Axis upperLeft = new Axis();
            BeanUtils.copyProperties(provinceDTO.getBoundaries().getUpperLeft(), upperLeft);
            Axis bottomRight = new Axis();
            BeanUtils.copyProperties(provinceDTO.getBoundaries().getBottomRight(), bottomRight);

            Boundaries boundaries = new Boundaries();
            boundaries.setBottomRight(bottomRight);
            boundaries.setUpperLeft(upperLeft);

            province = new Province();
            province.setName(name);
            province.setBoundaries(boundaries);
        }
        return province;
    }

    public static Property copyProperties(final RequestPropertyDTO propertyDTO) {
        Property property = null;
        if (!ObjectUtils.isEmpty(propertyDTO)) {
            property = new Property();
            BeanUtils.copyProperties(propertyDTO, property);
            Axis axis = new Axis();
            BeanUtils.copyProperties(propertyDTO, axis);
            property.setAxis(axis);
        }
        return property;
    }

    public static Optional<ResponsePropertyDTO> copyProperties(final Property property) {
        ResponsePropertyDTO propertyDTO = null;
        if (property != null) {
            Assert.notNull(property.getAxis(), "Axis must not be null");
            Assert.notNull(property.getId(), "The field 'id' must not be null");
            propertyDTO = new ResponsePropertyDTO();
            BeanUtils.copyProperties(property, propertyDTO);
            BeanUtils.copyProperties(property.getAxis(), propertyDTO, "id");
        }
        return Optional.ofNullable(propertyDTO);
    }
}
