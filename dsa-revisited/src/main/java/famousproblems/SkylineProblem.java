package famousproblems;

import java.util.HashMap;
import java.util.TreeMap;

class SkylineProblem {
}

class TimeMap {

    private HashMap<String, TreeMap<Integer, String>> keyTimeMap;

    public TimeMap() {
        keyTimeMap = new HashMap<String, TreeMap<Integer, String>>();
    }

    public void set(String key, String value, int timestamp) {
        if (!keyTimeMap.containsKey(key)) {
            keyTimeMap.put(key, new TreeMap<Integer, String>());
        }

        keyTimeMap.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!keyTimeMap.containsKey(key)) {
            return "";
        }

        Integer t = keyTimeMap.get(key).floorKey(timestamp);

        if (t != null) {
            return keyTimeMap.get(key).get(t);
        }

        // for(int t=timestamp; t>=0; t--) {
        //     if(keyTimeMap.get(key).containsKey(t)){
        //         return keyTimeMap.get(key).get(t);
        //     }
        // }

        return "";
    }
}
