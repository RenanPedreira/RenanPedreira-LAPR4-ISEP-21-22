package eapli.base.agvmanagement.dto;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.common.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class AGVMapper {

    public List<AGVDTO> toDTO(List<AGV> agvList){
        List<AGVDTO> agvDTOList = new ArrayList<>();

        for(AGV agv: agvList) {
            Pair<Integer,Integer> pos = new Pair<>(agv.getPosition().getxPosition(),agv.getPosition().getyPosition());
            AGVDTO agvDTO = new AGVDTO(agv.getIdentifier().toString(),agv.getModel().toString(),agv.getTechnicalDescription().toString(),agv.getTaskStatus().toString(),agv.getAutonomyStatus().getDuration(),agv.getBaseLocation().toString(), agv.getCapacity().getWeight(),agv.getSpeed().getSpeed(),pos);
            agvDTOList.add(agvDTO);
        }
        return agvDTOList;
    }
}
