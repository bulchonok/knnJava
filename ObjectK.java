import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class ObjectK {
    static ArrayList<ObjectK> al = new ArrayList<>();
    static ArrayList<ObjectK> al_test = new ArrayList<>();

    String group;
    List<Double> params;

    public ObjectK(List<Double> params, String group) {
        this.group = group;
        this.params = params;
        al.add(this);
    }

    public ObjectK(List<Double> params, String group, String identifier) {
        this.group = group;
        this.params = params;
        al_test.add(this);
    }

    public double CalculateDistance(ObjectK tempObj) {
        double dist = 0;
        for (int i = 0; i < tempObj.params.size(); i++) {
            dist += Math.pow(tempObj.params.get(i) - this.params.get(i), 2);
        }
        dist = Math.sqrt(dist);
        return dist;
    }
}