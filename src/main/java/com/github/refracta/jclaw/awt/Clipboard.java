package com.github.refracta.jclaw.awt;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Clipboard {
    public static final Clipboard.TextType HTML = new Clipboard.TextType("text/html");
    public static final Clipboard.TextType PLAIN = new Clipboard.TextType("text/plain");
    public static final Clipboard.Charset UTF8 = new Clipboard.Charset("UTF-8");
    public static final Clipboard.Charset UTF16 = new Clipboard.Charset("UTF-16");
    public static final Clipboard.Charset UNICODE = new Clipboard.Charset("unicode");
    public static final Clipboard.Charset US_ASCII = new Clipboard.Charset("US-ASCII");
    public static final Clipboard.TransferType READER = new Clipboard.TransferType(Reader.class);
    public static final Clipboard.TransferType INPUT_STREAM = new Clipboard.TransferType(InputStream.class);
    public static final Clipboard.TransferType CHAR_BUFFER = new Clipboard.TransferType(CharBuffer.class);
    public static final Clipboard.TransferType BYTE_BUFFER = new Clipboard.TransferType(ByteBuffer.class);

    private Clipboard() {
    }

    public static void clear() {
        getSystemClipboard().setContents(new Transferable() {
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[0];
            }

            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return false;
            }

            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
                throw new UnsupportedFlavorException(flavor);
            }
        }, null);
    }

    public static String getText() {
        Transferable clipboardContents = getSystemClipboard().getContents(Clipboard.class);
        DataFlavor[] flavors = clipboardContents.getTransferDataFlavors();
        if (flavors.length == 0) {
            return null;
        } else {
            DataFlavor textFlavor = DataFlavor.selectBestTextFlavor(flavors);
            if (textFlavor == null) {
                return null;
            } else {
                Reader clipboardReader = null;

                Object var5;
                try {
                    clipboardReader = textFlavor.getReaderForText(clipboardContents);
                    StringBuffer sb = new StringBuffer();
                    char[] cbuf = new char[4096];

                    for (int rcount = clipboardReader.read(cbuf); rcount != -1; rcount = clipboardReader.read(cbuf)) {
                        sb.append(cbuf, 0, rcount);
                    }

                    String var7 = sb.toString();
                    return var7;
                } catch (UnsupportedFlavorException var19) {
                    var5 = null;
                } catch (IOException var20) {
                    var5 = null;
                    return (String) var5;
                } finally {
                    if (clipboardReader != null) {
                        try {
                            clipboardReader.close();
                        } catch (IOException var18) {
                        }
                    }

                }

                return (String) var5;
            }
        }
    }

    public static void putText(CharSequence data) {
        StringSelection copy = new StringSelection(data.toString());
        getSystemClipboard().setContents(copy, copy);
    }

    public static void putText(Clipboard.TextType type, Clipboard.Charset charset, Clipboard.TransferType transferType, CharSequence data) {
        String mimeType = type + "; charset=" + charset + "; class=" + transferType;
        Clipboard.TextTransferable transferable = new Clipboard.TextTransferable(mimeType, data.toString());
        getSystemClipboard().setContents(transferable, transferable);
    }

    public static java.awt.datatransfer.Clipboard getSystemClipboard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public static class TransferType {
        private final Class dataClass;

        private TransferType(Class streamClass) {
            this.dataClass = streamClass;
        }

        public Class getDataClass() {
            return this.dataClass;
        }

        public String toString() {
            return this.dataClass.getName();
        }
    }

    public static class Charset {
        private final String name;

        private Charset(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    public static class TextType {
        private final String type;

        private TextType(String type) {
            this.type = type;
        }

        public String toString() {
            return this.type;
        }
    }

    private static class TextTransferable implements Transferable, ClipboardOwner {
        private final String data;
        private final DataFlavor flavor;

        public TextTransferable(String mimeType, String data) {
            this.flavor = new DataFlavor(mimeType, "Text");
            this.data = data;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{this.flavor, DataFlavor.stringFlavor};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            boolean b = this.flavor.getPrimaryType().equals(flavor.getPrimaryType());
            return b || flavor.equals(DataFlavor.stringFlavor);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (flavor.isRepresentationClassInputStream()) {
                return new StringBufferInputStream(this.data);
            } else if (flavor.isRepresentationClassReader()) {
                return new StringReader(this.data);
            } else if (flavor.isRepresentationClassCharBuffer()) {
                return CharBuffer.wrap(this.data);
            } else if (flavor.isRepresentationClassByteBuffer()) {
                return ByteBuffer.wrap(this.data.getBytes());
            } else if (flavor.equals(DataFlavor.stringFlavor)) {
                return this.data;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }

        public void lostOwnership(java.awt.datatransfer.Clipboard clipboard, Transferable contents) {
        }
    }
}
