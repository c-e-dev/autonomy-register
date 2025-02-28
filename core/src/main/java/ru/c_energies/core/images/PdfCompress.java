package ru.c_energies.core.images;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.DocumentException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PdfCompress implements Compress{
    private final ByteArrayInputStream bais;
    private final String extension;
    private int size;
    private final float FACTOR = 1.00f;
    public PdfCompress(ByteArrayInputStream bais, String extension){
        this.bais = bais;
        this.extension = extension;
    }
    @Override
    public byte[] start() throws IOException, DocumentException {
        //Get source pdf
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(this.bais), new PdfWriter("compressPdf.pdf"));

        // Iterate over all pages to get all images.
        for (int i=1; i<=pdfDoc.getNumberOfPages(); i++)
        {
            PdfPage page = pdfDoc.getPage(i);
            PdfDictionary pageDict = page.getPdfObject();
            PdfDictionary resources = pageDict.getAsDictionary(PdfName.Resources);
            // Get images
            PdfDictionary xObjects = resources.getAsDictionary(PdfName.XObject);
            for (Iterator<PdfName> iter = xObjects.keySet().iterator(); iter.hasNext(); ) {
                // Get image
                PdfName imgRef = iter.next();
                PdfStream stream = xObjects.getAsStream(imgRef);
                PdfImageXObject image = new PdfImageXObject(stream);
                BufferedImage bi = image.getBufferedImage();
                if (bi == null){ continue; }

                // Create new image
                int width = (int) (bi.getWidth() * FACTOR);
                int height = (int) (bi.getHeight() * FACTOR);
                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
                Graphics2D g = img.createGraphics();
                g.drawRenderedImage(bi, at);
                ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();

                // Write new image
                ImageIO.write(img, "JPG", imgBytes);
                Image imgNew =new Image(ImageDataFactory.create(imgBytes.toByteArray()));

                // Replace the original image with the resized image
                xObjects.put(imgRef, imgNew.getXObject().getPdfObject());
            }
        }
        pdfDoc.close();
        // Save altered PDF
        Path pdfPath = Paths.get("compressPdf.pdf");
        byte[] bytes = Files.readAllBytes(pdfPath);
        this.size = bytes.length;
        return bytes;
    }

    @Override
    public int size() {
        return this.size;
    }
}
