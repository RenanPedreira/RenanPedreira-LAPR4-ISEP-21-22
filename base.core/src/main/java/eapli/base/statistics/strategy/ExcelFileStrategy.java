package eapli.base.statistics.strategy;

import eapli.base.questionnairemanagement.domain.Message;
import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.questionnairemanagement.domain.Section;
import eapli.base.statistics.domain.StatisticalData;
import eapli.base.statistics.domain.StatisticalReport;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExcelFileStrategy implements FileStrategy {
    private static final String QUESTION_TYPE1 = "Single-Choice";
    private static final String QUESTION_TYPE2 = "Single-Choice with Input";
    private static final String QUESTION_TYPE3 = "Multiple-Choice";
    private static final String QUESTION_TYPE4 = "Multiple-Choice with Input";
    private static final String QUESTION_TYPE5 = "Numeric";
    private static final String TEXT1 = "Statistics Section - ";
    private static final String TEXT2 = "Options";
    private static final String TEXT3 = "Number of Answers";
    private static final String TEXT4 = "Percentage (%)";

    @Override
    public void execute(StatisticalReport statisticalReport) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        StringBuilder stringBuilder;
        List<StatisticalData> rawDataListCopy = new LinkedList<>();
        List<StatisticalData> percentageListCopy = new LinkedList<>();

        rawDataListCopy.addAll(statisticalReport.rawDataList());
        percentageListCopy.addAll(statisticalReport.percentageList());

        for (Section section : statisticalReport.questionnaire().sectionList()) {
            XSSFSheet sheet = workbook.createSheet(section.title().getTitle());
            stringBuilder = new StringBuilder();

            stringBuilder.append(statisticalReport.questionnaire().identifier().getIdentifier().replace("\n", ""))
                    .append(" - ")
                    .append(statisticalReport.questionnaire().title().getTitle());
            sheet.createRow(1);
            sheet.getRow(1)
                    .createCell(1)
                    .setCellValue(stringBuilder.toString());
            sheet.autoSizeColumn(1);

            stringBuilder = new StringBuilder();

            stringBuilder.append(TEXT1).append(section.title().getTitle());
            sheet.createRow(3);
            sheet.getRow(3).createCell(1).setCellValue(stringBuilder.toString());

            int currentRow = 5;

            for (Question question : section.content()) {
                List<Double> auxiliaryList1 = new ArrayList<>();
                List<String> auxiliaryList2 = new ArrayList<>();

                stringBuilder = new StringBuilder();

                if (question.getType().getType().replace(";", "").equals(QUESTION_TYPE1) ||
                        question.getType().getType().replace(";", "").equals(QUESTION_TYPE2) ||
                        question.getType().getType().replace(";", "").equals(QUESTION_TYPE3) ||
                        question.getType().getType().replace(";", "").equals(QUESTION_TYPE4) ||
                        question.getType().getType().replace(";", "").equals(QUESTION_TYPE5)) {
                    stringBuilder.append(question.number()).append(" - ").append(question.interrogation().getInterrogation());
                    sheet.createRow(currentRow);
                    sheet.getRow(currentRow).createCell(1).setCellValue(stringBuilder.toString());

                    currentRow += 2;


                    if (question.getType().getType().replace(";", "").equals(QUESTION_TYPE1) ||
                            question.getType().getType().replace(";", "").equals(QUESTION_TYPE2) ||
                            question.getType().getType().replace(";", "").equals(QUESTION_TYPE3) ||
                            question.getType().getType().replace(";", "").equals(QUESTION_TYPE4)) {

                        currentRow = configureTableHeader(sheet, currentRow);

                        for (Message option : question.getOptionList())
                            currentRow = generateTable(sheet,
                                    option.getMessage().replace(";", ""),
                                    rawDataListCopy,
                                    percentageListCopy,
                                    auxiliaryList1,
                                    auxiliaryList2,
                                    currentRow);
                    }

                    if (question.getType().getType().replace(";", "").equals(QUESTION_TYPE5)) {
                        currentRow = configureTableHeader(sheet, currentRow);

                        for (int count = 0; count <= 5; count++)
                            currentRow = generateTable(sheet,
                                    String.valueOf(count),
                                    rawDataListCopy,
                                    percentageListCopy,
                                    auxiliaryList1,
                                    auxiliaryList2,
                                    currentRow);
                    }

                    XSSFDrawing xssfDrawing = sheet.createDrawingPatriarch();
                    XSSFClientAnchor clientAnchor = xssfDrawing.createAnchor(0, 0, 0, 0, 3, currentRow, 10, currentRow + 10);
                    XSSFChart chart = xssfDrawing.createChart(clientAnchor);

                    currentRow += 10;

                    chart.setTitleText("Chart - Percentage of each Answer");
                    chart.setTitleOverlay(false);

                    XDDFChartLegend chartLegend = chart.getOrAddLegend();

                    chartLegend.setPosition(LegendPosition.RIGHT);

                    XDDFDataSource<String> dataSource = XDDFDataSourcesFactory.fromArray(auxiliaryList2.toArray(new String[0]));
                    XDDFNumericalDataSource<Double> numericalDataSource = XDDFDataSourcesFactory.fromArray(auxiliaryList1.toArray(new Double[0]));
                    XDDFChartData chartData = chart.createData(ChartTypes.PIE, null, null);

                    chartData.setVaryColors(true);

                    XDDFChartData.Series series = chartData.addSeries(dataSource, numericalDataSource);

                    series.setTitle("Series", null);
                    chart.plot(chartData);
                }
            }
        }

        File directory = new File(System.getProperty("user.dir"));

        stringBuilder = new StringBuilder();

        directory.mkdirs();
        stringBuilder.append("Report").append(statisticalReport.getIdentifier().getIdentifier()).append(".xlsx");

        File file = new File(directory, stringBuilder.toString());

        try {
            FileOutputStream stream = new FileOutputStream(file);

            workbook.write(stream);
            workbook.close();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private int configureTableHeader(XSSFSheet sheet, int currentRow) {
        sheet.createRow(currentRow);
        sheet.getRow(currentRow).createCell(3).setCellValue(TEXT2);
        sheet.getRow(currentRow).createCell(4).setCellValue(TEXT3);
        sheet.getRow(currentRow++).createCell(5).setCellValue(TEXT4);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);

        return currentRow;
    }

    private int generateTable(XSSFSheet sheet,
                              String string,
                              List<StatisticalData> rawDataListCopy,
                              List<StatisticalData> percentageListCopy,
                              List<Double> auxiliaryList1,
                              List<String> auxiliaryList2,
                              int currentRow) {
        double value;

        auxiliaryList2.add(string);
        sheet.createRow(currentRow);
        sheet.getRow(currentRow)
                .createCell(3)
                .setCellValue(string);
        sheet.getRow(currentRow)
                .createCell(4)
                .setCellValue(rawDataListCopy.remove(0).getValue());
        sheet.getRow(currentRow++)
                .createCell(5)
                .setCellValue((value = percentageListCopy.remove(0).getValue()));
        sheet.autoSizeColumn(3);

        auxiliaryList1.add(value);

        return currentRow;
    }
}
