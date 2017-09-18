package com.mustafa.hashdehash.domain;

public class MessageDigest {

   
    private String message;

    private String digest;

    
    MessageDigest() {
    }

    public MessageDigest(String message, String digest) {
        this.message = message;
        this.digest = digest;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDigest() {
        return digest;
    }

    public void setAuthor(String digest) {
        this.digest = digest;
    }

    
}
