package sunshare.json.manager;

@FunctionalInterface
public interface Matcher<T> {
    public boolean assertion(T value);
}