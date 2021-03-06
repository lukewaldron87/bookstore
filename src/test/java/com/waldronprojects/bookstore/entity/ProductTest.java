package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    private Product product;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        fieldModifier = new FieldModifier(product);
    }

    @Test
    public void testConstructorWithoutId_setsAllFields() throws NoSuchFieldException,
                                                                IllegalAccessException {
        String productName = "productName";
        BigDecimal unitPrice = new BigDecimal("1.10");
        String description = "description";
        int unitsInStock = 1;
        Collection<ProductType> productTypeCollection = createProductTypeCollection();
        Collection<Genre> genreCollection = createGenreCollection();
        Collection<Image> imageCollection = createImageCollection();
        Collection<Associate> associateCollection = createAssociateCollection();
        Product product = new Product(productName, unitPrice, description, unitsInStock,
                                      productTypeCollection, genreCollection, imageCollection,
                                      associateCollection);
        fieldModifier = new FieldModifier(product);
        assertEquals(productName, fieldModifier.getFieldValue("productName"));
        assertEquals(unitPrice, fieldModifier.getFieldValue("unitPrice"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
        assertEquals(unitsInStock, fieldModifier.getFieldValue("unitsInStock"));
        assertEquals(productTypeCollection, fieldModifier.getFieldValue("productTypeCollection"));
        assertEquals(genreCollection, fieldModifier.getFieldValue("genreCollection"));
        assertEquals(imageCollection, fieldModifier.getFieldValue("imageCollection"));
        assertEquals(associateCollection, fieldModifier.getFieldValue("associateCollection"));
    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
                                                             IllegalAccessException {
        Long id = 1L;
        String productName = "productName";
        BigDecimal unitPrice = new BigDecimal("1.10");
        String description = "description";
        int unitsInStock = 1;
        Collection<ProductType> productTypeCollection = createProductTypeCollection();
        Collection<Genre> genreCollection = createGenreCollection();
        Collection<Image> imageCollection = createImageCollection();
        Collection<Associate> associateCollection = createAssociateCollection();
        Product product = new Product(id, productName, unitPrice, description, unitsInStock,
                                      productTypeCollection, genreCollection, imageCollection,
                                      associateCollection);
        fieldModifier = new FieldModifier(product);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(productName, fieldModifier.getFieldValue("productName"));
        assertEquals(unitPrice, fieldModifier.getFieldValue("unitPrice"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
        assertEquals(unitsInStock, fieldModifier.getFieldValue("unitsInStock"));
        assertEquals(productTypeCollection, fieldModifier.getFieldValue("productTypeCollection"));
        assertEquals(genreCollection, fieldModifier.getFieldValue("genreCollection"));
        assertEquals(imageCollection, fieldModifier.getFieldValue("imageCollection"));
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        Long fieldValue = 1L;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        Long returnedFieldValue = product.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        Long fieldValue = 1L;
        String fieldName = "id";
        product.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetProductName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "productName";
        String fieldName = "productName";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = product.getProductName();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetProductName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "productName";
        String fieldName = "productName";
        product.setProductName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetUnitPrice() throws NoSuchFieldException, IllegalAccessException {
        BigDecimal fieldValue = new BigDecimal("1.10");
        String fieldName = "unitPrice";
        fieldModifier.setField(fieldName, fieldValue);
        BigDecimal returnedFieldValue = product.getUnitPrice();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetUnitPrice() throws NoSuchFieldException, IllegalAccessException {
        BigDecimal fieldValue = new BigDecimal("1.10");
        String fieldName = "unitPrice";
        product.setUnitPrice(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = product.getDescription();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        product.setDescription(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetUnitsInUnitsInStock() throws NoSuchFieldException, IllegalAccessException{
        int fieldValue = 1;
        String fieldName = "unitsInStock";
        fieldModifier.setField(fieldName, fieldValue);
        int returnedFieldValue = product.getUnitsInStock();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetUnitsInStock() throws NoSuchFieldException, IllegalAccessException{
        int fieldValue = 1;
        String fieldName = "unitsInStock";
        product.setUnitsInStock(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetProductTypeCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<ProductType> fieldValue = createProductTypeCollection();
        String fieldName = "productTypeCollection";
        fieldModifier.setField(fieldName, fieldValue);
        Collection<ProductType> returnedFieldValue = product.getProductTypeCollection();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetProductTypeCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<ProductType> fieldValue = createProductTypeCollection();
        String fieldName = "productTypeCollection";
        product.setProductTypeCollection(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetGenreCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<Genre> fieldValue = createGenreCollection();
        String fieldName = "genreCollection";
        fieldModifier.setField(fieldName, fieldValue);
        Collection<Genre> returnedFieldValue = product.getGenreCollection();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetGenreCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<Genre> fieldValue = createGenreCollection();
        String fieldName = "genreCollection";
        product.setGenreCollection(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetImageCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<Image> fieldValue = createImageCollection();
        String fieldName = "imageCollection";
        fieldModifier.setField(fieldName, fieldValue);
        Collection<Image> returnedFieldValue = product.getImageCollection();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetImageCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<Image> fieldValue = createImageCollection();
        String fieldName = "imageCollection";
        product.setImageCollection(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetAssociateCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<Associate> fieldValue = createAssociateCollection();
        String fieldName = "associateCollection";
        fieldModifier.setField(fieldName, fieldValue);
        Collection<Associate> returnedFieldValue = product.getAssociateCollection();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetAssociateCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<Associate> fieldValue = createAssociateCollection();
        String fieldName = "associateCollection";
        product.setAssociateCollection(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        String productName = "productName";
        BigDecimal unitPrice = new BigDecimal("2.20");
        String description = "description";
        int unitsInStock = 1;
        Collection<ProductType> productTypeCollection = createProductTypeCollection();
        Collection<Genre> genreCollection = createGenreCollection();
        Collection<Image> imageCollection = createImageCollection();
        Collection<Associate> associateCollection = createAssociateCollection();
        Product product = new Product(productName, unitPrice, description, unitsInStock,
                                      productTypeCollection, genreCollection, imageCollection,
                                      associateCollection);
        String productString = product.toString();
        StringBuilder expectedProductStringBuilder = new StringBuilder();
        expectedProductStringBuilder.append("Product [id=null, productName=").append(productName)
                                    .append(", unitPrice=").append(unitPrice)
                                    .append(", description=").append(description)
                                    .append(", unitsInStock=").append(unitsInStock)
                                    .append(", productTypeCollection=").append(productTypeCollection.toString())
                                    .append(", genreCollection=").append(genreCollection.toString())
                                    .append(", imageCollection=").append(imageCollection.toString())
                                    .append(", associateCollection=").append(associateCollection.toString())
                                    .append("]");
        assertEquals(expectedProductStringBuilder.toString(), productString);
    }

    private Collection<ProductType> createProductTypeCollection(){
        ProductType bookProductType1 = new ProductType(1L, "book",
                "A book");
        ProductType bookProductType2 = new ProductType(2L, "hardback book",
                "A book bound with rigid protective cover");
        Collection<ProductType> productTypeCollection = new ArrayList<>();
        productTypeCollection.add(bookProductType1);
        productTypeCollection.add(bookProductType2);
        return productTypeCollection;
    }

    private Collection<Genre> createGenreCollection() {
        Genre crimeGenre = new Genre(1L, "Crime", "Cops and robbers craic");
        Genre thrillerGenre = new Genre(2L, "Thriller", "Exciting stuff");
        Collection<Genre> genreCollection = new ArrayList<>();
        genreCollection.add(crimeGenre);
        genreCollection.add(thrillerGenre);
        return genreCollection;
    }

    private Collection<Image> createImageCollection() {
        Image image1 = new Image(1L, "/here/here");
        Image image2 = new Image(2L, "/there/there");
        Collection imageCollection = new ArrayList();
        imageCollection.add(image1);
        imageCollection.add(image2);
        return imageCollection;
    }

    private Collection<Associate> createAssociateCollection() {
        Associate associate1 = new Associate(1L, "Oscar Wild",
                                        "An Irish poet and playwright.");
        Associate associate2 = new Associate(2L, "James Joyce",
                                        "was an Irish novelist, short story writer,"
                                                + " poet, teacher, and literary critic.");
        Collection associateCollection = new ArrayList();
        associateCollection.add(associate1);
        associateCollection.add(associate2);
        return associateCollection;
    }
}