class Main {
    // subject
    interface TextField {
        void append(char c);

        void remove();

        String getText();
    }

    // real subject
    static class MyTextField implements TextField {
        private String text;

        public MyTextField() {
            this.text = "";
        }

        @Override
        public void append(char c) {
            text += c;
        }

        @Override
        public void remove() {
            text = text.substring(0, text.length() - 1);
        }

        @Override
        public String getText() {
            return text;
        }
    }

    // proxy
    static class MyTextFieldProxy implements TextField {
        private TextField textField;

        public MyTextFieldProxy() {
            this.textField = new MyTextField();
        }

        @Override
        public void append(char c) {
            if (textField.getText().contains(". ")) {
                textField.append(Character.toUpperCase(c));
            } else {
                textField.append(c);
            }
        }

        @Override
        public void remove() {
            textField.remove();
        }

        @Override
        public String getText() {
            return textField.getText();
        }
    }

    public static void main(String[] args) {
        final String str = "hello. world";

        TextField a = new MyTextField();
        TextField b = new MyTextFieldProxy();

        for (char c : str.toCharArray()) {
            a.append(c);
            b.append(c);
        }

        System.out.println("a.getText() = \"" + a.getText() + "\"");
        System.out.println("b.getText() = \"" + b.getText() + "\"");
    }
}
