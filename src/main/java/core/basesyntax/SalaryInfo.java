package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        try {
            Date from = sdf.parse(dateFrom);
            Date to = sdf.parse(dateTo);

            for (String name : names) {
                int totalSalary = 0;
                for (String record : data) {
                    String[] parts = record.split(" ");
                    Date workDate = sdf.parse(parts[0]);
                    String employeeName = parts[1];
                    int hours = Integer.parseInt(parts[2]);
                    int payPerHour = Integer.parseInt(parts[3]);

                    if (employeeName.equals(name)
                            && !workDate.before(from) && !workDate.after(to)) {
                        totalSalary += hours * payPerHour;
                    }
                }
                report.append(name).append(" - ").append(totalSalary).append("\n");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return report.toString().trim();
    }
}
