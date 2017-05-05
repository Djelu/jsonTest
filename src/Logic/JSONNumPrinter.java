package Logic;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONNumPrinter {

    private int numsCount;
    private int threadsCount;

    public JSONNumPrinter(File file) {

        try (FileReader fileReader = new FileReader(file)){

            JSONObject jsonObject = (JSONObject) new JSONParser().parse(fileReader);

            numsCount   = ((Long)jsonObject.get("numsCount")).intValue();
            threadsCount= ((Long)jsonObject.get("threadsCount")).intValue();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public void printNums(){

        if((numsCount < 1)||(threadsCount < 0))
            System.out.println("Не правильные вводные данные:"+
                                "\nКоличество чисел   = "+numsCount+
                                "\nКоличество потоков = "+threadsCount);
        else {
            if(numsCount < threadsCount) threadsCount = numsCount;
            if(threadsCount < 1) threadsCount = 1;

            int[][] ranges = createRanges();


            Thread[] threads = new Thread[threadsCount];

            for(int i=0; i<threadsCount;  i++) {
                int low = ranges[i][0];
                int high= ranges[i][1];
                (threads[i] = new Thread(() -> print(low,high))).start();
            }
        }
    }

    private int[][] createRanges(){
        int count = numsCount/threadsCount;
        int otherCount = numsCount%threadsCount;

        int[][] ranges = new int[threadsCount][2];
        ranges[0][0] = 1;

        for(int i=0, k=0; i<threadsCount; i++){
            ranges[i][1] = count*(i+1)+k;
            if(i < otherCount)
                ranges[i][1] += ++k;
        }
        for(int i=1; i<threadsCount; i++) ranges[i][0] = ranges[i-1][1]+1;

        return ranges;
    }

    private void print(int low, int high){
        for(int i=low; i<=high; i++) {
            System.out.println(i);
            sleep(1);
        }
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
