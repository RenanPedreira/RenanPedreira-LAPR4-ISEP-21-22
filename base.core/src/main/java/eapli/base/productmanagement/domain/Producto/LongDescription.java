package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LongDescription implements ValueObject {

    @Column(name = "longDescription")
    private String longDescription;

    public LongDescription(){
    }

    public LongDescription(final String longDescription) {
       changeLd(longDescription);
    }

   public void changeLd(String longDescription){
       if (longDescription.length() < 20 || longDescription.length() > 100 || longDescription == null) {
           throw new IllegalArgumentException("Extended description needs to be 20-100.");
       }
       this.longDescription = longDescription;
   }

   /* @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LongDescription)) {
            return false;
        }

        final LongDescription that = (LongDescription) o;
        return this.longDescription.equals(that.longDescription);
    }
*/

    public String getLongDescription() {
        return longDescription;
    }

    @Override
    public String toString(){
        return longDescription;
    }

}
