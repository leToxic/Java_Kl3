package generics.pair;

import java.util.LinkedList;

/**
 * Created: 29.09.2022 at 12:12
 *
 * @author Plasek Sebastian
 */
public class PairMap<K, V> implements Map<K, V> {

    private final LinkedList<Pair<K, V>> list = new LinkedList<>();

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        V ret = null;

        for (Pair<K, V> kvPair : list) {
            if (kvPair.getContent1().equals(key)) {
                ret = kvPair.getContent2();
                kvPair.setContent2(value);
            }
        }

        if (ret == null) {
            list.add(new Pair<>(key, value));
        }

        return ret;
    }

    @Override
    public V get(K key) {
        V ret = null;
        for (Pair<K, V> kvPair : list) {
            if (kvPair.getContent1().equals(key)) {
                ret = kvPair.getContent2();
            }
        }
        return ret;
    }

    @Override
    public int size() {
        return list.size();a
    }
}
