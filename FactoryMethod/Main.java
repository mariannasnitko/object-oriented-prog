class Main {

    interface IProduct {
        String ShipFrom();
    }

    static class ProductA implements IProduct {

        @Override
        public String ShipFrom() {
            return " from Ukraine";
        }
    }

    static class ProductB implements IProduct {

        @Override
        public String ShipFrom() {
            return " from Spain";
        }
    }

    static class DefaultProduct implements IProduct {

        @Override
        public String ShipFrom() {
            return " not available";
        }
    }

    static class Creator {
        private static Creator instance = null;

        private Creator() {
        }

        public static Creator getInstance() {
            if (instance == null)
                instance = new Creator();
            return instance;
        }

        public IProduct FactoryMethod(int month) {
            IProduct iproduct = null;
            if (month >= 4 && month <= 11) {
                iproduct = new ProductA();
            } else {
                if (month == 1 || month == 2 || month == 12) {
                    iproduct = new ProductB();
                } else {
                    iproduct = new DefaultProduct();
                }
            }
            return iproduct;
        }
    }

    public static void main(String[] args) {
        var creator = Creator.getInstance();
        // Creator c = new Creator();
        IProduct product;

        for (int i = 1; i <= 12; i++) {
            product = creator.FactoryMethod(i);
            System.out.println("Avocados" + product.ShipFrom());
        }
    }
}