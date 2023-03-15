package com.example.cakemachine.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Collection;

public class EncodeUtil {
    private EncodeUtil(){

    }

    public static <T> boolean encode(String filename, Collection<T> items){
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)))) {
            encode(encoder, items);
        } catch (FileNotFoundException fileNotFoundException) {
            return false;
        }
        return true;
    }

    public static <T> void encode(XMLEncoder encoder, Collection<T> items){
        encoder.writeObject(items);
    }

    public static <T> Collection<T> decode(String filename){
        try ( XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)))) {
            return decode(decoder);
        } catch (FileNotFoundException fileNotFoundException) {
            return null;
        }
    }

    public static <T> Collection<T> decode(XMLDecoder decoder){
        return (Collection<T>) decoder.readObject();
    }
}
