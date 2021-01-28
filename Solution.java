import java.io.*;
import java.util.*;

public class Solution{
    
    public static void main(String[] args){ 

      ArrayList<String> sub1 = new ArrayList<String>(); //与えられたデータから:の前だけを集めるためのリスト
      ArrayList<String> sub2 = new ArrayList<String>(); //与えられたデータから:の後ろだけを集めるためのリスト

      try { //ファイルの読み込み
        File f = new File("data.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<String> data1 = new ArrayList<String>(); //とりあえず与えれたデータをタブ区切りで格納するためのリスト
        ArrayList<String> data2 = new ArrayList<String>(); //data1を:区切りで分けるためのリスト
        String nothing[] = new String[1]; //sub2のために:がないところを埋めるString型の配列
        String[] tmp = (br.readLine()).split("\t"); //与えられたデータをタブ刻みに格納する。
        String tmp2[] = new String[(tmp.length) * 2]; //data2を作るための配列

        for (int j=0; j < tmp.length; j++){ //data1にtmpを格納する
            data1.add(tmp[j]);
        }
        
        for(int i = 0; i < tmp.length - 1; i += 2){ //:で分割してtmp5の要素が配列ではなく、String型になる様にcopyを用いて結合させる(二個ずつデータが足されるため、絶対偶数となる)
            String[] tmp3 = tmp[i].split(":"); //i番目の要素を:で分割した配列
            String[] tmp4 = tmp[i+1].split(":");// i+1番目の要素を:で分割した配列
            String[] tmp5 = new String[tmp3.length + tmp4.length]; //上の二つを順番通りに結合させた配列
            System.arraycopy(tmp3, 0, tmp5, 0, tmp3.length);
            System.arraycopy(tmp4, 0, tmp5, tmp3.length, tmp4.length);
            for (int j = 0; j < tmp5.length; j++){
                data2.add(tmp5[j]);
            }
        }

        for (int i = 0; i < data1.size(); i++){
            if (data2.get(i) == data1.get(i)){ //data1とdata2を比べて:の後をsub2に格納する。sub1は:の前。
               sub1.add(data2.get(i));
               sub2.add("0");
            } else {
               sub1.add(data2.get(i));
               sub2.add(data2.get(i+1));
               data2.remove(i+1);
            }
        }

        // for (int i = 0; i < data2.size(); i++) { //作られたsub2があっているか確認のための出力
        //   String tmpY = sub2.get(i);
        //     System.out.println(tmpY);
        // }

        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }


      try{ //ファイルの書き込み
          File f = new File("data1.txt");
          BufferedWriter bw = new BufferedWriter(new FileWriter(f));
          for (int i = 0; i < sub1.size() - 1; i += 2) {
            if (sub2.get(i) != "0" && sub2.get(i+1) != "0") { //データのキーと両方に:がついている場合
                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();

                bw.write(sub2.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();

                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub2.get(i+1));
                bw.newLine();

                bw.write(sub2.get(i));
                bw.write("	");
                bw.write(sub2.get(i+1));
                bw.newLine();
            }else if (sub2.get(i) != "0") { //データのキーに:がついている場合
                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();

                bw.write(sub2.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();
            } else if (sub2.get(i+1) != "0") { //データの値に:がついている場合
                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();

                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub2.get(i+1));
                bw.newLine();
            } else { //どちらにも:がついていない場合
                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();
            }
          }
          bw.close();

      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}