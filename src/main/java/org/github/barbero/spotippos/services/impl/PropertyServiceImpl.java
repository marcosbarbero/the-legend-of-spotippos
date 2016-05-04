package org.github.barbero.spotippos.services.impl;

import org.github.barbero.spotippos.dto.web.PropertySearchDTO;
import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.helper.Utils;
import org.github.barbero.spotippos.model.entity.Property;
import org.github.barbero.spotippos.model.repository.PropertyRepository;
import org.github.barbero.spotippos.services.PropertyService;
import org.github.barbero.spotippos.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcos Barbero
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private ProvinceService provinceService;

    @Autowired
    public PropertyServiceImpl(final PropertyRepository propertyRepository, final ProvinceService provinceService) {
        this.propertyRepository = propertyRepository;
        this.provinceService = provinceService;
    }

    private Collection<Property> properties(final List<RequestPropertyDTO> propertyDTOs) {
        Collection<Property> properties = new ArrayList<>();
        propertyDTOs.forEach(propertyDTO -> properties.add(Utils.copyProperties(propertyDTO)));
        return properties;
    }

    private PropertySearchDTO propertySearchDTO(List<Property> properties) {
        PropertySearchDTO propertySearchDTO = null;
        if (!properties.isEmpty()) {
            propertySearchDTO = new PropertySearchDTO();
            propertySearchDTO.setFoundProperties(properties.size());
            List<ResponsePropertyDTO> propertyDTOs = new ArrayList<>();
            properties.forEach(property -> propertyDTOs.add(Utils.copyProperties(property).get()));
            propertySearchDTO.setProperties(propertyDTOs);
        }
        return propertySearchDTO;
    }

    @Override
    @Transactional
    public ResponsePropertyDTO save(final RequestPropertyDTO propertyDTO) {
        final Property saved = this.propertyRepository.save(Utils.copyProperties(propertyDTO));
        return Utils.copyProperties(saved).get();
    }

    @Override
    @Transactional
    public void save(final List<RequestPropertyDTO> propertyDTOs) {
        this.propertyRepository.save(this.properties(propertyDTOs));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResponsePropertyDTO> get(final Long id) {
        ResponsePropertyDTO propertyDTO = null;
        final Property property = this.propertyRepository.findOne(id);
        final Optional<ResponsePropertyDTO> optionalProperty = Utils.copyProperties(property);
        if (optionalProperty.isPresent()) {
            propertyDTO = optionalProperty.get();
            propertyDTO.setProvinces(this.provinceService.getProvinceNames(propertyDTO.getX(), propertyDTO.getY()));
        }
        return Optional.ofNullable(propertyDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PropertySearchDTO> get(final Integer ax, final Integer ay, final Integer bx, final Integer by) {
        return Optional.ofNullable(propertySearchDTO(this.propertyRepository.findProperties(ax, ay, bx, by)));
    }
}
