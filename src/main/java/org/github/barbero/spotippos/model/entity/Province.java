package org.github.barbero.spotippos.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
@Entity
@Table(name = Province.TABLE_NAME)
public class Province implements Serializable {
    private static final long serialVersionUID = -6720859555250661419L;

    protected static final String TABLE_NAME = "province";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "boundaries_id")
    private Boundaries boundaries;

}
