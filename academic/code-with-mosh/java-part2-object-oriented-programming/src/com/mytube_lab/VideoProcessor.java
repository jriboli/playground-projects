package com.mytube_lab;

public class VideoProcessor {
    private Encodable encodable;
    private Storable storable;
    private Notifiable notifiable;
    public VideoProcessor(Encodable encodable, Storable storable, Notifiable notifiable) {
        this.encodable = encodable;
        this.storable = storable;
        this.notifiable = notifiable;
    }
    public void process(Video video) {
        encodable.encode(video);
        storable.store(video);
        notifiable.sendMessage(video.getUser());
    }
}

