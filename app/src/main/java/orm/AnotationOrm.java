package orm;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class AnotationOrm {
    public static String getTableName(Class aClass) {
        Temp t = new Temp();
        getTableNameInner(aClass, t);
        return t.name;
    }

    public static String getWhere(Class aClass) {
        Temp t = new Temp();
        getWhereInner(aClass, t);
        return t.where;
    }

    public static String[] getAppendCommand(Class aClass) {
        Temp t = new Temp();
        getAppendCommandCreateTable(aClass, t);
        return t.append;
    }
    private static void getAppendCommandCreateTable(Class aClass,Temp t){
        if (aClass == null) return ;
        try {
            if (aClass.isAnnotationPresent(MapAppendCommandCreateTable.class)) {
                t.append = ((MapAppendCommandCreateTable) aClass.getAnnotation(MapAppendCommandCreateTable.class)).value();
            } else {
                Class superClazz = aClass.getSuperclass();
                getAppendCommandCreateTable(superClazz, t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void getWhereInner(Class aClass, Temp t) {
        if (aClass == null) return;
        try {
            if (aClass.isAnnotationPresent(MapWhere.class)) {
                t.where = ((MapWhere) aClass.getAnnotation(MapWhere.class)).value();
            } else {
                Class superClazz = aClass.getSuperclass();
                getWhereInner(superClazz, t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static ItemField getKeyColumnItemField(Class aClass) {

        ItemField res = null;
        List<Field> df = getAllFields(aClass);
        for (Field f : df) {
            if (f.isAnnotationPresent(MapPrimaryKey.class)) {
                final MapPrimaryKey key = f.getAnnotation(MapPrimaryKey.class);
                res = new ItemField();
                res.type = f.getType();
                res.fieldName = f.getName();
                res.columnName =Utils.clearStringTrim(key.value());
                res.columnNameRaw=Utils.clearStringTrimRaw(key.value());


                res.field = f;
                break;
            }
        }
        return res;
    }

    public static List<ItemField> getListItemFieldColumn(Class aClass) {


        List<ItemField> list = new ArrayList<>();
        for (Field f : getAllFields(aClass)) {
            if (f.isAnnotationPresent(MapColumn.class)) {
                final MapColumn column = f.getAnnotation(MapColumn.class);
                final MapIndex mapIndex=f.getAnnotation(MapIndex.class);
                final MapUserField ss = f.getAnnotation(MapUserField.class);
                ItemField fi = new ItemField();
                if(mapIndex!=null){
                    fi.isIndex=true;
                }
                fi.columnName = Utils.clearStringTrim(column.value());
                fi.columnNameRaw= Utils.clearStringTrimRaw(column.value());

                fi.fieldName = f.getName();
                fi.type = f.getType();
                if (ss != null) {
                    fi.isUserType = true;
                    fi.aClassUserType = ss.IUserType();
                }
                list.add(fi);
                fi.field = f;
            }
        }
        return list;
    }

    private static List<Field> getAllFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        Class superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            fields.addAll(getAllFields(superClazz));
        }
        return fields;
    }

    static void getTableNameInner(Class clazz, Temp table) {

        try {
            if (clazz.isAnnotationPresent(MapTable.class)) {
                table.name = ((MapTable) clazz.getAnnotation(MapTable.class)).value();
            } else {
                Class superClazz = clazz.getSuperclass();
                getTableNameInner(superClazz, table);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    static class Temp {
        public String name;
        public String where;
        public String[] append;


//        public Temp() {
//            this.name = s;
//        }
    }

}
