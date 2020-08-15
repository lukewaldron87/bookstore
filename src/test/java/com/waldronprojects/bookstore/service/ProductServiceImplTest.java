package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dao.ProductDao;
import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.entity.factory.ProductEntityFactory;
import com.waldronprojects.bookstore.entity.factory.ProductType;
import com.waldronprojects.bookstore.entity.factory.UnitTestProductEntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testGetProduct() {
        Product product = createProductObject(ProductType.GENERIC);
        Mockito.when(productDao.getProduct(product.getId())).thenReturn(product);
        Product returnedProduct = productService.getProduct(product.getId());
        assertEquals(product, returnedProduct);
    }

    @Test
    public void testGetProducts() {
        List<Product> productList =
                createProductList(ProductType.GENERIC);
        Mockito.when(productDao.getProducts()).thenReturn(productList);
        List<Product> returnedProductList = productService.getProducts();
        assertEquals(productList, returnedProductList);
        Mockito.verify(productDao,Mockito.times(1)).getProducts();
    }

    private List<Product> createProductList(ProductType type){
        Product product = createProductObject(ProductType.GENERIC);
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        productList.add(product);
        return productList;
    }

    @Test
    public void testSaveProduct() {
        Product product =
                createProductObject(ProductType.GENERIC);
        productService.saveProduct(product);
        ArgumentCaptor<Product>  argumentCaptor = ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productDao, Mockito.times(1))
                .saveProduct(argumentCaptor.capture());
        Product capturedProduct = argumentCaptor.getValue();
        assertEquals(product, capturedProduct);
    }

    @Test
    public void testDeleteProduct() {
        Integer id = 1;
        productService.deleteProduct(id);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(productDao, Mockito.times(1))
                .deleteProduct(argumentCaptor.capture());
        Integer capturedInt = argumentCaptor.getValue();
        assertEquals(id, capturedInt);
    }

    private Product createProductObject(ProductType type){
        ProductEntityFactory productEntityFactory = new UnitTestProductEntityFactory();
        return productEntityFactory.createProduct(type);
    }
}