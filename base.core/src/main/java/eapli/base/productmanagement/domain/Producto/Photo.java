package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Arrays;

@Embeddable
public class Photo implements ValueObject {

    @Column(name = "photo")
    @Lob
    private byte[] photo;

    public Photo(){
    }

    public Photo(final byte[] photo){
        changePhoto(photo);
    }

    public byte[] getPhoto() {
        return photo;
    }

    public byte[] photo() {
        // defensive copy
        return Arrays.copyOf(photo, photo.length);
    }
    public void changePhoto(final byte[] photo) {
        // defensive copy
        this.photo = Arrays.copyOf(photo, photo.length);
    }

    @Override
    public String toString(){
        return String.valueOf(photo);
    }
}

