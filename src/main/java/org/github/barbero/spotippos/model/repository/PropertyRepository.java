package org.github.barbero.spotippos.model.repository;

import org.github.barbero.spotippos.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Marcos Barbero
 */
public interface PropertyRepository extends JpaRepository<Property, Long> {
    String SQL = "select p.* from property p" +
            "  inner join axis a on a.id = p.axis_id" +
            " where" +
            "   a.x >= :ax" +
            "   and a.y <= :ay" +
            "   and a.x <= :bx" +
            "   and a.y >= :by";

    @Query(value = SQL, nativeQuery = true)
    List<Property> findProperties(@Param("ax") Integer ax, @Param("ay") Integer ay, @Param("bx") Integer bx, @Param("by") Integer by);
}
