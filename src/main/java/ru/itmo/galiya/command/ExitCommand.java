package ru.itmo.galiya.command;

import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.Environment;

public class ExitCommand extends Command {//завершение программу

    public ExitCommand() {
        super("exit", "exit - выйти из системы", false);
    }
    @Override
    public void execute(Environment env, String[] args){
        System.exit(0);
    }
}