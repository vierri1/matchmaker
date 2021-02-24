package com.test.matchmaker.group.matcher;

/**
 * Матчер игроков, основанный на сравнении характеристик игрока по диапазону разброса значений.
 * Наследники должны определять, какую характеристику необходимо проверять на соответствие.
 */
public abstract class BaseMatcher implements IMatcher {

    /**
     * Увеличение диапазона допустимых значений для характеристик игрока в каждую секунду ожидания.
     */
    private final double incrementPerSec;
    /**
     * Минимальный коэффициент для определения диапазона допустимых значений характеристик игрока.
     */
    private final double minCoefficient;

    public BaseMatcher(double minCoefficient, double incrementPerSec) {
        this.minCoefficient = minCoefficient / 100;
        this.incrementPerSec = incrementPerSec / 100;
    }

    /**
     * Проверяет, что значение соответствует базовому значению. На результат влияет время ожидания в очереди,
     * чем оно больше, тем больший разброс в характеристиках допустим.
     *
     * @param baseValue  базовое значение, по которому идет сравнение.
     * @param value      проверяемое значение.
     * @param waitPeriod период ожидания.
     * @return true - проверяемое значение входит в допустимый диапазон, false - не входит.
     */
    protected boolean isMatch(Double baseValue, Double value, long waitPeriod) {
        double diff = baseValue * (minCoefficient + computeDiffCoefficient(waitPeriod));
        return isInRange(baseValue, value, diff);
    }

    private double computeDiffCoefficient(long waitPeriod) {
        return incrementPerSec * waitPeriod;
    }

    private boolean isInRange(Double baseValue, Double value, Double diff) {
        return (baseValue + diff) >= value && (baseValue - diff) <= value;
    }
}
