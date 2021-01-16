package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.entity.factory.ProductEntityFactory;
import com.waldronprojects.bookstore.entity.factory.ProductType;

import java.math.BigDecimal;
import java.util.*;

public class UnitTestProductEntityFactory extends ProductEntityFactory {

    private final Map<ProductType, Product> FACTORY_MAP;

    public UnitTestProductEntityFactory() {
        final Map<ProductType, Product> factoryMap = new EnumMap<>(ProductType.class);
        factoryMap.put(ProductType.GENERIC, createGenericProduct());
        FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
    }

    @Override
    public Product createProduct(ProductType productType) {
        return FACTORY_MAP.get(productType);
    }

    private Product createGenericProduct() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("genericProduct");
        product.setUnitPrice(new BigDecimal("1.10"));
        product.setDescription("productDescription");
        product.setUnitsInStock(1);
        return product;
    }
}

