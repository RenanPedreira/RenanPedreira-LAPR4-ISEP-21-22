package eapli.base.warehousemanagement.factory;

import eapli.base.agvmanagement.domain.*;

import eapli.base.common.domain.model.Identifier;

import eapli.base.ordermanagement.domain.Ordre;


import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.base.warehousemanagement.domain.Assignment.AssignmentStatus;
import eapli.framework.domain.model.DomainFactory;



public class AssignmentFactory implements DomainFactory<Assignment> {
    private Assignment ass;
    private AssignmentStatus status;
    private Identifier identifier;
    private Ordre ordre;
    private AGV agv;


    public AssignmentFactory statusy(final AssignmentStatus status){
        this.status = status;
        return this;
    }
    public AssignmentFactory ordrey(final Ordre ordre){
        this.ordre = ordre;
        return this;
    }
    public AssignmentFactory agvy(final AGV agv){
        this.agv = agv;
        return this;
    }

    public AssignmentFactory idy(final Identifier identifier){
        this.identifier = identifier;
        return this;
    }


    private Assignment buildOrThrow() {
        if ( ass != null) {
            return ass;
        } else if (identifier != null && ordre != null && status!= null && agv != null) {
            //prod = new Product(internalCode,name,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category);
            ass = new Assignment(identifier,agv,status,ordre);
            return ass;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Assignment build() {
        final Assignment ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built product.
        ass = null;
        return ret;
    }
}
