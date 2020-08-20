package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnitTestProductEntityFactory extends ProductEntityFactory {

    private static final Map<ProductType, Product> FACTORY_MAP;

    static {
        final HashMap<ProductType, Product> factoryMap = new HashMap<>();
        factoryMap.put(ProductType.GENERIC, createGenericProduct());
        FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
    }

    @Override
    public Product createProduct(ProductType productType) {
        Product product = FACTORY_MAP.get(productType);
        return product;
    }

    private static Product createGenericProduct() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("genericProduct");
        product.setUnitPrice(1.1);
        product.setDescrpition("productDescription");
        return product;
    }
}

