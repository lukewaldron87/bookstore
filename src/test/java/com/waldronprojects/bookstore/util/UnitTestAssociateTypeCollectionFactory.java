package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.AssociateType;
import com.waldronprojects.bookstore.entity.factory.AssociateTypeCollectionFactory;

import java.util.ArrayList;
import java.util.Collection;

public class UnitTestAssociateTypeCollectionFactory extends AssociateTypeCollectionFactory {

    @Override
    public Collection<AssociateType> createAssociateTypeCollection() {
        AssociateType associateType1 = new AssociateType(1L, "Author",
                "A person who writes a book");
        AssociateType associateType2 = new AssociateType(2L, "Illustrator",
                "A person who draws pictures for books");
        Collection<AssociateType> associateTypeCollection = new ArrayList<>();
        associateTypeCollection.add(associateType1);
        associateTypeCollection.add(associateType2);
        return associateTypeCollection;
    }
}
