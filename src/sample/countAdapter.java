package sample;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class countAdapter {
    private Map<Integer,upTree> arrayMap = new HashMap<>();
    private LinkedList<upTree> mapKey = new LinkedList<>();
    private double[][] data;
    public void initData(int size, List<String> dataFile){
        this.data = new double[size][size];
        dataFile.remove(0);
        for (int i=0;i<size;i++){
            String chString = dataFile.get(i).replaceAll("\t",",");
            String[] dataRow = chString.split(",");
            for(int j=0;j<size;j++){
                data[i][j] = Integer.parseInt(dataRow[j]);
            }
            upTree upTree = new upTree(i,1,0.0);
            arrayMap.put(i,upTree);
            mapKey.add(upTree);
        }
    }

    public void run(){
        int mapCountName = data.length;
        int dataSize = data.length;
        while (true){
            int x = 0;
            int y = 0;
            double com = 100000;
            for(int i=0;i<dataSize;i++){
                for(int j=0;j<dataSize;j++){
                    if(data[i][j] != 0 && data[i][j]<com){
                        com = data[i][j];
                        x=i;
                        y=j;
                    }
                }
            }
            dataSize--;

            data = makeMap(data,x,y,dataSize);

            int xcount = mapKey.get(x).getNameCount();
            int ycount = mapKey.get(y).getNameCount();
            upTree upTree = new upTree(mapCountName,xcount+ycount,com/2);
            upTree.setDoun(mapKey.get(x));
            upTree.setDoun(mapKey.get(y));
            arrayMap.put(mapCountName,upTree);
            mapKey.remove(y);
            mapKey.remove(x);
            mapKey.add(upTree);
            mapCountName++;
            if(dataSize == 1){
                break;
            }
        }
    }

    public void getTree(){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        for(Integer key:arrayMap.keySet()){
            if(arrayMap.get(key).getDoun() != null){
                for(upTree down:arrayMap.get(key).getDoun()){
                    double mydata = arrayMap.get(key).getNumber();
                    double downdata = down.getNumber();
                    String value = nf.format(mydata-downdata);
                    System.out.println(key+"->"+down.getName()+":"+value);
                }
            }
            if(arrayMap.get(key).getUp() != null){
                double mydata = arrayMap.get(key).getNumber();
                double updata = arrayMap.get(key).getUp().getNumber();
                String value = nf.format(updata-mydata);
                System.out.println(key+"->"+arrayMap.get(key).getUp().getName()+":"+value);
            }

        }
    }

    private double[][] makeMap(double[][] oldMap, int x, int y, int size){
        double[][] newData = new double[size][size];
        int newi = 0 , newj = 0;
        for(int i=0;i<size+1;i++){
            if(i==x || i==y)
                continue;
            newj = 0;
            for (int j=0;j<size+1;j++){
                if(j==x || j==y)
                    continue;
                newData[newi][newj] = oldMap[i][j];
                newj++;
            }
            newi++;
        }
        LinkedList<Double> changData = new LinkedList<>();
        int xcount = mapKey.get(x).getNameCount();
        int ycount = mapKey.get(y).getNameCount();
        for(int i=0;i<size+1;i++){
            if(i==x || i==y)
                continue;

            double newd = (oldMap[i][x] * xcount + oldMap[i][y] * ycount)/(xcount+ycount);
            changData.add(newd);
        }
        for(int i=0;i<size;i++){
            if(i==size-1){
                newData[i][i] = 0;
                continue;
            }
            newData[i][size-1] = changData.get(i);
            newData[size-1][i] = changData.get(i);
        }
        //printArray(newData);
        return newData;
    }
    private void printArray(double[][] newData){
        for (double[] dd:newData){
            for(double d:dd){
                System.out.print(d+" , ");
            }
            System.out.println();
        }

    }

}
