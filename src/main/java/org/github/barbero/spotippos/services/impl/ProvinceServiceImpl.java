package org.github.barbero.spotippos.services.impl;

import org.github.barbero.spotippos.dto.web.ProvinceDTO;
import org.github.barbero.spotippos.helper.Utils;
import org.github.barbero.spotippos.model.entity.Province;
import org.github.barbero.spotippos.model.repository.ProvinceRepository;
import org.github.barbero.spotippos.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marcos Barbero
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImpl(final ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    private String[] getProvincesName(final List<Province> provinces) {
        String[] names = new String[provinces.size()];
        for (int i = 0; i < provinces.size(); i++) {
            names[i] = provinces.get(i).getName();
        }
        return names;
    }

    @Override
    @Transactional
    public void save(final String name, final ProvinceDTO provinceDTO) {
        this.provinceRepository.save(Utils.copyProperties(name, provinceDTO));
    }


    @Override
    @Transactional(readOnly = true)
    public String[] getProvinceNames(final Integer x, final Integer y) {
        final List<Province> provinces = this.provinceRepository.findProvinces(x, y);
        return getProvincesName(provinces);
    }
}
