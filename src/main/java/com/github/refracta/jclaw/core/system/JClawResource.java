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
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }
    }

    public static Mat getMatFromPath(String path) {
        try {
            return getMatFromBytes(IOUtils.toByteArray(JClawResource.class.getClassLoader().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Mat getMatFromBytes(byte[] bytes) {
        return Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);
    }

    public static void copyFromPath(String src, String dest) {
        try {
            IOUtils.copy(JClawResource.class.getClassLoader().getResourceAsStream(src), new FileOutputStream(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
