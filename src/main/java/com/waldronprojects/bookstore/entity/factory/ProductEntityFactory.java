package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Product;

public abstract class ProductEntityFactory {

    public abstract Product createProduct(ProductTypeEnum productTypeEnum);
}
