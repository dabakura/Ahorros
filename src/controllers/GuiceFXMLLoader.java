package controllers;

import com.google.inject.Injector;
import java.io.InputStream;
import javafx.fxml.FXMLLoader;
import javax.inject.Inject;

class GuiceFXMLLoader {
    private final Injector injector;

    @Inject
    GuiceFXMLLoader(Injector injector) {
        this.injector = injector;
    }

    // Load some FXML file, using the supplied Controller, and return the
    // instance of the initialized controller...?
    Object load(String url, Class<?> controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(injector::getInstance);
        InputStream in = null;
        try {
            in = controller.getResourceAsStream(url);
            return loader.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) try { in.close(); } catch (Exception ee) { ee.printStackTrace(); }
        }
        return null;
    }
}