package doug;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import fitnesse.ContextConfigurator;
import fitnesse.FitNesseContext;
import fitnesse.http.Request;
import fitnesse.responders.run.SuiteResponder;
import fitnesse.testrunner.MultipleTestsRunner;
import fitnesse.testrunner.PagesByTestSystem;
import fitnesse.testsystems.Assertion;
import fitnesse.testsystems.ExceptionResult;
import fitnesse.testsystems.TestPage;
import fitnesse.testsystems.TestResult;
import fitnesse.testsystems.TestSummary;
import fitnesse.testsystems.TestSystem;
import fitnesse.testsystems.TestSystemListener;
import fitnesse.testsystems.slim.tables.SlimAssertion;
import fitnesse.testsystems.slim.tables.SlimExpectation;
import fitnesse.wiki.WikiPage;

public class Main {

	public static void main(String[] args) throws Exception {
		SuiteResponder suit = new SuiteResponder();
		FitNesseContext context = ContextConfigurator.systemDefaults().makeFitNesseContext();
		Request request = new Request(new ByteArrayInputStream("".getBytes()));
		request.setResource(".petClinic");
		suit.makeResponse(context, request);
		List<WikiPage> pages = suit.getPagesToRun();
		PagesByTestSystem pagesByTestSystem = new PagesByTestSystem(pages, context.getRootPage());
		MultipleTestsRunner runner = new MultipleTestsRunner(pagesByTestSystem, context.testSystemFactory);
		Listener listener = new Listener();
		runner.addTestSystemListener(listener);
		runner.executeTestPages();

		List<FitnesseScenario> scenaries = listener.getScenaries();
		List<Pair> pairs = Pairs.from(scenaries);
		FileOutputStream fileOutputStream = new FileOutputStream("qualquer");
		for (Pair pair : pairs) {
			System.out.printf("%s	%s	%s	%s	%s	%s	%s\n", pair.getScenarioA().getFullPath(),
					pair.getScenarioB().getFullPath(), pair.getUniform().getRelativeUniformity().getUniformity(),
					pair.getUniform().getUniformIntputs(), pair.getUniform().getNonUniforInputs(),
					pair.getUniform().getUniformOutputs(), pair.getUniform().getNonUniformOutputs());

			fileOutputStream.write(String.format("%s	%s	%s	%s	%s	%s	%s\n", pair.getScenarioA().getFullPath(),
					pair.getScenarioB().getFullPath(), pair.getUniform().getRelativeUniformity().getUniformity(),
					pair.getUniform().getUniformIntputs(), pair.getUniform().getNonUniforInputs(),
					pair.getUniform().getUniformOutputs(), pair.getUniform().getNonUniformOutputs()).getBytes());
		}

	}

	static class Listener implements TestSystemListener {
		private List<FitnesseScenario> scenaries;
		private FitnesseScenario current;

		public Listener() {
			scenaries = new ArrayList<>();
		}

		public List<FitnesseScenario> getScenaries() {
			return scenaries;
		}

		@Override
		public void testSystemStarted(TestSystem testSystem) {

		}

		@Override
		public void testOutputChunk(String output) {
		}

		@Override
		public void testStarted(TestPage testPage) {
			current = new FitnesseScenario(testPage.getFullPath());
			scenaries.add(current);
		}

		@Override
		public void testComplete(TestPage testPage, TestSummary testSummary) {
		}

		@Override
		public void testSystemStopped(TestSystem testSystem, Throwable cause) {
		}

		@Override
		public void testAssertionVerified(Assertion assertion, TestResult testResult) {
			print(assertion);
		}

		@Override
		public void testExceptionOccurred(Assertion assertion, ExceptionResult exceptionResult) {
			print(assertion);
		}

		private void print(Assertion assertion) {
			SlimAssertion anAssertion = (SlimAssertion) assertion;
			SlimExpectation expectation = anAssertion.getExpectation();
			if (expectation instanceof fitnesse.testsystems.slim.tables.SlimTable.VoidReturnExpectation) {
				current.addOutput(expectation.getEsperado());
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.SlimTable.ReturnedValueExpectation) {
				current.addOutput(expectation.getEsperado());
			} else if (isFixture(anAssertion)) {
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.ArgumentExpectation) {
				current.addInput(expectation.getEsperado());
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.ScriptActionExpectation) {
				printArgs(anAssertion);
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.EnsureActionExpectation) {
				for (Object object : anAssertion.getArgs()) {
					current.addOutput(object.toString());
				}
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.RejectActionExpectation) {
				printArgs(anAssertion);
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.QueryTable.QueryTableExpectation) {
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.TableTable.TableTableExpectation) {
				for (Object object : anAssertion.getArgs()) {
					current.addOutput(object.toString());
				}
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.SlimTable.SymbolAssignmentExpectation) {
				current.addOutput(expectation.getEsperado());
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.ShowActionExpectation) {
			}

			else {
				System.out.println(expectation.getEsperado() + anAssertion);
			}
		}

		private void printArgs(SlimAssertion anAssertion) {
			for (Object object : anAssertion.getArgs()) {
				current.addInput(object.toString());
			}
		}

		private boolean isFixture(SlimAssertion anAssertion) {
			SlimExpectation expectation = anAssertion.getExpectation();
			return expectation instanceof fitnesse.testsystems.slim.tables.SlimTable.ConstructionExpectation
					|| expectation instanceof fitnesse.testsystems.slim.tables.ImportTable.ImportExpectation
					|| expectation instanceof fitnesse.testsystems.slim.tables.ScenarioTable.ScenarioExpectation;
		}

	}

}
