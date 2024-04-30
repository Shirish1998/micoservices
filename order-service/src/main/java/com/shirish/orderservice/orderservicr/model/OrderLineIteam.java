package com.shirish.orderservice.orderservicr.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_line_iteam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineIteam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

}
