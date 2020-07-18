package com.waldronprojects.bookstore.factory;

import com.waldronprojects.bookstore.entity.Product;

public abstract class ProductEntityFactory {

    public abstract Product createProduct(ProductType productType);
}
