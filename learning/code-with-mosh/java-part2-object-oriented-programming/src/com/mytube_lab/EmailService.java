package com.mytube_lab;

public class EmailService implements Notifiable{
    @Override
    public void sendMessage(User user) {
        System.out.println("Notifying " + user.getEmail() + "...");
        System.out.println("Done!\n");
    }
}
