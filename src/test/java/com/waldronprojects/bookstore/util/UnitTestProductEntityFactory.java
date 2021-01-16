package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.entity.factory.ProductEntityFactory;
import com.waldronprojects.bookstore.entity.factory.ProductTypeEnum;

import java.math.BigDecimal;
import java.util.*;

public class UnitTestProductEntityFactory extends ProductEntityFactory {

    private final Map<ProductTypeEnum, Product> FACTORY_MAP;

    public UnitTestProductEntityFactory() {
        final Map<ProductTypeEnum, Product> factoryMap = new EnumMap<>(ProductTypeEnum.class);
        factoryMap.put(ProductTypeEnum.GENERIC, createGenericProduct());
        FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
    }

    @Override
    public Product createProduct(ProductTypeEnum productTypeEnum) {
        return FACTORY_MAP.get(productTypeEnum);
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

