package org.springmvc.dto;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che3.util.SafeClose;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Iterator;

public class Dcm2jpgIOStreamOutput {

    private final ImageReader imageReader = ImageIO.getImageReadersByFormatName("DICOM").next();
    private float windowCenter;
    private float windowWidth;
    private boolean autoWindowing = true;
    private int windowIndex;
    private int voiLUTIndex;
    private boolean preferWindow = true;
    private Attributes prState;
    private int overlayActivationMask = 0xffff;
    private int overlayGrayscaleValue = 0xffff;
    private int frame = 1;

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void setWindowCenter(float windowCenter) {
        this.windowCenter = windowCenter;
    }

    public void setWindowWidth(float windowWidth) {
        this.windowWidth = windowWidth;
    }

    public void setAutoWindowing(boolean autoWindowing) {
        this.autoWindowing = autoWindowing;
    }

    public void setWindowIndex(int windowIndex) {
        this.windowIndex = windowIndex;
    }

    public void setVoiLUTIndex(int voiLUTIndex) {
        this.voiLUTIndex = voiLUTIndex;
    }

    public void setPreferWindow(boolean preferWindow) {
        this.preferWindow = preferWindow;
    }

    public void setPrState(Attributes prState) {
        this.prState = prState;
    }

    public void setOverlayActivationMask(int overlayActivationMask) {
        this.overlayActivationMask = overlayActivationMask;
    }

    public void setOverlayGrayscaleValue(int overlayGrayscaleValue) {
        this.overlayGrayscaleValue = overlayGrayscaleValue;
    }

    //, float windowWidth, float windowCenter
    public String convert(File src) throws Exception {
//		this.windowCenter = windowCenter;
//		this.windowWidth = windowWidth;

        //定义一个二进制数组
        byte[] data = null;
        Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
        ImageReader reader = iter.next();
        ImageInputStream iis = ImageIO.createImageInputStream(src);
        BufferedImage bi;
        ByteArrayOutputStream out = null;

        try {
            reader.setInput(iis, false);
            bi = readImage(iis);
            if (bi == null) {
                System.out.println("\nError: " + src + " - couldn't read!");
                return null;
            }
//        	System.out.println("width: " + bi.getWidth());
//        	System.out.println("height: " + bi.getHeight());
//        	System.out.println("type: " + bi.getType());
//            response.setContentType("image/*");
//            out = response.getOutputStream();

            out = new ByteArrayOutputStream();
            ImageIO.write(bi,"bmp", out);
            out.close();
        } finally {
            SafeClose.close(iis);
            SafeClose.close(out);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        return "data:image/bmp;base64,"+encoder.encode(out.toByteArray()).replaceAll("\n|\r","");

//      return "data:image/bmp;base64,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";
    }

    private ImageReadParam readParam() throws Exception {
        DicomImageReadParam param = (DicomImageReadParam) imageReader.getDefaultReadParam();
        param.setWindowCenter(windowCenter);
        param.setWindowWidth(windowWidth);
        param.setAutoWindowing(autoWindowing);
        param.setWindowIndex(windowIndex);
        param.setVOILUTIndex(voiLUTIndex);
        param.setPreferWindow(preferWindow);
        param.setPresentationState(prState);
        param.setOverlayActivationMask(overlayActivationMask);
        param.setOverlayGrayscaleValue(overlayGrayscaleValue);
        return param;
    }

    private BufferedImage readImage(ImageInputStream iis) throws Exception {
        imageReader.setInput(iis);
        return imageReader.read(frame - 1, readParam());
    }

}
