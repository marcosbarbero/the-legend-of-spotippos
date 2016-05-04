package org.github.barbero.spotippos.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.barbero.spotippos.dto.datamass.PropertiesDTO;
import org.github.barbero.spotippos.dto.datamass.ProvincesDTO;
import org.github.barbero.spotippos.services.DatamassService;
import org.github.barbero.spotippos.services.PropertyService;
import org.github.barbero.spotippos.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service to make datamass on application startup.
 *
 * @author Marcos Barbero
 */
@Service
public class DatamassServiceImpl implements DatamassService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${datamass.resources.provinces}")
    private Resource provincesResource;

    @Value("${datamass.resources.properties}")
    private Resource propertiesResource;

    private ProvinceService provinceService;

    private PropertyService propertyService;

    @Autowired
    public DatamassServiceImpl(final ProvinceService provinceService, final PropertyService propertyService) {
        this.provinceService = provinceService;
        this.propertyService = propertyService;
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void load() {
        this.provinces();
        this.properties();
    }

    /**
     * Load data of provinces into database.
     */
    private void provinces() {
        try {
            final ProvincesDTO provinces = MAPPER.readValue(this.provincesResource.getInputStream(), new TypeReference<ProvincesDTO>() {
            });
            this.save(provinces);
        } catch (IOException e) {
            throw new RuntimeException("Could not load provinces from datamass file.");
        }
    }

    /**
     * Load data of properties into database.
     */
    private void properties() {
        try {
            final PropertiesDTO properties = MAPPER.readValue(this.propertiesResource.getInputStream(), new TypeReference<PropertiesDTO>() {
            });
            this.propertyService.save(properties.getProperties());
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties from datamass file.");
        }
    }

    /**
     * Save the Spotippos provinces.
     *
     * @param provinces The provinces to be persisted
     */
    private void save(final ProvincesDTO provinces) {
        this.provinceService.save("Gode", provinces.getGode());
        this.provinceService.save("Groola", provinces.getGroola());
        this.provinceService.save("Jaby", provinces.getJaby());
        this.provinceService.save("Nova", provinces.getNova());
        this.provinceService.save("Ruja", provinces.getRuja());
        this.provinceService.save("Scavy", provinces.getScavy());
    }

}
