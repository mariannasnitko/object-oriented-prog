public class CommandDemo {
    public static void main(final String[] arguments) {
        BankOperation op = new BankOperation();

        Command print = new PrintMoveOfFundsCommand(op);
        Command update = new UpdateExchangeRatesCommand(op);
        Command credit = new DoCreditAnalysis(op);

        ExecuteOperation exec = new ExecuteOperation();
        exec.register("print", print);
        exec.register("update", update);
        exec.register("credit", credit);

        exec.execute("print");
        exec.execute("update");
        exec.execute("credit");
    }
}