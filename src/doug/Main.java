package doug;

import java.io.ByteArrayInputStream;
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
		request.setResource("HuntTheWumpus.AllRequirements");
		suit.makeResponse(context, request);
		List<WikiPage> pages = suit.getPagesToRun();
		PagesByTestSystem pagesByTestSystem = new PagesByTestSystem(pages, context.getRootPage());
		MultipleTestsRunner runner = new MultipleTestsRunner(pagesByTestSystem, context.testSystemFactory);
		runner.addTestSystemListener(new Listener());
		runner.executeTestPages();

	}

	static class Listener implements TestSystemListener {

		@Override
		public void testSystemStarted(TestSystem testSystem) {
		}

		@Override
		public void testOutputChunk(String output) {
		}

		@Override
		public void testStarted(TestPage testPage) {
			System.out.println("<<<Scenario>>>"+testPage.getFullPath());
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
				System.out.println("<<<INPUT>>>" + expectation.getEsperado());
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.SlimTable.ReturnedValueExpectation) {
				System.out.println("<<<OUTPUT>>>" + expectation.getEsperado());
			} else if (isFixture(anAssertion)) {

			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.ArgumentExpectation) {
				System.out.println("<<<INPUT>>>" + expectation.getEsperado());
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.ScriptActionExpectation) {
				printArgs(anAssertion, "<<<INPUT>>>");
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.EnsureActionExpectation) {
				printArgs(anAssertion, "<<<OUTPUT>>>");
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.ScriptTable.RejectActionExpectation) {
				printArgs(anAssertion, "<<<INPUT>>>");
			} else if (expectation instanceof fitnesse.testsystems.slim.tables.QueryTable.QueryTableExpectation) {
				fitnesse.testsystems.slim.tables.QueryTable.QueryTableExpectation query = (fitnesse.testsystems.slim.tables.QueryTable.QueryTableExpectation) expectation;
			} else {
				System.out.println(expectation.getEsperado() + anAssertion);
			}
		}

		private void printArgs(SlimAssertion anAssertion, String type) {
			for (Object object : anAssertion.getArgs())
				System.out.println(type + object);
		}

		private boolean isFixture(SlimAssertion anAssertion) {
			SlimExpectation expectation = anAssertion.getExpectation();
			return expectation instanceof fitnesse.testsystems.slim.tables.SlimTable.ConstructionExpectation
					|| expectation instanceof fitnesse.testsystems.slim.tables.ImportTable.ImportExpectation
					|| expectation instanceof fitnesse.testsystems.slim.tables.ScenarioTable.ScenarioExpectation;
		}

	}

}
