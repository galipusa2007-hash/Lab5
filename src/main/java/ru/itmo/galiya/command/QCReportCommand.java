package ru.itmo.galiya.command;

import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class QCReportCommand extends Command {//отчет по проверкам

    public QCReportCommand() {
        super("qc_report", "qc_report [--from YYYY-MM-DD] [--to YYYY-MM-DD] - отчёт по проверкам", false);
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length == 0) {
            return;
        }
        if (args.length == 2) {
            if (args[0].equals("--from") || args[0].equals("--to")) {
                validateDate(args[1]);
                return;
            }
        }
        if (args.length == 4) {
            boolean valid =
            (args[0].equals("--from") && args[2].equals("--to")) ||
            (args[0].equals("--to") && args[2].equals("--from"));
            if (valid) {
                validateDate(args[1]);
                validateDate(args[3]);
                return;
            }
        }
        throw new CommandArgsException("Используй: qc_report или qc_report --from YYYY-MM-DD или qc_report --to YYYY-MM-DD или qc_report --from YYYY-MM-DD --to YYYY-MM-DD");
    }
    private void validateDate(String value) throws CommandArgsException {
        try {
            LocalDate.parse(value);
        } catch (Exception e) {
            throw new CommandArgsException("Используй верный формат даты: " + value + ". Используй YYYY-MM-DD");
        }
    }
    @Override
    public void execute(Environment env, String[] args) {
        LocalDate from = null;
        LocalDate to = null;

        if (args.length == 2) {
            if (args[0].equals("--from")) {
                from = LocalDate.parse(args[1]);
            } else if (args[0].equals("--to")) {
                to = LocalDate.parse(args[1]);
            }
        }
        if (args.length == 4) {
            if (args[0].equals("--from")) {
                from = LocalDate.parse(args[1]);
                to = LocalDate.parse(args[3]);
            } else if (args[0].equals("--to")) {
                to = LocalDate.parse(args[1]);
                from = LocalDate.parse(args[3]);
            }
        }
        int total = 0;
        int pass = 0;
        int fail = 0;

        for (QCCheck check: env.getCheckManager().getAll()) {
            Instant checkedAtInstant = check.getCheckedAt();
            LocalDate checkedDate = checkedAtInstant.atZone(ZoneId.systemDefault()).toLocalDate();

            if (from != null && checkedDate.isBefore(from)) {
                continue;
            }
            if (to != null && checkedDate.isAfter(to)) {
                continue;
            }
            total++;

            if (check.getStatus() == QCStatus.PASS) {
                pass++;
            }
            else if (check.getStatus() == QCStatus.FAIL) {
                fail++;
            }
        }
        System.out.println("QC REPORT");
        System.out.println("From: " + (from == null ? "-" : from));
        System.out.println("To: " + (to == null ? "-" : to));
        System.out.println("Total checks: " + total);
        System.out.println("Passed: " + pass);
        System.out.println("Failed: " + fail);
    }
}
