package ru.naumen.collection.task1;

import ru.naumen.collection.task1.User;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * <p>Воспользуемся методом new_coll.retainAll.</p>
 * Он позволяет за O(N*M) вернуть элементы коллекции совпадающие с другой коллекцией</p>
 * <p>При этом M - это сложность поиска элемента другой коллекции в первой.</p>
 * <p>Поэтому оптимальным видится использование двух HashSet, чтобы обеспечить O(M) = O(1),</p>
 * <p>так как они получают элемент по хэшкоду</p>
 *
 * <p>Итого: сложность - O(N)</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        var collBList = new HashSet<>(collB);
        var new_coll = new LinkedHashSet<>(collA);
        new_coll.retainAll(collBList);
        return new ArrayList<>(new_coll);
    }
}