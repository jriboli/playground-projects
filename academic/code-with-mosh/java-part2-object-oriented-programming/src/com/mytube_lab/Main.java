package com.mytube_lab;

public class Main {

    public static void main(String[] args) {
        var video = new Video();
        video.setFileName("birthday.mp4");
        video.setTitle("Jennifer's birthday");
        video.setUser(new User("john@domain.com"));

        VideoEncoder encoder = new VideoEncoder();
        VideoDatabase database = new VideoDatabase();
        EmailService message = new EmailService();

        var processor = new VideoProcessor(encoder,database,message);
        processor.process(video);
    }
}
