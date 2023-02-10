package orm;


import java.util.List;

class cacheMetaData<T> {

    public List<ItemField> listColumn = null;
    public ItemField keyColumn = null;
    String tableName = null;
    String where = null;
    String[] appendCreateTable=null;
    private int isIAction = 0;
    private Class result = null;


    public cacheMetaData(Class<T> aClass)  {
        SetClass(aClass);
    }

    public boolean isIAction() {
        return isIAction == 1;
    }

    private void SetClass(Class tClass)  {
        if (result == null) {
            result = tClass;
        }
        if (tableName == null) {
            String s=Utils.clearStringTrim(AnotationOrm.getTableName(tClass));
            tableName = s;
        }

        if (where == null) {
            where = AnotationOrm.getWhere(tClass);
        }
        if(appendCreateTable==null){
            appendCreateTable=AnotationOrm.getAppendCommand(tClass);
        }
        if (keyColumn == null) {
            keyColumn = AnotationOrm.getKeyColumnItemField(tClass);
        }
        if (listColumn == null) {
            listColumn = AnotationOrm.getListItemFieldColumn(tClass);
        }
        if (isIAction == 0) {
            isIAction = 2;
            for (Class aClass : tClass.getInterfaces()) {
                if (aClass == IActionOrm.class) {
                    isIAction = 1;
                }
            }
        }
    }


    public String[] getStringSelect() {
        String[] list = new String[listColumn.size() + 1];
        for (int i = 0; i < listColumn.size(); i++) {
            list[i] =tableName+"."+ listColumn.get(i).columnName;
        }
        if (keyColumn != null && keyColumn.columnName != null)
            list[listColumn.size()] =tableName+"."+ keyColumn.columnName;
        return list;
    }


}
