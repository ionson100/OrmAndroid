package orm;

/**
 * Этот интерфей сипользовать  как замену сурогатного поля по гуду, он в основном работает для вытаскивания одиночной записи по гуиду
 */
public interface IUsingGuidId {
    String get_id();
}
