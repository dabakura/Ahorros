package controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import helpers.IOFileSerializable;

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