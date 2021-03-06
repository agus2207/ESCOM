///Paquete de Trabajo
package com.SAEAAV.Experimental;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class AltPrintScreen 
{
    private Rectangle boundsPanel;

    public void Principal() {
        try {
//            JFrame frame = new JFrame();
//            frame.setSize(200, 200);
//            frame.setVisible(true);
            
            try
            {
                Thread.sleep(1000 * 2);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            
            Robot robot = new Robot();
            
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_PRINTSCREEN);
            robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
            robot.keyRelease(KeyEvent.VK_ALT);
            
            try
            {
                Thread.sleep(1000 * 2);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            
            Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            RenderedImage image = (RenderedImage) t.getTransferData(DataFlavor.imageFlavor);
            
            BufferedImage pg1 = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics2D createGraphics = pg1.createGraphics();
            createGraphics.drawRenderedImage(image, null);
            //createGraphics.drawImage(image, this.boundsPanel.getX(), this.boundsPanel.getY(), this.boundsPanel.getWidth(), this.boundsPanel.getHeight(), null);
            System.out.println("X="+this.boundsPanel.getX()+"Y="+this.boundsPanel.getY()+"W="+this.boundsPanel.getWidth()+"H="+this.boundsPanel.getHeight());
            BufferedImage subimage = pg1.getSubimage((int)(this.boundsPanel.getX()*2.0), (int)(this.boundsPanel.getY()*2.0), (int)(this.boundsPanel.getWidth()-this.boundsPanel.getX()*1.0), (int)(this.boundsPanel.getHeight()-this.boundsPanel.getY()*1));
            image     = (RenderedImage)subimage;
            
            
            boolean isSuccess = ImageIO.write(image, "png", new File("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\RECURSOS\\altScreenChoco.png"));
            System.out.println(isSuccess);
            
            
            
            
            //System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(AltPrintScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage convertRenderedImage(RenderedImage img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage)img;  
        }   
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        if (keys!=null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
        img.copyData(raster);
        return result;
    }
    public void setBoundsPanel(Rectangle boundsPanel) {
        this.boundsPanel = boundsPanel;
    }
}