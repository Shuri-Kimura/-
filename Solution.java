import java.io.*;
import java.util.*;

public class Solution{
    
    public static void main(String[] args){ 

      ArrayList<String> sub1 = new ArrayList<String>(); //�^����ꂽ�f�[�^����:�̑O�������W�߂邽�߂̃��X�g
      ArrayList<String> sub2 = new ArrayList<String>(); //�^����ꂽ�f�[�^����:�̌�낾�����W�߂邽�߂̃��X�g

      try { //�t�@�C���̓ǂݍ���
        File f = new File("data.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<String> data1 = new ArrayList<String>(); //�Ƃ肠�����^���ꂽ�f�[�^���^�u��؂�Ŋi�[���邽�߂̃��X�g
        ArrayList<String> data2 = new ArrayList<String>(); //data1��:��؂�ŕ����邽�߂̃��X�g
        String nothing[] = new String[1]; //sub2�̂��߂�:���Ȃ��Ƃ���𖄂߂�String�^�̔z��
        String[] tmp = (br.readLine()).split("\t"); //�^����ꂽ�f�[�^���^�u���݂Ɋi�[����B
        String tmp2[] = new String[(tmp.length) * 2]; //data2����邽�߂̔z��

        for (int j=0; j < tmp.length; j++){ //data1��tmp���i�[����
            data1.add(tmp[j]);
        }
        
        for(int i = 0; i < tmp.length - 1; i += 2){ //:�ŕ�������tmp5�̗v�f���z��ł͂Ȃ��AString�^�ɂȂ�l��copy��p���Č���������(����f�[�^��������邽�߁A��΋����ƂȂ�)
            String[] tmp3 = tmp[i].split(":"); //i�Ԗڂ̗v�f��:�ŕ��������z��
            String[] tmp4 = tmp[i+1].split(":");// i+1�Ԗڂ̗v�f��:�ŕ��������z��
            String[] tmp5 = new String[tmp3.length + tmp4.length]; //��̓�����Ԓʂ�Ɍ����������z��
            System.arraycopy(tmp3, 0, tmp5, 0, tmp3.length);
            System.arraycopy(tmp4, 0, tmp5, tmp3.length, tmp4.length);
            for (int j = 0; j < tmp5.length; j++){
                data2.add(tmp5[j]);
            }
        }

        for (int i = 0; i < data1.size(); i++){
            if (data2.get(i) == data1.get(i)){ //data1��data2���ׂ�:�̌��sub2�Ɋi�[����Bsub1��:�̑O�B
               sub1.add(data2.get(i));
               sub2.add("0");
            } else {
               sub1.add(data2.get(i));
               sub2.add(data2.get(i+1));
               data2.remove(i+1);
            }
        }

        // for (int i = 0; i < data2.size(); i++) { //���ꂽsub2�������Ă��邩�m�F�̂��߂̏o��
        //   String tmpY = sub2.get(i);
        //     System.out.println(tmpY);
        // }

        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }


      try{ //�t�@�C���̏�������
          File f = new File("data1.txt");
          BufferedWriter bw = new BufferedWriter(new FileWriter(f));
          for (int i = 0; i < sub1.size() - 1; i += 2) {
            if (sub2.get(i) != "0" && sub2.get(i+1) != "0") { //�f�[�^�̃L�[�Ɨ�����:�����Ă���ꍇ
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
            }else if (sub2.get(i) != "0") { //�f�[�^�̃L�[��:�����Ă���ꍇ
                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();

                bw.write(sub2.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();
            } else if (sub2.get(i+1) != "0") { //�f�[�^�̒l��:�����Ă���ꍇ
                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub1.get(i+1));
                bw.newLine();

                bw.write(sub1.get(i));
                bw.write("	");
                bw.write(sub2.get(i+1));
                bw.newLine();
            } else { //�ǂ���ɂ�:�����Ă��Ȃ��ꍇ
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