
public interface Collection<T> {
    /**
     * Добавляет элемент в коллекцию
     * @param element добавляемый элемент
     */
    void add(T element);

    /**
     * Удаляет элемент из коллекции с помощью <code>boolean equals()</code>
     * @param element удаляемый элемент
     */
    void remove(T element);

    /**
     * Проверяет наличие элемента в коллекции
     * @param element искомый элемент
     * @return <code>true</code>, если элемент найден
     * <code>false</code> если элемент не найден
     */
    boolean contains(T element);

    /**
     * Возвращает количество элементов в коллекции
     * @return число, равное количеству добавленных элементов
     */
    int size();
}
