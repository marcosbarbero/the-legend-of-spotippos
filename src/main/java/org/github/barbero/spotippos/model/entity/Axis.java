package org.github.barbero.spotippos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Axis.TABLE_NAME)
public class Axis implements Serializable {
    private static final long serialVersionUID = -3364480867236820750L;

    protected static final String TABLE_NAME = "axis";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer x;

    @Column(nullable = false)
    private Integer y;
}
