class Main {

public interface IClass {
    int cost(int money);
}

public static class Luxury implements IClass {
    @Override
    public int cost(int money) {
        money = 300;
        System.out.println("Tourist ordered a luxury service, so they get a taxi.");
        System.out.format("Total cost is %d ", money);
        return money;
    }
}

public static class Standard implements IClass {
    @Override
    public int cost(int money) {
        money = 200;
        System.out.println("Tourist ordered a standard service, so they get a microbus.");
        System.out.format("Total cost is %d ", money);
        return money;
    }
}

public static class Econom implements IClass {
    @Override
    public int cost(int money) {
        money = 100;
        System.out.println("Tourist ordered an econom service, so they get a big bus.");
        System.out.format("Total cost is %d ", money);
        return money;
    }
}

public static class Agency {
    private IClass classType;

    public void setClassType(IClass classType){
        this.classType = classType;
    }

    public  void calculateCost(){
        int money = 0;
        System.out.println("\nCalculating... ");
        classType.cost(money);
        System.out.println("\n");
    }

}

    public static void main(String[] args) {
        Agency agency = new Agency();

        agency.setClassType(new Luxury());
        agency.calculateCost();

        agency.setClassType(new Standard());
        agency.calculateCost();

        agency.setClassType(new Econom());
        agency.calculateCost();

        agency.setClassType(new Standard());
        agency.calculateCost();

    }
}