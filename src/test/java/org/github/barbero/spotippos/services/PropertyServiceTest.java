package org.github.barbero.spotippos.services;

import org.github.barbero.spotippos.Application;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.dto.web.PropertySearchDTO;
import org.github.barbero.spotippos.helper.Given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_getProperty_withNullId_shouldReturnError() {
        this.propertyService.get(null);
    }

    @Test
    public void test_getProperty_withValidId_shouldReturnError() {
        final Optional<ResponsePropertyDTO> propertyDTO = this.propertyService.get(1L);
        Assert.isTrue(propertyDTO.isPresent());
    }

    @Test
    public void test_getPropertySearch_withNullValues_shouldReturnError() {
        final Optional<PropertySearchDTO> optional = this.propertyService.get(null, null, null, null);
        Assert.isTrue(!optional.isPresent());
    }

    @Test
    public void test_getPropertySearch_withValidValues_shouldNotReturnError() {
        final Optional<PropertySearchDTO> optional = this.propertyService.get(80, 700, 300, 250);
        Assert.isTrue(optional.isPresent());
    }

    @Test
    public void test_getPropertySearch_withValidValues_shouldReturnTwoProperties() {
        final Optional<PropertySearchDTO> optional = this.propertyService.get(80, 1000, 600, 500);
        Assert.isTrue(optional.isPresent());
        final PropertySearchDTO propertySearchDTO = optional.get();
        Assert.isTrue(propertySearchDTO.getFoundProperties() > 1);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_saveOne_withNullValue_shouldReturnError() {
        this.propertyService.save(Given.nullPropertyDTO());
    }

    @Test
    public void test_saveOne_withValidValue_shouldNotReturnError() {
        final ResponsePropertyDTO save = this.propertyService.save(Given.validPropertyDTO());
        Assert.notNull(save);
    }

    @Test
    public void test_saveList_withValidValue_shouldNotReturnError() {
        this.propertyService.save(Arrays.asList(Given.validPropertyDTO()));
    }
}
