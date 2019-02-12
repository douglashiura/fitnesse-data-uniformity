package doug;

import java.util.ArrayList;
import java.util.List;


public class Pairs {

	public static List<Pair> from(List<FitnesseScenario> scenaries) {
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < scenaries.size(); i++) {
			for (int j = 0; j < scenaries.size(); j++) {
				if (i != j) {
					pairs.add(new Pair(scenaries.get(i), scenaries.get(j)));
				}
			}
		}
		return pairs;
	}

}
