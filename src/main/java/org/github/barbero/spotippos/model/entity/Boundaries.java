package org.github.barbero.spotippos.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
@Entity
@Table(name = Boundaries.TABLE_NAME)
public class Boundaries implements Serializable {
    private static final long serialVersionUID = 4337976196559538552L;

    protected static final String TABLE_NAME = "boundaries";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "upper_left_id")
    private Axis upperLeft;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bottom_right_id")
    private Axis bottomRight;

}
