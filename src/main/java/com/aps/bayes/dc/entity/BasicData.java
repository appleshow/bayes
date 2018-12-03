package com.aps.bayes.dc.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BasicData {
    private List<Integer> blues;
    private int red;

    private String abcProportion;

    /**
     * @param blue
     * @param abc
     * @return
     */
    public boolean hasBluedWhenABC(Integer blue, String abc) {
        if (null != this.blues && null != abc) {
            if (abc.equals(this.abcProportion) && this.blues.contains(blue)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<Integer> getBlues() {
        return blues;
    }

    public void setBlues(List<Integer> blues) {
        this.blues = blues;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public String getAbcProportion() {
        return abcProportion;
    }

    public void setAbcProportion(String abcProportion) {
        this.abcProportion = abcProportion;
    }

    public void setAbcProportion() {
        if (null != this.blues) {
            final Map<String, Integer> abcCount = new HashMap<>();

            abcCount.put("a", 0);
            abcCount.put("b", 0);
            abcCount.put("c", 0);

            this.blues.stream().forEach(blue -> {
                if (blue < 12) {
                    abcCount.put("a", abcCount.get("a") + 1);
                } else if (blue < 23) {
                    abcCount.put("b", abcCount.get("b") + 1);
                } else if (blue < 34) {
                    abcCount.put("c", abcCount.get("c") + 1);
                }
            });

            this.abcProportion = String.format("%d:%d:%d", abcCount.get("a"), abcCount.get("b"), abcCount.get("c"));
        }
    }
}
