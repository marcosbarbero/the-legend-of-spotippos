package org.github.barbero.spotippos.helper;

import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.dto.web.ProvinceDTO;
import org.github.barbero.spotippos.model.entity.Property;
import org.github.barbero.spotippos.model.entity.Province;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author Marcos Barbero
 */
public class UtilsTest {

    private static final String PROVINCE_NAME = "Gode";

    @Test
    public void test_copyProperties_provinceDTO2Province_withNullValues_shouldNotReturnError() {
        final Province province = Utils.copyProperties(null, null);
        Assert.assertNull(province);
    }

    @Test
    public void test_copyProperties_provinceDTO2Province_withNullProvinceDTO_shouldNotReturnError() {
        final Province province = Utils.copyProperties(PROVINCE_NAME, null);
        Assert.assertNull(province);
    }

    @Test
    public void test_copyProperties_provinceDTO2Province_withNullName_shouldNotReturnError() {
        final Province province = Utils.copyProperties(null, Given.validProvinceDTO());
        Assert.assertNull(province);
    }

    @Test
    public void test_copyProperties_provinceDTO2Province_withValidValues_shouldNotReturnError() {
        final ProvinceDTO provinceDTO = Given.validProvinceDTO();
        final Province province = Utils.copyProperties(PROVINCE_NAME, provinceDTO);
        Assert.assertNotNull(province);
        Assert.assertEquals(province.getName(), PROVINCE_NAME);
        Assert.assertTrue(province.getBoundaries().getBottomRight().getX() == provinceDTO.getBoundaries().getBottomRight().getX());
        Assert.assertTrue(province.getBoundaries().getBottomRight().getY() == provinceDTO.getBoundaries().getBottomRight().getY());
        Assert.assertTrue(province.getBoundaries().getUpperLeft().getX() == provinceDTO.getBoundaries().getUpperLeft().getX());
        Assert.assertTrue(province.getBoundaries().getUpperLeft().getY() == provinceDTO.getBoundaries().getUpperLeft().getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyProperties_provinceDTO2Province_withNullBoundaries_shouldReturnError() {
        Utils.copyProperties(PROVINCE_NAME, Given.provinceDTONullBoundaries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyProperties_provinceDTO2Province_withNullUpperLeft_shouldReturnError() {
        Utils.copyProperties(PROVINCE_NAME, Given.provinceDTONullUpperLeft());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyProperties_provinceDTO2Province_withNullBottomRight_shouldReturnError() {
        Utils.copyProperties(PROVINCE_NAME, Given.provinceDTONullBottomRight());
    }

    @Test
    public void test_copyProperties_propertyDTO2Property_withNullValue_shouldNotReturnError() {
        final Property property = Utils.copyProperties(Given.nullPropertyDTO());
        Assert.assertNull(property);
    }

    @Test
    public void test_copyProperties_propertyDTO2Property_withValidValue_shouldNotReturnError() {
        final RequestPropertyDTO propertyDTO = Given.validPropertyDTO();
        final Property property = Utils.copyProperties(propertyDTO);
        Assert.assertNotNull(property);
        Assert.assertTrue(property.getAxis().getX() == propertyDTO.getX());
        Assert.assertTrue(property.getAxis().getY() == propertyDTO.getY());
        Assert.assertTrue(property.getBaths() == propertyDTO.getBaths());
        Assert.assertTrue(property.getBeds() == propertyDTO.getBeds());
        Assert.assertTrue(property.getSquareMeters() == propertyDTO.getSquareMeters());
    }

    @Test
    public void test_copyProperties_property2PropertyDTO_withNullValue_shouldNotReturnError() {
        final Optional<ResponsePropertyDTO> property = Utils.copyProperties(Given.nullProperty());
        Assert.assertFalse(property.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyProperties_property2PropertyDTO_withNullId_shouldReturnError() {
        Property property = Given.validProperty(null);
        Utils.copyProperties(property);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyProperties_property2PropertyDTO_withNullAxis_shouldReturnError() {
        Property property = Given.propertyWithNullAxis();
        Utils.copyProperties(property);
    }

    @Test
    public void test_copyProperties_property2PropertyDTO_withValidValue_shouldReturnError() {
        Property property = Given.validProperty(1L);
        final Optional<ResponsePropertyDTO> optional = Utils.copyProperties(property);
        Assert.assertTrue(optional.isPresent());
        final ResponsePropertyDTO propertyDTO = optional.get();

        Assert.assertTrue(property.getId().intValue() == propertyDTO.getId());
        Assert.assertTrue(property.getAxis().getX() == propertyDTO.getX());
        Assert.assertTrue(property.getAxis().getY() == propertyDTO.getY());
        Assert.assertTrue(property.getBaths() == propertyDTO.getBaths());
        Assert.assertTrue(property.getBeds() == propertyDTO.getBeds());
        Assert.assertTrue(property.getSquareMeters() == propertyDTO.getSquareMeters());
    }
}
