import java.io.*;
import java.util.*;

public class Solution1{
    
    public static void main(String[] args) throws IOException{
      //ファイルの読み込み 
      File f = new File("input-heavy"); //どのファイルを読み込むか
      BufferedReader br = new BufferedReader(new FileReader(f));
      String str;
      int n = 0; //入力されたデータの行数
      ArrayList<String[]> cells = new ArrayList<String[]>(); //タブ区切りで集めたリストを:で分けて格納する
      ArrayList<String> txt = new ArrayList<String>(); //改行区切りで分けて格納する配列。
      String[] nothing = {""}; //比較や空の改行などがあった時にこれを使う
      
      while(!((str = br.readLine()) == null)) { //行数のカウントと読み込み
        txt.add(str);
        n++;
      }

      for (int i = 0; i < txt.size(); i++){ //空の改行があった場合、空文字列を偶数になる様に入れる
        String tmp = txt.get(i);
        if (tmp.equals("")){
          txt.add(i + 1, "");
          i++;
          n++; //一つ文字を入れたので行数も増やす
        }  
      }

      String[][] cell = new String[n][]; //タブ区切りの配列を集める配列

      for (int i = 0; i < cell.length; i++){
          cell[i] = txt.get(i).split("\t");
      }

      for (int i = 0; i < cell.length; i++){ //先ほどの二次元配列を用いてcellsに:区切りで格納
          for (int j = 0; j < cell[i].length; j++){
            String string = cell[i][j];
            cells.add(string.split(":",-1));
          }
      }

      br.close();


      //ファイルの書き込み
      File f1 = new File("data1.txt");
      BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
      for (int i = 0; i < cells.size() - 1; i += 2) { //キーと値をとるため2ずつiをとる
        String[] tmp1 = cells.get(i);
        String[] tmp2 = cells.get(i + 1);
        for (int j = 0; j < tmp1.length; j++) {
          for (int k = 0; k < tmp2.length; k++) {
            if (Arrays.equals(tmp1,nothing) && Arrays.equals(tmp2,nothing)) { //改行かキーがないだけか判別している。空の改行のとき、newLine
              bw.newLine();
            } else {
              bw.write(tmp1[j]);
              bw.write("	");
              bw.write(tmp2[k]);
              bw.newLine();
            }
          }
        }
      }

      if (cells.size() % 2 == 1){ //2ずつ要素を取っているため、値の記述がなくキーだけのときに最後の値を出力させる
        String[] tmp3 = cells.get(cells.size() - 1);
        for (int i = 0; i < tmp3.length; i++){
          bw.write(tmp3[i]);
          bw.newLine();
        }
      }
      bw.close();

    }
}