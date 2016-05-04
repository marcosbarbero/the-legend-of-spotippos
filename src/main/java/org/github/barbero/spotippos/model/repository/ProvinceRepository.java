package org.github.barbero.spotippos.model.repository;

import org.github.barbero.spotippos.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marcos Barbero
 */
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    String SQL = "select p.* from province p " +
            "   inner join boundaries b on b.id = p.boundaries_id " +
            "   left join axis bottom" +
            "      on bottom.id = b.bottom_right_id" +
            "   left join axis upp" +
            "      on upp.id = b.upper_left_id" +
            "  where" +
            "    bottom.x >= :axisX" +
            "    and upp.x <= :axisX" +
            "    and bottom.y <= :axisY" +
            "    and upp.y >= :axisY";

    @Query(value = SQL, nativeQuery = true)
    List<Province> findProvinces(@Param("axisX") Integer axisX, @Param("axisY") Integer axisY);
}
