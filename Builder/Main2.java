class Main {

    public static class Menu {
        public String Place;
        public String MeatDish;
        public String SideDish;
        public String Drink;
        public String Dessert;
    }

    public static abstract class MenuBuilder {
        public Menu menu;

        public void CreateMenu() {
            menu = new Menu();
        }

        public Menu DispatchMenu() {
            return menu;
        }

        public abstract void SetMenuType();

        public abstract void SetMeatDish();

        public abstract void SetSideDish();

        public abstract void SetDrink();

        public abstract void SetDessert();
    }

    // concrete builders
    public static class MacBuilder extends MenuBuilder {
        @Override
        public void SetMenuType() {
            menu.Place = "MacDonalds";
        }

        @Override
        public void SetMeatDish() {
            menu.MeatDish = "Mac meat dish";
        }

        @Override
        public void SetSideDish() {
            menu.SideDish = "Mac side dish";
        }

        @Override
        public void SetDrink() {
            menu.Drink = "Mac drink";
        }

        @Override
        public void SetDessert() {
            menu.Dessert = "Mac dessert";
        }
    }

    public static class FastBuilder extends MenuBuilder {
        @Override
        public void SetMenuType() {
            menu.Place = "Fast";
        }

        @Override
        public void SetMeatDish() {
            menu.MeatDish = "Fast meat dish";
        }

        @Override
        public void SetSideDish() {
            menu.SideDish = "Fast side dish";
        }

        @Override
        public void SetDrink() {
            menu.Drink = "Fast drink";
        }

        @Override
        public void SetDessert() {
            menu.Dessert = "Fast dessert";
        }
    }

    public static class PuzataHataBuilder extends MenuBuilder {
        @Override
        public void SetMenuType() {
            menu.Place = "PuzataHata";
        }

        @Override
        public void SetMeatDish() {
            menu.MeatDish = "PuzataHata meat dish";
        }

        @Override
        public void SetSideDish() {
            menu.SideDish = "PuzataHata side dish";
        }

        @Override
        public void SetDrink() {
            menu.Drink = "PuzataHata drink";
        }

        @Override
        public void SetDessert() {
            menu.Dessert = "PuzataHata dessert";
        }
    }

    public static class Director {
        public Menu GenerateMenu(MenuBuilder menuBuilder) {
            menuBuilder.CreateMenu();
            menuBuilder.SetMenuType();
            menuBuilder.SetMeatDish();
            menuBuilder.SetSideDish();
            menuBuilder.SetDrink();
            menuBuilder.SetDessert();
            return menuBuilder.DispatchMenu();
        }
    }

    public static void main(String[] args) {
        MenuBuilder macMenuBuilder = new MacBuilder();
        Director dir = new Director();
        Menu macMenu = dir.GenerateMenu(macMenuBuilder);
        System.out.println(
                macMenu.MeatDish + '\n' + macMenu.SideDish + '\n' + macMenu.Drink + '\n' + macMenu.Dessert + '\n');

        MenuBuilder fastMenuBuilder = new FastBuilder();
        Menu fastMenu = dir.GenerateMenu(fastMenuBuilder);
        System.out.println(
                fastMenu.MeatDish + '\n' + fastMenu.SideDish + '\n' + fastMenu.Drink + '\n' + fastMenu.Dessert + '\n');

        MenuBuilder puzataMenuBuilder = new PuzataHataBuilder();
        Menu puzataMenu = dir.GenerateMenu(puzataMenuBuilder);
        System.out.println(puzataMenu.MeatDish + '\n' + puzataMenu.SideDish + '\n' + puzataMenu.Drink + '\n'
                + puzataMenu.Dessert + '\n');
    }
}