package com.example.product_service.service;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.Repository.ProductRepository;
import com.example.product_service.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ProductResponse> getAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ProductResponse getById(Long id) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return toResponse(p);
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        if (repo.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("Product with name '" + request.name() + "' already exists");
        }
        Product p = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .build();
        return toResponse(repo.save(p));
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.getCreatedAt()
        );
    }
}
