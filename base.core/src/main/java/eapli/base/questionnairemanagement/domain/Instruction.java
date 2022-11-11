package eapli.base.questionnairemanagement.domain;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Instruction implements ValueObject, Comparable<Instruction> {
    @Column(name = "Instruction")
    private String instruction;

    public Instruction(final String instruction) {
        this.instruction = instruction;
    }

    protected Instruction(){}

    @Override
    public boolean equals(final Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Instruction))
            return false;

        final Instruction that = (Instruction) o;
        return this.instruction.equals(that.instruction);

    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(instruction);

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() { return this.instruction.hashCode();}

    @Override
    public int compareTo(final Instruction ins) {
        return instruction.compareTo(ins.instruction);
    }

    public String getInstruction() {
        return instruction;
    }

}
