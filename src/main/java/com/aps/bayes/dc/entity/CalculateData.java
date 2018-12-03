package com.aps.bayes.dc.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CalculateData {
    final public static List<Integer> blues;
    final public static Map<Integer, Double> blueFrequency;
    final public static Map<Integer, Map<String, Double>> blueWithABCFrequency;

    final private static Logger LOG = LogManager.getLogger(CalculateData.class);


    static {
        blues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33);
        blueFrequency = new HashMap<>();
        blueWithABCFrequency = new HashMap<>();

        blues.stream().forEach(blue -> blueFrequency.put(blue, 0.0));
    }

    /**
     * @param loadData
     */
    public static void initData(LoadData loadData) {
        final Map<Integer, Integer> blueCount = new HashMap<>();
        final Map<Integer, Map<String, Integer>> blueWithABCCount = new HashMap<>();

        blues.stream().forEach(blue -> {
            blueCount.put(blue, 0);

            final Map<String, Integer> abcCount = new HashMap<>();
            for (int a = 0; a <= 6; a++) {
                for (int b = 0; b <= 6; b++) {
                    for (int c = 0; c <= 6; c++) {
                        if (a + b + c == 6) {
                            abcCount.put(String.format("%d:%d:%d", a, b, c), 0);
                        }
                    }
                }
            }

            blueWithABCCount.put(blue, abcCount);
        });

        loadData.forEach(basicData -> {
            basicData.setAbcProportion();

            basicData.getBlues().forEach(blue -> {
                blueCount.put(blue, blueCount.get(blue) + 1);

                Map<String, Integer> abcCount = blueWithABCCount.get(blue);
                abcCount.put(basicData.getAbcProportion(), abcCount.get(basicData.getAbcProportion()) + 1);
                blueWithABCCount.put(blue, abcCount);
            });
        });

        blues.forEach(blue -> {
            blueFrequency.put(blue, (1.0 * blueCount.get(blue)) / loadData.size());

            final Map<String, Double> abcFrequency = new HashMap<>();
            blueWithABCCount.get(blue).forEach((abc, count) -> abcFrequency.put(abc, (1.0 * count) / blueCount.get(blue))
            );

            blueWithABCFrequency.put(blue, abcFrequency);
        });
    }

    /**
     * @param abc
     * @return
     */
    public static DCAnswer abcAnswer(String abc) {
        final DCAnswer dcAnswer = new DCAnswer();
        final List<KeyProbability> keyProbabilityArrayList = new ArrayList<>();
        double evidence = 0;

        for (int blue : blues) {
            evidence += blueFrequency.get(blue) * blueWithABCFrequency.get(blue).get(abc);
        }
        for (int blue : blues) {
            if (evidence == 0) {
                keyProbabilityArrayList.add(new KeyProbability(blue, 0.0));
            } else {
                keyProbabilityArrayList.add(new KeyProbability(blue, (blueFrequency.get(blue) * blueWithABCFrequency.get(blue).get(abc) * 100) / evidence));
            }
        }

        final String[] abcArray = abc.split(":");
        for (int index = 1; index <= abcArray.length; index++) {
            int times, from, to;
            List<Integer> exceptList = new ArrayList<>();

            times = Integer.parseInt(abcArray[index - 1]);
            switch (index) {
                case 1:
                    from = 1;
                    to = 11;

                    break;
                case 2:
                    from = 12;
                    to = 22;

                    break;
                case 3:
                    from = 23;
                    to = 33;

                    break;
                default:
                    times = 0;
                    from = 0;
                    to = 0;
            }

            for (int time = 1; time <= times; time++) {
                KeyProbability keyProbability = findMaxLessThan(keyProbabilityArrayList, exceptList, from, to);
                exceptList.add(keyProbability.getKey());
                dcAnswer.getBlues().add(keyProbability.getKey());
            }

        }

        dcAnswer.getBlues().sort((blue1, blue2) -> blue1 > blue2 ? 1 : -1);
        dcAnswer.setKeyProbabilityList(keyProbabilityArrayList);
        return dcAnswer;
    }

    /**
     * @param keyProbabilityList
     * @param exceptList
     * @param from
     * @param to
     * @return
     */
    private static KeyProbability findMaxLessThan(final List<KeyProbability> keyProbabilityList, final List<Integer> exceptList, final int from, final int to) {
        final KeyProbability keyProbability = new KeyProbability();

        keyProbability.setKey(keyProbabilityList.get(from).getKey());
        keyProbability.setProbability(keyProbabilityList.get(from).getProbability());
        keyProbabilityList.forEach(keyProbabilityTemp -> {
            if (keyProbabilityTemp.getKey() >= from
                    && keyProbabilityTemp.getKey() <= to
                    && keyProbabilityTemp.getProbability() >= keyProbability.getProbability()
                    && !exceptList.contains(keyProbabilityTemp.getKey())) {
                keyProbability.setKey(keyProbabilityTemp.getKey());
                keyProbability.setProbability(keyProbabilityTemp.getProbability());
            }
        });

        return keyProbability;
    }
}
