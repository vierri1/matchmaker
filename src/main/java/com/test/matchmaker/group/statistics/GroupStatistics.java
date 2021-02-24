package com.test.matchmaker.group.statistics;


import com.test.matchmaker.user.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Сбор и обработка статистики по группе.
 */
public class GroupStatistics {

    private static final String STAT_PLACEHOLDER = "%s max:%f;min:%f;avg:%f\n";
    private static final String TIME_PLACEHOLDER = "%s(sec) max:%d;min:%d;avg:%f\n";

    @Getter
    @Setter
    private double maxSkill;
    @Getter
    @Setter
    private double minSkill;
    @Getter
    @Setter
    private double avgSkill;
    @Getter
    @Setter
    private double maxLatency;
    @Getter
    @Setter
    private double minLatency;
    @Getter
    @Setter
    private double avgLatency;
    @Getter
    @Setter
    private long minTimeSpent;
    @Getter
    @Setter
    private long maxTimeSpent;
    @Getter
    @Setter
    private double avgTimeSpent;

    private int count;
    private double skillSum;
    private double latencySum;
    private double timeSpentSum;

    @Override
    public String toString() {
        return String.format(STAT_PLACEHOLDER, "SKILL", maxSkill, minSkill, avgSkill) +
                String.format(STAT_PLACEHOLDER, "LATENCY", maxLatency, minLatency, avgLatency) +
                String.format(TIME_PLACEHOLDER, "TIME SPENT", maxTimeSpent, minTimeSpent, avgTimeSpent);
    }

    public void addFor(User user) {
        double skill = user.getSkill();
        double latency = user.getLatency();
        long timeSpent = user.getWaitPeriod();

        handleSkill(skill);
        handleLatency(latency);
        handleTimeSpent(timeSpent);
        addSum(skill, latency, timeSpent);
        computeAvg();
    }

    private void handleSkill(double skill) {
        if (count == 0) {
            maxSkill = skill;
            minSkill = skill;
        } else {
            if (skill > maxSkill) {
                maxSkill = skill;
            } else if (skill < minSkill) {
                minSkill = skill;
            }
        }
    }

    private void handleLatency(double latency) {
        if (count == 0) {
            maxLatency = latency;
            minLatency = latency;
        } else {
            if (latency > maxLatency) {
                maxLatency = latency;
            } else if (latency < minLatency) {
                minLatency = latency;
            }
        }
    }

    private void handleTimeSpent(long timeSpent) {
        if (count == 0) {
            maxTimeSpent = timeSpent;
            minTimeSpent = timeSpent;
        } else {
            if (timeSpent > maxTimeSpent) {
                maxTimeSpent = timeSpent;
            } else if (timeSpent < minTimeSpent) {
                minTimeSpent = timeSpent;
            }
        }
    }

    private void addSum(double skill, double latency, long timeSpent) {
        count++;
        skillSum += skill;
        latencySum += latency;
        timeSpentSum += timeSpent;
    }

    private void computeAvg() {
        avgSkill = skillSum / count;
        avgLatency = latencySum / count;
        avgTimeSpent = timeSpentSum / count;
    }

}
