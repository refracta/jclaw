import com.github.refracta.jclaw.awt.env.Key;
import com.github.refracta.jclaw.core.JClaw;
import com.github.refracta.jclaw.core.system.*;
import com.github.refracta.jclaw.searcher.ScreenSearcher;
import com.github.refracta.jclaw.searcher.factory.ScreenSearcherFactory;
import com.github.refracta.jclaw.ui.OverlayFrame;
import com.github.refracta.jclaw.util.OpenCVUtil;
import com.github.refracta.jclaw.util.RectangleUtil;
import com.sun.jna.platform.WindowUtils;
import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class JSPaint {
    public static void main(String[] args) throws IOException {
        JClaw.init();

        new ProcessBuilder("cmd.exe", "/c", "start chrome /incognito jspaint.app").start();
        JClawDelay.delay(7);

        JClawKeyboard.keyDown(KeyEvent.VK_WINDOWS);
        JClawKeyboard.keyDownAndUp(Key.UP);
        JClawKeyboard.keyUp(KeyEvent.VK_WINDOWS);
        JClawDelay.delay(1);

        Rectangle screenRectangle = JClawUtil.getUnionScreenRectangle();
        Mat capture = JClawScan.capture(screenRectangle);
        // OpenCVUtil.saveMatImage(capture, "capture.png");

        Mat select = JClawResource.getMatFromPath("Select.png");
        ScreenSearcher rgbScreenSearcher = ScreenSearcherFactory.getRGBScreenSearcher();
        Rectangle selectRectangle = rgbScreenSearcher.search(capture, select, -1).get(0);
        Point drawCenter = new Point(selectRectangle.x + selectRectangle.width + 130, selectRectangle.y + selectRectangle.height + 50);

        JClawMouse.move(screenRectangle, drawCenter);
        JClawMouse.press();
        double r = 50;
        for (int i = 0; i < 360 * 3; i++) {
            double angle = (i / 180.0) * Math.PI;
            r -= 0.04;
            JClawMouse.move(screenRectangle, new Point((int) (drawCenter.x - 50 + r * Math.cos(angle)), (int) (drawCenter.y + r * Math.sin(angle))));
            JClawDelay.delay(0.001);
        }
        JClawMouse.release();

        int selectWidth = select.width();
        JClawClipboard.putImage(OpenCVUtil.mat2BufferedImage(select));
        for (int i = 0; i < 5; i++) {
            JClawKeyboard.keyDown(Key.CTRL);
            JClawKeyboard.keyDownAndUp("v");
            JClawKeyboard.keyUp(Key.CTRL);
            JClawDelay.delay(0.01);
            for (int j = 0; j < (selectWidth + 2) * (i + 1); j++) {
                JClawKeyboard.keyDown(Key.RIGHT);
                JClawDelay.delay(0.01);
                JClawKeyboard.keyUp(Key.RIGHT);
                JClawDelay.delay(0.01);
            }
        }
        JClawKeyboard.keyDownAndUp(Key.ENTER);

        capture = JClawScan.capture(screenRectangle);
        List<Rectangle> drawToolRectangles = JClawScan.scanParallelMultiple(capture, new Mat[]{JClawResource.getMatFromPath("Airbrush.png"), JClawResource.getMatFromPath("Brush.png"), JClawResource.getMatFromPath("Eraser.png"), JClawResource.getMatFromPath("Fill.png"), JClawResource.getMatFromPath("Pencil.png")}, rgbScreenSearcher);

        Rectangle overlayStart = rgbScreenSearcher.search(capture, JClawResource.getMatFromPath("FreeSelect.png"), 1).get(0);
        OverlayFrame frame = new OverlayFrame();
        frame.getContentPane().add(new JComponent() {
            protected void paintComponent(Graphics g) {
                g.setColor(Color.GREEN);
                for (Rectangle r : drawToolRectangles) {
                    g.fillRect(r.x - overlayStart.x, r.y - overlayStart.y, r.width, r.height);
                }
            }

            public Dimension getPreferredSize() {
                return new Dimension(50, 125);
            }
        });
        frame.setLocation(screenRectangle.x + overlayStart.x, screenRectangle.y + overlayStart.y);
        frame.pack();
        frame.setVisible(true);
        WindowUtils.setWindowAlpha(frame, 0.5f);

        List<Point> drawTools = RectangleUtil.getCenterPointList(drawToolRectangles);
        List<Rectangle> searchList = rgbScreenSearcher.search(capture, select, -1);
        for (int i = 0; i < 5; i++) {
            Rectangle target = searchList.get(i);
            JClawMouse.move(screenRectangle, drawTools.get(i));
            JClawMouse.click();
            JClawMouse.move(screenRectangle, RectangleUtil.getCenterPoint(target));
            JClawMouse.click();
        }
        JClawDelay.delay(0.01);

        Rectangle magnifier = ScreenSearcherFactory.getRGBAScreenSearcher().search(capture, JClawResource.getMatFromPath("Magnifier.png"), 1).get(0);
        JClawMouse.move(screenRectangle, RectangleUtil.getCenterPoint(magnifier));
        JClawMouse.click();
        JClawMouse.move(screenRectangle, drawCenter);
        JClawMouse.click();

        JClawKeyboard.keyDown(Key.ALT);
        JClawKeyboard.keyDownAndUp("d");
        JClawKeyboard.keyUp(Key.ALT);
        JClawDelay.delay(0.1);
        JClawKeyboard.type("[JClaw] Hello World!");

        JClawDelay.delay(3);
        System.exit(0);
    }
}
