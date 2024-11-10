package store.view;

public interface Input {

    String inputUser();

    String readItem();

    String readFree(String name, int amount);

    String readExtra(String name, int amount);

    String readMembership();

    String readRestart();
}
