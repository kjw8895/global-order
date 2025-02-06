package com.global.order.core.utils;

import com.global.order.core.annotation.CustomTsid;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class CustomTsidGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        CustomTsid.FactorySupplier factorySupplier = CustomTsid.FactorySupplier.INSTANCE;
        return factorySupplier.get().generate().toLong();
    }
}
