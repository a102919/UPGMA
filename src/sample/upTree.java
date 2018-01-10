package sample;

import java.util.ArrayList;
import java.util.List;

public class upTree {
    private upTree up = null;
    private List<upTree> doun = null;
    private int name;
    private int nameCount;
    private Double number;

    public upTree(int name, int nameCount, Double number) {
        this.name = name;
        this.nameCount = nameCount;
        this.number = number;
    }

    public int getNameCount() {
        return nameCount;
    }

    public void setDoun(upTree doun) {
        if(this.doun == null)
            this.doun = new ArrayList<>();
        this.doun.add(doun);
        doun.setUp(this);
    }

    public void setUp(upTree up) {
        this.up = up;
    }

    public upTree getUp() {
        return up;
    }

    public List<upTree> getDoun() {
        return doun;
    }

    public int getName() {
        return name;
    }

    public Double getNumber() {
        return number;
    }
}
