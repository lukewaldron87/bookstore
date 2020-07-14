package com.waldronprojects.bookstore.factory;

import com.waldronprojects.bookstore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitTestProductEntityFactory extends ProductEntityFactory {

    public static final String GENERIC = "GENERIC";

    @Override
    public Product createProduct(String type) {
        Product product = null;
        switch (type){
            case GENERIC:
                product = createGenericProduct();
                break;
            default:
                throw new IllegalArgumentException("Argument " + type + " strange supported");
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

