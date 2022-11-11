package eapli.base.protocol;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.net.UnknownHostException;
import java.util.List;

public class AgvsCurrentLocationRequest extends AGVProtocolRequest{

    private AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

    public AgvsCurrentLocationRequest(String inputLine) throws UnknownHostException {
        super(inputLine);
    }

    public String execute(){

        List<AGV> list = agvRepository.getAllAgvFromWarehouse(super.tokens[4]);

        return getAgvsCurrentLocationRequest(list);
    }

    public String getAgvsCurrentLocationRequest(List<AGV> list){
        StringBuilder result = new StringBuilder();

        for(AGV agv : list){
            if(agv.getIdentifier().toString().compareTo(super.tokens[5])!=0) {
                result.append(agv.getPosition().coordinates().getKey());
                result.append(" ");
                result.append(agv.getPosition().coordinates().getValue());
                result.append("-");
            }
        }
        result.append("\n");

        return result.toString();
    }

}
