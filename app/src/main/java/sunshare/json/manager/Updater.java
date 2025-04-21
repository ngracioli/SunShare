package sunshare.json.manager;

@FunctionalInterface
public interface Updater<T> {
    T update(T value);
}
