package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.AssociateType;
import com.waldronprojects.bookstore.util.UnitTestAssociateTypeCollectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTestAssociateTypeCollectionFactoryTest {

    AssociateTypeCollectionFactory associateTypeCollectionFactory;

    @Before
    public void setUp(){
        associateTypeCollectionFactory = new UnitTestAssociateTypeCollectionFactory();
    }

    @Test
    public void testCreateAssociateTypeCollection_returnsCollectionOfAssociateType(){
        Collection<AssociateType> associateTypeCollection =
                associateTypeCollectionFactory.createAssociateTypeCollection();
        for(AssociateType associateType: associateTypeCollection){
            assertTrue(associateType instanceof AssociateType);
        }
    }

    @Test
    public void testCreateAssociateTypeCollection_returnsCorrectStaticAssociateTypes(){
        Collection<AssociateType> associateTypeCollection =
                associateTypeCollectionFactory.createAssociateTypeCollection();
        assertEquals("AssociateType { id='1', name='Author', description='A person who writes a book'}",
                associateTypeCollection.toArray()[0].toString());
        assertEquals("AssociateType { id='2', name='Illustrator', description='A person who draws pictures for books'}",
                associateTypeCollection.toArray()[1].toString());
    }
}
