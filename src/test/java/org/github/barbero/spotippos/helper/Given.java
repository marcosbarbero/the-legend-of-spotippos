package org.github.barbero.spotippos.helper;

import org.github.barbero.spotippos.dto.datamass.BoundariesDTO;
import org.github.barbero.spotippos.dto.web.AxisDTO;
import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.dto.web.ProvinceDTO;
import org.github.barbero.spotippos.model.entity.Axis;
import org.github.barbero.spotippos.model.entity.Boundaries;
import org.github.barbero.spotippos.model.entity.Property;
import org.github.barbero.spotippos.model.entity.Province;

/**
 * Util class for unit tests.
 *
 * @author Marcos Barbero
 */
public class Given {

    public static Province validProvince(Long id) {
        Boundaries boundaries = new Boundaries();
        boundaries.setBottomRight(new Axis(null, 600, 500));
        boundaries.setUpperLeft(new Axis(null, 0, 1000));

        Province province = new Province();
        province.setId(id);
        province.setName("Gode");
        province.setBoundaries(boundaries);

        return province;
    }

    public static ProvinceDTO validProvinceDTO() {
        BoundariesDTO boundaries = new BoundariesDTO();
        boundaries.setBottomRight(new AxisDTO(600, 500));
        boundaries.setUpperLeft(new AxisDTO(600, 500));

        ProvinceDTO province = new ProvinceDTO();
        province.setBoundaries(boundaries);

        return province;
    }

    public static ProvinceDTO provinceDTONullBoundaries() {
        ProvinceDTO province = new ProvinceDTO();
        province.setBoundaries(null);

        return province;
    }

    public static ProvinceDTO provinceDTONullUpperLeft() {
        BoundariesDTO boundaries = new BoundariesDTO();
        boundaries.setBottomRight(new AxisDTO(0, 1000));
        boundaries.setUpperLeft(null);

        ProvinceDTO province = new ProvinceDTO();
        province.setBoundaries(boundaries);

        return province;
    }

    public static ProvinceDTO provinceDTONullBottomRight() {
        BoundariesDTO boundaries = new BoundariesDTO();
        boundaries.setBottomRight(null);
        boundaries.setUpperLeft(new AxisDTO(0, 1000));

        ProvinceDTO province = new ProvinceDTO();
        province.setBoundaries(boundaries);

        return province;
    }


    public static RequestPropertyDTO nullPropertyDTO() {
        return null;
    }

    public static RequestPropertyDTO validPropertyDTO() {
        RequestPropertyDTO propertyDTO = new RequestPropertyDTO();
        propertyDTO.setBaths(1);
        propertyDTO.setBeds(1);
        propertyDTO.setSquareMeters(200);
        propertyDTO.setX(100);
        propertyDTO.setY(650);
        return propertyDTO;
    }

    public static Property nullProperty() {
        return null;
    }

    public static Property validProperty(Long id) {
        Property property = new Property();
        property.setId(id);
        property.setBaths(1);
        property.setBeds(1);
        property.setSquareMeters(200);
        property.setAxis(new Axis(null, 50, 50));
        return property;
    }

    public static Property propertyWithNullAxis() {
        Property property = new Property();
        property.setId(1L);
        property.setBaths(1);
        property.setBeds(1);
        property.setSquareMeters(200);
        property.setAxis(null);
        return property;
    }
}
