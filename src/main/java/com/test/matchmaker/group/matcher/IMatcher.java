package com.test.matchmaker.group.matcher;


import com.test.matchmaker.user.User;

/**
 * API для определения подходит ли игрок в группу по критериям.
 */
public interface IMatcher {
    /**
     * Определяет, подходит ли игрок в группу.
     * @param user игрок.
     * @param waitPeriod период ожидания, от которого должен рассчитываться коэффициент разброса значений.
     *                   Должен быть максимальным периодом ожидания для группы.
     * @return true - игрока можно добавить в группу. False - игрок не подходит в группу.
     */
    boolean isMatch(User user, long waitPeriod);
}
