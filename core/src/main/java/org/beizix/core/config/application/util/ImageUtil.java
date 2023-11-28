package org.beizix.core.config.application.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/** 이미지 crop 과 resize http://egloos.zum.com/kingori/v/4486279 */
public class ImageUtil {

  public static BufferedImage cropImageByMaxWidthAndRatio(
      InputStream inputStream, int maxWidth, double xyRatio) throws IOException {
    BufferedImage originalImg = ImageIO.read(inputStream);

    // get the center point for crop
    int[] centerPoint = {originalImg.getWidth() / 2, originalImg.getHeight() / 2};

    // calculate crop area
    int cropWidth = originalImg.getWidth();
    int cropHeight = originalImg.getHeight();

    if (cropHeight > cropWidth * xyRatio) {
      // long image
      cropHeight = (int) (cropWidth * xyRatio);
    } else {
      // wide image
      cropWidth = (int) ((float) cropHeight / xyRatio);
    }

    // set target image size
    int targetWidth = cropWidth;
    int targetHeight = cropHeight;

    if (targetWidth > maxWidth) {
      // too big image
      targetWidth = maxWidth;
      targetHeight = (int) (targetWidth * xyRatio);
    }

    // processing image
    BufferedImage targetImage =
        new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

    Graphics2D graphics2D = targetImage.createGraphics();
    graphics2D.setBackground(Color.WHITE);
    graphics2D.setPaint(Color.WHITE);
    graphics2D.fillRect(0, 0, targetWidth, targetHeight);
    graphics2D.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2D.drawImage(
        originalImg,
        0,
        0,
        targetWidth,
        targetHeight,
        centerPoint[0] - (cropWidth / 2),
        centerPoint[1] - (cropHeight / 2),
        centerPoint[0] + (cropWidth / 2),
        centerPoint[1] + (cropHeight / 2),
        null);

    return targetImage;
  }
}
