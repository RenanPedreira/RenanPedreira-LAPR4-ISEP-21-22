package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Title implements ValueObject,Comparable<Title>{
    @Column(name = "Title")
    private String title;

    public Title(final String title)
    {
        this.title = title;
    }

    protected Title() {}

    public static Title valueOf(final String title){
        return new Title(title);
    }

    @Override
    public boolean equals(final Object o)
    {
        if(this == o)
            return true;
        if(!(o instanceof Title))
            return false;

        final Title that = (Title) o;
        return this.title.equals(that.title);
    }

    @Override
    public int hashCode(){return title.hashCode();}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(title).append("\n");

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(final Title tit){return title.compareTo(tit.title);}

    public String getTitle() {
        return title;
    }
}
