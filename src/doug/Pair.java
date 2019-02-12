package doug;

import net.douglashiura.leb.uid.scenario.measures.uniform.AbsoluteUniformity;

public class Pair {

	private AbsoluteUniformity uniform;
	private FitnesseScenario scenarioA;
	private FitnesseScenario scenarioB;

	public Pair(FitnesseScenario scenarioA, FitnesseScenario scenarioB) {
		this.scenarioA = scenarioA;
		this.scenarioB = scenarioB;
		uniform = new AbsoluteUniformity(scenarioA.getScenario(), scenarioB.getScenario());
	}

	public FitnesseScenario getScenarioA() {
		return scenarioA;
	}

	public FitnesseScenario getScenarioB() {
		return scenarioB;
	}

	public AbsoluteUniformity getUniform() {
		return uniform;
	}
}
