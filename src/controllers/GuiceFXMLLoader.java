package controllers;

import com.google.inject.Injector;
import java.io.InputStream;
import javafx.fxml.FXMLLoader;
import javax.inject.Inject;

class GuiceFXMLLoader {
    private final Injector injector;
    private FXMLLoader loader;

    FXMLLoader getLoader() { return this.loader; }

    @Inject
    GuiceFXMLLoader(Injector injector) {
        this.injector = injector;
    }

    // Load some FXML file, using the supplied Controller, and return the
    // instance of the initialized controller...?
    Object load(String url, Class<?> controller) {
        this.loader = new FXMLLoader();
        this.loader.setControllerFactory(injector::getInstance);
        InputStream in = null;
        try {
            in = controller.getResourceAsStream(url);
            return this.loader.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) try { in.close(); } catch (Exception ee) { ee.printStackTrace(); }
        }
        return null;
    }
}