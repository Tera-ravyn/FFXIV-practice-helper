import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.TransferHandler;

class App{
    public static void main(String[] args) throws Exception{
        Mapping mapping = new Mapping();
        Transfer transfer = new Transfer();

        System.out.println("初次使用请阅读说明，运行时请确保文件夹中有映射表和待转换的曲谱");
        System.out.println("请输入要转换的曲谱名（不带后缀名）：");

        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        
        mapping.read("映射/mapping.txt");
        transfer.init(fileName);
        transfer.transfer(fileName, mapping);

        scan.close();
    }
}

class Transfer{
    private File file;
    public Transfer(){};
    public void init(String fileName){
        file = new File("键位/"+fileName+"-output.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("生成导出文件失败");
            }
        } else {
            try(FileWriter fw = new FileWriter(file)){
                fw.write("");
                fw.flush();
            } catch (IOException e) {
                System.out.println("请手动删除已有的output.txt文件");
            }
        }
    }
    public void transfer(String fileName, Mapping mapping){

        try (Scanner scan = new Scanner(new FileReader("曲谱/"+fileName+".txt")))
        {
            while(scan.hasNext()){
                String note = scan.next();
                try(FileWriter fw = new FileWriter(file, true)){
                    fw.write(mapping.transfer(note)+" ");
                    fw.flush();
                } catch (IOException e) {
                    System.out.println("转换写入失败");
                }

            }
            System.out.println("转换完成！请打开output.txt文件查看");
        }catch(IOException e) {
            System.out.println("打开曲谱文件失败");
        } 
    }

}

class Mapping{
    private Map<String, String> map = new HashMap();
    public Mapping(){
        map.clear();
        map.put("|","|");
        map.put("-","-");
        map.put("0","（空）");
    }
    public void read(String fileName){//read mapping
        try(Scanner scan = new Scanner(new FileReader(fileName))) {
            while (scan.hasNext()) {
                String note = scan.next();
                String key = scan.next();
                map.put(note, key);
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("没有找到映射表");
        }
   
    }
    public String transfer(String note){
        try{
            if(map.containsKey(note)) {
                return map.get(note);
            }
            else throw new Exception();
        } catch(Exception e) {
            return "?";
        }
    }

}