package fitnesse.testsystems.slim.tables;

import fitnesse.testsystems.Expectation;
import fitnesse.testsystems.TestResult;
import fitnesse.testsystems.slim.results.SlimExceptionResult;

public interface SlimExpectation extends Expectation {

	SlimExpectation NOOP_EXPECTATION = new SlimExpectation() {
		@Override
		public TestResult evaluateExpectation(Object returnValues) {
			return null;
		}

		@Override
		public SlimExceptionResult evaluateException(SlimExceptionResult exceptionResult) {
			return null;
		}

		@Override
		public String getEsperado() {
			return this.getClass().getSimpleName();
		}
	};

	TestResult evaluateExpectation(Object returnValues);

	SlimExceptionResult evaluateException(SlimExceptionResult exceptionResult);

	String getEsperado();
}