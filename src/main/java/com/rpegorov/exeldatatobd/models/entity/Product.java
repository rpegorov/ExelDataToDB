package com.rpegorov.exeldatatobd.models.entity;

import com.rpegorov.exeldatatobd.models.dto.DataType;
import com.rpegorov.exeldatatobd.models.dto.ProductType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class describing Entity model for Product
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private DataType dataType;

    private LocalDate date;

    /**
     * Setting relationships between tables, setting a link to Orders in Product
     */
    @ManyToOne()
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

    private Integer amount;

}
