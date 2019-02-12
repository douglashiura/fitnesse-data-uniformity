package doug;

import java.util.UUID;

import net.douglashiura.us.serial.Input;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Output;

public class FitnesseScenario {

	private Interaction interaction;
	private String fullPath;

	public FitnesseScenario(String fullPath) {
		this.fullPath = fullPath;
		this.interaction = new Interaction(UUID.randomUUID(), "");
	}

	public void addOutput(String esperado) {
		interaction.addOutput(new Output(UUID.randomUUID(), "", esperado));
	}

	public void addInput(String esperado) {
		interaction.addInput(new Input(UUID.randomUUID(), "", esperado));

	}

	public Interaction getScenario() {
		return interaction;
	}

	public String getFullPath() {
		return fullPath;
	}
}
