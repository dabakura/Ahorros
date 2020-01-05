package helpers;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import models.Storage;

/**
 *
 * @author david
 */
public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(Storage.class).in(Singleton.class);
        bind(IOFileSerializable.class);
    }
}