package org.github.barbero.spotippos.services;

import org.github.barbero.spotippos.Application;
import org.github.barbero.spotippos.helper.Given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProvinceServiceTest {

    @Autowired
    private ProvinceService service;

    @Test
    public void test_findProvinces_withValidRegion_shouldNotReturnError() {
        final String[] provinceNames = this.service.getProvinceNames(450, 580);
        Assert.isTrue(provinceNames.length == 2);
    }

    @Test
    public void test_findProvinces_withInvalidRegion_shouldNotReturnError() {
        final String[] provinceNames = this.service.getProvinceNames(450, -580);
        Assert.isTrue(ObjectUtils.isEmpty(provinceNames));
    }

    @Test
    public void test_save_withValidValues_shouldNotReturnError() {
        this.service.save("Jabol", Given.validProvinceDTO());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_save_withNullValues_shouldReturnError() {
        this.service.save(null, null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_save_withNullName_shouldReturnError() {
        this.service.save("Gode", null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_save_withNullProvince_shouldReturnError() {
        this.service.save(null, Given.validProvinceDTO());
    }

}
