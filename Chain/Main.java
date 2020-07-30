import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

  static void info(String format, Object... args) {
    System.out.printf("[INFO] %s%n", String.format(format, args));
  }

  static class Pharmacy {

    private final String id;
    private final Map<String, Double> productNameToPrice;

    public Pharmacy(String id, Middleware provider, String... productNames) {
      this.id = id;
      this.productNameToPrice = new LinkedHashMap<>();

      for (String productName : productNames) {
        System.out.println();
        productNameToPrice.put(productName, provider.getPrice(productName));
      }
    }

    public void printPrices() {
      System.out.println();
      for (Map.Entry<String, Double> entry : productNameToPrice.entrySet()) {
        info("[%s] %s price is %.2f", id, entry.getKey(), entry.getValue());
      }
    }
  }

  // Concrete Handler
  static class Provider implements Middleware {

    private final String id;
    private final Middleware next;
    private final double multiplier;
    private final Map<String, Double> warehouse;

    public Provider(String id, Middleware next, double multiplier, Map<String, Double> warehouse) {
      this.id = id;
      this.next = next;
      this.multiplier = multiplier;
      this.warehouse = warehouse;
    }

    @Override
    public double getPrice(String productName) {
      if (warehouse.containsKey(productName)) {
        double ret = warehouse.get(productName);
        info("[%s] [productName=%s] [warehouse] %.2f", id, productName, ret);
        return ret;
      }
      double price = next.getPrice(productName);
      double ret = price * multiplier;
      info("[%s] [productName=%s] %.2f -> %.2f", id, productName, price, ret);
      return ret;
    }
  }

  // Concrete Handler
  static class Producer implements Middleware {

    private final String id;

    public Producer(String id) {
      this.id = id;
    }

    @Override
    public double getPrice(String productName) {
      double ret = ThreadLocalRandom.current().nextDouble(100.0, 500.0);
      info("[%s] [productName=%s] initial price: %.2f", id, productName, ret);
      return ret;
    }
  }

  interface Middleware {
    double getPrice(String productName);
  }

  public static void main(String[] args) {
    System.out.println("hello world");

    Producer producer  = new Producer("Producer ");

    Provider provider1 = new Provider("Provider1", producer , 1.3,
      Map.of("Lovastrin", 543.0));
    Provider provider2 = new Provider("Provider2", provider1, 1.2,
      Map.of("Infupen", 321.0));
    Provider provider3 = new Provider("Provider3", provider2, 1.1,
      Map.of("Corbinol", 432.0));

    Pharmacy pharmacy  = new Pharmacy("Pharmacy ", provider3,
      "Infupen", "Lovastrin", "Cyprotrigine", "Corbinol");

    pharmacy.printPrices();
  }
}