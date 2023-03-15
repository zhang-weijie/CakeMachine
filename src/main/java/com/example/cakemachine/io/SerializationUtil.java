package com.example.cakemachine.io;

import java.io.*;
import java.util.Collection;

public class SerializationUtil {
    private SerializationUtil(){

    }
    public static <T> boolean serialize(String filename, Collection<T> items){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
            serialize(oos,items);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static <T> void serialize(ObjectOutput objectOutput, Collection<T> items) throws IOException {
        objectOutput.writeObject(items);
    }

    public static <T> Collection<T> deserialize(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return deserialize(ois);
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    public static <T> Collection<T> deserialize(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return (Collection<T>)objectInput.readObject();
    }
}