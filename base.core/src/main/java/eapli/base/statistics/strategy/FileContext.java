package eapli.base.statistics.strategy;

import eapli.base.statistics.domain.StatisticalReport;

public class FileContext {
    private FileStrategy strategy;

    public void setStrategy(FileStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(StatisticalReport statisticalReport) {
        this.strategy.execute(statisticalReport);
    }
}
