package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Product;

public class UnitTestProductEntityFactory extends ProductEntityFactory {

    @Override
    public Product createProduct(ProductType productType) {
        Product product = null;
        switch (productType){
            case GENERIC:
                product = createGenericProduct();
                break;
            default:
                throw new IllegalArgumentException("Argument " + productType + " strange supported");
        }
        return product;
    }

    private Product createGenericProduct() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("genericProduct");
        product.setUnitPrice(1.1);
        product.setDescrpition("productDescription");
        return product;
    }
}

