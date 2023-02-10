package orm;

public class Utils {
    

    public static String trimStart(String str, Character... c){
        if(str==null) return null;
        Character[] cCore=c;
        str=str.trim();
        if(str.length()==0) return str;

        for (int i=0;i<str.length();i++){
            if(ContainsArray(cCore,str.charAt(i))){
                continue;
            }else{
                return str.substring(i);
            }
        }
       return "";
    }

    public static String trimEnd(String str, Character... c){
        if(str==null) return null;
        Character[] cCore=c;
        str=str.trim();
        if(str.length()==0) return str;
        StringBuilder builder=new StringBuilder();
        for (int i=str.length()-1;i>0;i--){
            if(ContainsArray(cCore,str.charAt(i))){
                continue;
            }else{
                builder.append(str.substring(0,i+1));
                break;
            }
        }
        return builder.toString();
    }
    public static  String trimAll(String str, Character... c){
        String s=trimStart(str,c);
        return trimEnd(s,c);
    }
    private static <T> boolean ContainsArray(T[] t,T d){
        for (T w :t){
           
            if(w.equals(d)){
                return true;
            }
        }
        return false;
    }
    public static String clearStringTrim(String str){
        return  "'"+trimAll(str,' ','`','[',']','\'')+"'";
    }
    public static String clearStringTrimRaw(String str){
        return  trimAll(str,' ','`','[',']','\'');
    }


}
