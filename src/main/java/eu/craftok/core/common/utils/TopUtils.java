package eu.craftok.core.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Project Craftok-Core Created by Sithey
 */

public class TopUtils {

    public static List<TopUtilsObject> getTop(List<TopUtilsObject> tops){
        List<TopUtilsObject> values = new ArrayList<>();
        HashMap<Object, Integer> map = new HashMap<>();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<Object, Integer> sorted_map = new TreeMap<>(bvc);
        for (TopUtilsObject player : tops) {
            map.put(player.object, player.value);
        }
        sorted_map.putAll(map);
        for (Object s : sorted_map.keySet()) {
            values.add(new TopUtilsObject(s, map.get(s)));
        }
        return values;
    }

    public static class TopUtilsObject{

        private Object object;
        private Integer value;

        public TopUtilsObject(Object object, Integer value){
            this.object = object;
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public Object getObject() {
            return object;
        }
    }

}
