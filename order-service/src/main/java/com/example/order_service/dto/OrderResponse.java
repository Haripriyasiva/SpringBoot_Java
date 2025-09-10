package com.example.order_service.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String status;

    // Format the date in API responses
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    // Utility for readable logging
    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + (id != null ? id : "null") +
                ", customerId=" + (customerId != null ? customerId : "null") +
                ", productId=" + (productId != null ? productId : "null") +
                ", quantity=" + (quantity != null ? quantity : "null") +
                ", price=" + (price != null ? price : "null") +
                ", status='" + (status != null ? status : "null") + '\'' +
                ", orderDate=" + (orderDate != null ? orderDate.toString() : "null") +
                '}';
    }
}
