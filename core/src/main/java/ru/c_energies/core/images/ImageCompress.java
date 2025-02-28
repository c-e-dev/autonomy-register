package ru.c_energies.core.images;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Iterator;

public class ImageCompress implements Compress {
    private final ByteArrayInputStream bais;
    private final String extension;
    private int size;
    public ImageCompress(ByteArrayInputStream bais, String extension){
        this.bais = bais;
        this.extension = extension;
    }
    public byte[] start() throws IOException {
        BufferedImage image = ImageIO.read(this.bais);
        File compressedImageFile = new File("compressed_image." + this.extension);
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(this.extension);
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.25f);  // Change the quality value you prefer
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
        byte[] bytes = Files.readAllBytes(compressedImageFile.toPath());
        this.size = bytes.length;
        return bytes;
    }

    public int size(){
        return this.size;
    }
}
