package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ProductControllerTest {

    private MockMvc mockMvc;
    private static final String URl_MAPPING_PREFIX = "/employee/product/";
    private static final String VIEW_PATH_PREFIX = "/WEB-INF/view/";

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(VIEW_PATH_PREFIX);
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testListProducts() throws Exception {
        List<Product> productList = createProductList();
        when(productService.getProducts()).thenReturn(productList);
        mockMvc.perform(get(URl_MAPPING_PREFIX + "list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/list-products"))
                .andExpect(forwardedUrl(VIEW_PATH_PREFIX + "employee/list-products.jsp"))
                .andExpect(model().attribute("products", hasSize(2)))
                .andExpect(model().attribute("products", hasItem(productList.get(0))))
                .andExpect(model().attribute("products", hasItem(productList.get(1))));
        verify(productService, times(1)).getProducts();
        verifyNoMoreInteractions(productService);
    }

    private List<Product> createProductList() {
        Product product1 = createProductWithId(1);
        Product product2 = createProductWithId(1);
        return Arrays.asList(product1, product2);
    }

    @Test
    public void testShowFormForAdd() throws Exception {
        mockMvc.perform(get(URl_MAPPING_PREFIX + "showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/product-form"))
                .andExpect(forwardedUrl(VIEW_PATH_PREFIX + "employee/product-form.jsp"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveProduct() throws Exception {
        Product product = createProductWithId(1);
        mockMvc.perform(post(URl_MAPPING_PREFIX + "saveProduct")
                        .flashAttr("product", product))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:list"))
                .andExpect(redirectedUrl("list"));
        verify(productService, times(1)).saveProduct(product);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testShowFormForUpdate() throws Exception {
        Product product = createProductWithId(1);
        when(productService.getProduct(product.getId()))
                .thenReturn(product);
        mockMvc.perform(get(URl_MAPPING_PREFIX + "showFormForUpdate")
                        .param("productId", String.valueOf(product.getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/product-form"))
                .andExpect(forwardedUrl(VIEW_PATH_PREFIX + "employee/product-form.jsp"))
                .andExpect(model().attribute("product", product));
        verify(productService, times(1)).getProduct(product.getId());
        verifyNoMoreInteractions(productService);
    }

    private Product createProductWithId(int id) {
        return new Product(id,
                "productName"+id,
                id,
                "productDescription"+id,
                id);
    }

    @Test
    public void testDelete() throws Exception {
        int productId = 1;
        mockMvc.perform(get(URl_MAPPING_PREFIX + "delete")
                        .param("productId", String.valueOf(productId)))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:list"))
                .andExpect(redirectedUrl("list?productId=" + productId))
                .andExpect(model().attribute("productId", productId));
        verify(productService, times(1)).deleteProduct(productId);
        verifyNoMoreInteractions(productService);
    }
}