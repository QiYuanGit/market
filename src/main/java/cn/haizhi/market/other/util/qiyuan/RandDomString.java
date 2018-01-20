package cn.haizhi.market.other.util.qiyuan;

public class RandDomString {
    public static String getSix(){
        String []baseStr = {"a","b","c","d","e","A","B","f","g","q","w","e","r","t","Y","y","u","i","o","p","j",
                "k","l","b","n","m"};
        String str="";
        for(int i = 0 ;i<6;i++) {
            int index = ((int) (100 * Math.random())) % baseStr.length;
            str += baseStr[index];
        }
        return str;
    }
}
