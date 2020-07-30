import java.util.HashMap;
import java.util.Map;

class Main {

    // adaptee
  static class OldKey {
    private String val;

    public OldKey() {
    }

    public OldKey(String val) {
      this.val = val;
    }

    public String value() {
      return val;
    }

    @Override
    public String toString() {
      return String.format("OldKey(val=%s)", val);
    }

    static OldKey of(String pin) {
      if (pin.length() != 4) return null;
      String ret = String.format("%08d", Math.abs(pin.hashCode()) % 100000000);
      System.out.print(new OldKey(ret));
      return new OldKey(ret);
    }
  }

  static class NewKey {
    private String val;

    public NewKey() {
    }

    public NewKey(String val) {
      this.val = val;
    }

    public String value() {
      return val;
    }

    @Override
    public String toString() {
      return String.format("NewKey(val=%s)", val);
    }

    static NewKey of(String pin) {
      if (pin.length() != 4) return null;
      String ret = String.format("7777%08d", Math.abs(pin.hashCode()) % 100000000);
      System.out.print(new NewKey(ret));
      return new NewKey(ret);
    }
  }

  // target
  static class MyNewSystem {
    private Map<String, Integer> secretNumbers;

    public MyNewSystem() {
      this.secretNumbers = new HashMap<>();
    }

    public Integer getSecretNumber(NewKey key) {
      return secretNumbers.get(key.value());
    }

    public void setSecretNumber(NewKey key, Integer number) {
      secretNumbers.put(key.value(), number);
    }
  }

  // adapter
  static class OldKeyAdapter extends NewKey {
    private OldKey oldKey;

    public OldKeyAdapter(OldKey oldKey) {
      this.oldKey = oldKey;
    }

    @Override
    public String value() {
      return "7777" + oldKey.value();
    }
  }

  public static void main(String[] args) {
    MyNewSystem system = new MyNewSystem();

    System.out.print("before: ");
    System.out.printf("%n");
   // System.out.println(system.getSecretNumber(new OldKeyAdapter(OldKey.of("1234"))));
    system.getSecretNumber(new OldKeyAdapter(OldKey.of("1234")));

    system.setSecretNumber(NewKey.of("1234"), 42);

    System.out.print("after: ");
    System.out.printf("%n");
   // System.out.println(system.getSecretNumber(new OldKeyAdapter(OldKey.of("1234"))));
    system.getSecretNumber(new OldKeyAdapter(OldKey.of("1234")));
  }
}