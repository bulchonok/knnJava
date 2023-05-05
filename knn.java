import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class knn {
    static HashMap<ObjectK, Double> distMap = new HashMap<>();

    public static void train(String train_set) {
        try {
            Files.lines(Path.of(train_set)).forEach(
                    line -> {
                        List<Double> params = new ArrayList<>();
                        String[] tmp = line.split(",");
                        for (int i = 0; i < tmp.length-1; i++) {
                            params.add(Double.parseDouble(tmp[i]));
                        }
                        new ObjectK(params,tmp[tmp.length-1]);
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String test(int k, String test_set) {
        AtomicInteger counter= new AtomicInteger();
        AtomicInteger counterRight= new AtomicInteger();
        try {
            Files.lines(Path.of(test_set)).forEach(
                    line -> {
                        List<Double> params = new ArrayList<>();
                        String[] tmp = line.split(",");
                        for (int i = 0; i < tmp.length-2; i++) {
                            params.add(Double.parseDouble(tmp[i]));
                        }
                        ObjectK temp = new ObjectK(params,tmp[tmp.length-1],"test");
                        if(test(k,temp).equals(temp.group)) {
                            counterRight.getAndIncrement();
                        }
                        counter.getAndIncrement();

                    }
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        int percentage = (int) (counterRight.doubleValue() / counter.doubleValue() * 100);
        System.out.println("("+counterRight+"/"+counter+") = "+percentage+"%");
        return null;

    }

    public static String test(int k, ObjectK testObj) {
        distMap.clear();
        for (ObjectK obj : ObjectK.al
        ) {
            distMap.put(obj, obj.CalculateDistance(testObj));
        }
        String[] arr = new String[k];
        distMap.entrySet().stream().sorted(HashMap.Entry.comparingByValue()
               ).limit(k).forEach(e -> {
            int l = 0;
            while (!(arr[l] == null || arr[l].length() == 0)) {
                l++;
            }
            arr[l] = e.getKey().group;
        });
        HashMap<String, Integer> counter = new HashMap<>();
        for (String a : arr
        ) {
            if (counter.get(a) == null) counter.put(a, 1);
            else counter.put(a, counter.get(a) + 1);
        }




        String testResult = (Collections.max(counter.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
        counter.clear();
        distMap.clear();
        if (testResult.equals(testObj.group)){
            System.out.println(testResult+" it was a +");
        }else {
            System.out.println("wrong result: "+testResult+" was "+testObj.group+" -");
        }
        return testResult;


    }
}
