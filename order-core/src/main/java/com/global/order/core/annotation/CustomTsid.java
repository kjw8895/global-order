package com.global.order.core.annotation;

import com.global.order.core.utils.CustomTsidGenerator;
import io.hypersistence.tsid.TSID;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Clock;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@IdGeneratorType(CustomTsidGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CustomTsid {
    Class<? extends Supplier<TSID.Factory>> value() default CustomTsid.FactorySupplier.class;

    public static class FactorySupplier implements Supplier<TSID.Factory> {
        private TSID.Factory tsidFactory;
        public static final FactorySupplier INSTANCE = new FactorySupplier();

        private FactorySupplier() {
            this.tsidFactory = TSID.Factory.builder()
                    .withNodeBits(System.getenv("ENV_NODE_BITS") == null ? 10 : Integer.parseInt(System.getenv("ENV_NODE_BITS")))
                    .withClock(Clock.system(System.getenv("ENV_TZ") == null ? ZoneId.systemDefault() : ZoneId.of(System.getenv("ENV_TZ"))))
                    .withRandomFunction(() -> ThreadLocalRandom.current().nextInt())
                    .build();
        }

        public synchronized Long generate() {
            return this.tsidFactory.generate().toLong();
        }

        public TSID.Factory get() {
            return this.tsidFactory;
        }
    }
}
