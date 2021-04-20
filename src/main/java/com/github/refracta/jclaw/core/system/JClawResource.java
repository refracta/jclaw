package com.github.refracta.jclaw.core.system;

import org.apache.commons.io.IOUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class JClawResource {
    public static File getFileFromURL(String path) {
        URL url = JClawResource.class.getClassLoader().getResource(path);
        System.out.println(path);
        System.out.println(url);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }
    }

    public static Mat getImageFromPath(String path) {
        try {
            return Imgcodecs.imdecode(new MatOfByte(IOUtils.toByteArray(JClawResource.class.getClassLoader().getResourceAsStream(path))), Imgcodecs.IMREAD_UNCHANGED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyFromPath(String src, String dest) {
        try {
            IOUtils.copy(JClawResource.class.getClassLoader().getResourceAsStream(src), new FileOutputStream(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
