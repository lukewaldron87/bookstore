package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.AssociateType;

import java.util.Collection;

public abstract class AssociateTypeCollectionFactory {

    public abstract Collection<AssociateType> createAssociateTypeCollection();
}
