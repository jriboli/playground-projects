package com.mytube_lab;

public class VideoEncoder implements Encodable{
    @Override
    public void encode(Video video) {
        System.out.println("Encoding video...");
        System.out.println("Done!\n");
    }
}
