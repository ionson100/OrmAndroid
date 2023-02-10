package orm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface ISession {
    /**
     *Exposes methods to manage a SQLite database.
     */
    SQLiteDatabase getSqLiteDatabase();

    /**
     * Обновление объекта, созданого на основе типа который мапится в таблицу
     */
    <T> int update(T item);

    /**
     * Вставка объекта в таблицу
     */
    <T> int insert(T item) ;

    /**
     *Удаление объекта из таблицы
     * @param item
     * @param <T>
     * @return
     */
    <T> int delete(T item) ;

    /**
     * Получение списка объкектов их талици
     * @param where  строка запроса, без слова where, можно просто: 1=1,
     * @param objects  массив параметров, если они присутствуют в запросе, или null
     */
    <T> List<T> getList(Class<T> tClass, String where, Object... objects);

    /**
     *Получение объекта по первичному ключу
     * @param tClass
     * @param id значение первичного ключа
     */
    <T> T get(Class<T> tClass, Object id) ;

    /**
     * Возвращение первого элемента из запроса
     * @param sql
     * @param objects
     * @return
     */
    Object executeScalar(String sql, Object... objects);

    /**
     * Execute a single SQL statement that is NOT a SELECT/INSERT/UPDATE/DELETE.
     * For INSERT statements, use any of the following instead.
     */
    void execSQL(String sql, Object... objects);

    /**
     * Runs the provided SQL and returns a Cursor over the result set.
     * Params:
     * sql – the SQL query. The SQL string must not be ; terminated
     * selectionArgs – You may include ?s in where clause in the query,
     * which will be replaced by the values from selectionArgs. The values will be bound as Strings.
     * Returns:
     * A Cursor object, which is positioned before the first entry.
     * Note that Cursors are not synchronized, see the documentation for more details.
     * @param sql
     * @param objects
     * @return
     */
    Cursor execSQLRaw(String sql, Object... objects);

    /**
     * Получение партикулярного названия таблицы

     */
    String getTableName(Class aClass);

    void beginTransaction();

    void commitTransaction();

    void endTransaction();

    /**
     * Проверка на существование таблицы
     */
    boolean tableExists(Class aClass) ;

    /**
     * Очистка таблицы, полная
     */
    int deleteAllRowsFromTable(Class aClass) ;

    /**
     * Удаление записей из таблицы, по условию

     * @param where Условие, без where
     * @param objects  массив параметров, если они присутствуют в запросе, или null
     */
    int deleteRowsFromTable(Class aClass, String where, Object... objects) ;

    /**
     * Удаление таблицы, если она существует
     * @param tableName название таблицы
     */
    void dropTable(String tableName) ;

    /**
     * Удаление таблицы, если она существует
     * @param aClass Тип удаляемой таблицы
     */
    void dropTable(Class aClass) ;

    /**
     * Создание таблицы
     */
    void createTable(Class aClass) ;


    void createTableIfNotExists(Class aClass) ;

    /**
     * Создание таблицы, если она не существует
     */
    String getSqlCreateTable(Class aClass);

    /**
     * Создание таблиц на основе типов, помеченных аннотацие MapTable
     * Находит все классы, и создает табллицы на основе этих классов, возвращает количество найденых таблиц;
     * @param context контекст приложения
     */
    int CreateAllTablesIfDoesExist(Context context) throws Exception;
}
