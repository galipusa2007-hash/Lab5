package ru.itmo.galiya.command;

import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;
import ru.itmo.galiya.storage.AppData;
import ru.itmo.galiya.storage.FileStorage;

public class SaveCommand extends Command {
    private final FileStorage fileStorage;

    public SaveCommand() {
        super("save","save <path> - сохранение программу в файл",false);
        this.fileStorage = new FileStorage();
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 1) {
            throw new CommandArgsException("Используй: save <path>");
        }
    }
    @Override
    public void execute(Environment env, String[] args) {
        String path = args[0];

        AppData data = new AppData(
                env.getParamManager().exportData(),
                env.getSampleManager().exportData(),
                env.getPlanManager().exportData(),
                env.getCheckManager().exportData(),
                env.getLimitManager().exportData()
        );
        fileStorage.save(path, data);
        System.out.println("Сохранение прошло успешно: " + path);
    }
}
