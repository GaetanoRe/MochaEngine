package com.util;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import java.io.*;
import java.awt.image.*;
import java.nio.*;
import javax.imageio.*;

public class Texture {
    private int id;
    private int width;
    private int height;
    private int zIndex;

    public Texture(String filename, int zIndex){
        BufferedImage bi;
        try{
            bi = ImageIO.read(new File(filename));
            this.width = bi.getWidth();
            this.height = bi.getHeight();
            this.zIndex = zIndex;

            int [] pixelsRaw = new int [width * height * 4];
            pixelsRaw = bi.getRGB(0,0, width, height, null, 0, width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
            for(int i = 0; i < width * height; i++){
                int pixel = pixelsRaw[i];
                pixels.put((byte) ((pixel >> 16) & 0xFF)); // RED
                pixels.put((byte) ((pixel >> 8) & 0xFF)); // GREEN
                pixels.put((byte) (pixel & 0xFF)); // BLUE
                pixels.put((byte) ((pixel >> 24) & 0xFF)); // ALPHA
            }
            pixels.flip();

            this.id = glGenTextures();

            glBindTexture(GL_TEXTURE_2D,id);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

        }catch(IOException ie){
            System.out.println("Cannot find file");
        }


    }

    public void setzIndex(int z){
        zIndex = z;
    }

    public int getzIndex(){
        return zIndex;
    }

    public void bind(int sampler){
        if(sampler >= 0 && sampler <= 31){
            glActiveTexture(GL_TEXTURE0 + sampler);
            glBindTexture(GL_TEXTURE_2D, id);
        }

    }

    public void unbind(){glBindTexture(GL_TEXTURE_2D, 0);}
}
