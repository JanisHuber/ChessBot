package org.example.chess;

import java.io.*;

public class SerializationUtil {
    public static <T extends Serializable> T deepClone(T object) {
        try {
            // Serialisieren (Objekt in Byte-Array umwandeln)
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(object);

            // Deserialisieren (Byte-Array in Objekt umwandeln)
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);

            @SuppressWarnings("unchecked")
            T clonedObject = (T) in.readObject();
            return clonedObject;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Fehler beim Klonen des Objekts", e);
        }
    }
}
