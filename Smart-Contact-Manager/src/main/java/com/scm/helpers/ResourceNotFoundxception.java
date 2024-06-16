package com.scm.helpers;

public class ResourceNotFoundxception extends RuntimeException{
    public ResourceNotFoundxception(String message){
        super(message);
    }
    public ResourceNotFoundxception(){
        super("Resource Not Found");
    }
}
