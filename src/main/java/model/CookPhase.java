package model;

import java.util.List;
import java.util.Objects;

/**
 * Этап готовки, включающий в себя описание и шаги данного этапа готовки.
 */
public class CookPhase
{
    private final String summery;
    private final List<CookPhaseStep> steps;

    public CookPhase(String summery, List<CookPhaseStep> steps)
    {
        this.summery = summery;
        this.steps = steps;
    }

    public String getSummery()
    {
        return summery;
    }

    public List<CookPhaseStep> getSteps()
    {
        return steps;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookPhase cookPhase = (CookPhase) o;
        return Objects.equals(summery, cookPhase.summery) && Objects.equals(steps, cookPhase.steps);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(summery, steps);
    }

    @Override
    public String toString()
    {
        return "CookPhase{" +
                "summery='" + summery + '\'' +
                ", steps=" + steps +
                '}';
    }
}
