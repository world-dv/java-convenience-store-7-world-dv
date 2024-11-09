package store.view;

public interface Input {

    String inputUser();

    String readItem();

    String readFree(String name, Integer amount);

    String readExtra(String name, Integer amount);

    String readMembership();
}
