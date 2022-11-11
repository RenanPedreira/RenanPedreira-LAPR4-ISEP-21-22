package eapli.base.dashboard.application;

import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.common.util.Pair;
import eapli.base.dashboard.Dashboard;
import eapli.base.warehousemanagement.service.GetWarehousePlantService;
import eapli.base.warehousemanagement.service.ListAGVService;

import java.util.List;

public class DashboardController {

    ListAGVService agvService1 = new ListAGVService();
    GetWarehousePlantService getWarehousePlantService = new GetWarehousePlantService();

    private static List<AGVDTO> listagvdto;

    public void showDashboard() {

        Dashboard server = new Dashboard(listagvdto);
        server.changeController(this);
        server.start();
    }

    public static void main(String[] args) {
         //ListAGVService agvService = new ListAGVService();
         //Dashboard server = new Dashboard(agvService.listAvailableAGVSDTO().get(i));//
        //
        Dashboard dashboard = new Dashboard();
         //server.start();
        dashboard.start();
    }


    public List<AGVDTO> getList() {
        return this.agvService1.listAGVSDTO();
        //return this.agvService1.listAvailableAGVSDTO();
    }

    public Pair<Integer,Integer> getPlant(){
        return getWarehousePlantService.plant();
    }

}
