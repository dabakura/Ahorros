package helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Snapshot {
    private static final int PANEL_IMAGE_WIDTH = 3600;/*3508;*/
    private static final int PANEL_IMAGE_HIGH = 2652;/*2480;*/

    public static void CaptureAndSaveDisplay(Parent root, String initialName){
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        fileChooser.setTitle("Guardar Imagen");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.setInitialFileName(initialName+".png");
        FileChooser.ExtensionFilter pdfExtensionFilter =
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(pdfExtensionFilter);
        fileChooser.setSelectedExtensionFilter(pdfExtensionFilter);
        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            try {
                //Pad the capture area
                ImageView image = createScaledView(root);
                //rotate 90 deg
                image.getTransforms().add(new Rotate(90,0,0));
                //snapshot
                WritableImage writableImage = image.snapshot(new SnapshotParameters(),null);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);
                imprimirDirecto(file);
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    private static ImageView createScaledView(Node node) {
        final Bounds bounds = node.getLayoutBounds();
        final double scaleWidth = PANEL_IMAGE_WIDTH / bounds.getWidth();
        final double scaleHigh = PANEL_IMAGE_HIGH / bounds.getHeight();
        final WritableImage image = new WritableImage(
                PANEL_IMAGE_WIDTH,
                PANEL_IMAGE_HIGH
                );
        final SnapshotParameters spa = new SnapshotParameters();
        spa.setTransform(Transform.scale(scaleWidth,scaleHigh ));
        return new ImageView(node.snapshot(spa,null));
    }

    private static void imprimirDirecto(File file) {
        try (FileInputStream archivo = new FileInputStream(file)){
            DocFlavor formato = DocFlavor.INPUT_STREAM.PNG;
            Doc documento = new SimpleDoc(archivo, formato, null);
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

            //----las opciones que me interesan configurar de la impresora
            aset.add(MediaSizeName.NA_LETTER);//el tamaño de la hoja (carta)
            // aset.add(MediaSizeName.ISO_A4);
            aset.add(new Copies(1));
            //aset.add(Chromaticity.MONOCHROME);
            // aset.add(OrientationRequested.PORTRAIT);
            // discover the printers that can print the format according to the
            // instructions in the attribute set
            PrintService[] services = PrintServiceLookup.lookupPrintServices(formato, aset);
            // Create a print job from one of the print services
            if (services.length > 0) {
                DocPrintJob job = services[0].createPrintJob();
                try {
                    job.print(documento, aset);
                } catch (PrintException pe) {}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
