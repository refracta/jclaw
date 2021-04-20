package com.github.refracta.jclaw.util;

import org.apache.commons.io.FilenameUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class OpenCVUtil {
    public static Mat bufferedImage2Mat(BufferedImage bufferedImage) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "bmp", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            Mat loadedImage = Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
            Mat convertedImage = new Mat();
            Imgproc.cvtColor(loadedImage, convertedImage, Imgproc.COLOR_BGR2BGRA);
            return convertedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage mat2BufferedImage(Mat matrix) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".bmp", matrix, mob);
        byte[] ba = mob.toArray();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(ba));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    public static void saveMatImage(Mat image, String path) {
        BufferedImage bufferedImage = mat2BufferedImage(image);
        File file = new File(path);
        try {
            ImageIO.write(bufferedImage, FilenameUtils.getExtension(path), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
