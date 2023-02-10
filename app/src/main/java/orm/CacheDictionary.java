package orm;

import java.util.Dictionary;
import java.util.Hashtable;


class CacheDictionary {

    private static final Object lock = new Object();

    private static final Dictionary<String, cacheMetaData> dic = new Hashtable();

    public static cacheMetaData getCacheMetaData(Class aClass)  {
        if (dic.get(aClass.getName()) == null) {
            synchronized (lock) {
                if (dic.get(aClass.getName()) == null) {
                    dic.put(aClass.getName(), new cacheMetaData<>(aClass));
                }
            }
        }
        return dic.get(aClass.getName());
    }
}
