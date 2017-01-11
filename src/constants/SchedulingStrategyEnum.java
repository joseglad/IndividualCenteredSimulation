package constants;

public enum SchedulingStrategyEnum {
	/**
	 * Each agent must done an action before that any agent start a second action. And the agent sequence must change at every tick.
	 */
    Fair,
    /**
     * Each agent must done an action before that any agent start a second action. But the sequence have not change.
     */
    Sequential,

    /**
     * ???
     */
    Random
}
