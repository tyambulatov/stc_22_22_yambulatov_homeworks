

public interface List<E> extends Collection<E> {
    /**
     * Удалить элемент по индексу
     * @param index индекс элемента
     */
    void removeAt(int index);

    /**
     * Получить элемент по индексу
     * @param index индекс элемента
     * @return значение, которое лежит под индексом, либо <code>null</code>
     */
    E get(int index);
}
