package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(LINE_SEPARATOR);

        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate workDate = LocalDate.parse(parts[INDEX_DATE], DATE_FORMAT);
                String employeeName = parts[INDEX_NAME];
                int hours = Integer.parseInt(parts[INDEX_HOURS]);
                int payPerHour = Integer.parseInt(parts[INDEX_RATE]);

                if (employeeName.equals(name)
                        && !workDate.isBefore(from)
                        && !workDate.isAfter(to)) {
                    totalSalary += hours * payPerHour;
                }
            }

            report.append(name).append(" - ").append(totalSalary);
            if (i != names.length - 1) {
                report.append(LINE_SEPARATOR);
            }
        }

        return report.toString();
    }
}
