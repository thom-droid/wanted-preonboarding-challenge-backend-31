package org.example.cqrs.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "weight", precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(name = "dimensions")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> dimensions;

    @Column(name = "materials", length = Integer.MAX_VALUE)
    private String materials;

    @Size(max = 100)
    @Column(name = "country_of_origin", length = 100)
    private String countryOfOrigin;

    @Column(name = "warranty_info", length = Integer.MAX_VALUE)
    private String warrantyInfo;

    @Column(name = "care_instructions", length = Integer.MAX_VALUE)
    private String careInstructions;

    @Column(name = "additional_info")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> additionalInfo;

}