package eapli.base.app.backoffice.console.presentation.warehouse_employee_functionalities;

import eapli.base.agvmanagement.application.ConfigureAGVController;
import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.warehousemanagement.dto.DockDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ConfigureAGVUI extends AbstractUI {
    private static final int EXIT_OPTION = 0;
    private static final int INITIAL_OPTION = 1;
    private static ConfigureAGVController controller = new ConfigureAGVController();

    @Override
    protected boolean doShow() {
        List<DockDTO> dockList = controller.retrieveAllAvailableDocks();
        String model = Console.readLine("Please insert the AGV's model: ");
        String description = Console.readLine("Please insert the AGV's technical description: ");
        String taskStatus = Console.readLine("Please insert the AGV's current status: ").toUpperCase();
        double capacity = Console.readDouble("Please insert the AGV's capacity: ");
        String dockIdentifier = null;

        int data = Console.readInteger("Please insert the AGV's battery autonomy: ");

        Duration batteryAutonomy = Duration.of(data, ChronoUnit.MINUTES);

        int count = 1;

        for (DockDTO dockDTO : dockList)
            System.out.printf("#%d - Dock Identifier : %s\n", count++, dockDTO.getIdentifier());

        int option1 = Console.readOption(INITIAL_OPTION, count, EXIT_OPTION);

        if (option1 >= INITIAL_OPTION && option1 <= count)
            dockIdentifier = dockList.get(option1 - 1).getIdentifier();

        AGVDTO agvDTO = controller.configureAGV(model, description, taskStatus, batteryAutonomy, dockIdentifier, capacity);

        String option2 = Console.readLine("\nPlease confirm new AGV data [Y|N]:");

        switch (option2) {
            case "Y":
                controller.persistAGV(agvDTO);

                break;
            case "N":

                break;
            default:
                System.out.printf("Invalid option inserted.\nAGV configuration aborted.");

                break;
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure new AGV";
    }
}
