package org.github.barbero.spotippos.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
@Entity
@Table(name = Property.TABLE_NAME)
public class Property implements Serializable {
    private static final long serialVersionUID = 1702930317674965549L;

    protected static final String TABLE_NAME = "property";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "axis_id")
    private Axis axis;

    @Column(nullable = false)
    private Integer beds;

    @Column(nullable = false)
    private Integer baths;

    @Column(nullable = false)
    private Integer squareMeters;

}
