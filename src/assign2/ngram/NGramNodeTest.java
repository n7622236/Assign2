package assign2.ngram;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Junit test for NGramNode
 * 
 * @author Chou,Shu-Hung(n7622236), Weiwei Nong(n8742600)
 */
public class NGramNodeTest {
	
	private NGramContainer nGramContainer;
	private NGramNode toTest;
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();	
     
	/**
	 * Initiation
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		toTest = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test Constructor1 with Valid Arguments
	 * 
	 * Create a new NGramNode instance and 
	 * provide valid words and valid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test
	public void test_ConstructorOne_With_ValidArguments() throws NGramException{
		String[] words = {"be","or","not","to"};
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(words, predictions, probabilities);
	}
	
	/**
	 * Test Constructor1 with Invalid words
	 * 
	 * Create a new NGramNode instance and 
	 * provide invalid words and valid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException - if words is null or empty or contains at least one empty or null string
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorOne_With_InValidWords() throws NGramException{
		String[] words = {"",null,"not","to"};
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(words, predictions, probabilities);
	}
	
	/**
	 * Test Constructor1 with Invalid predictions
	 * 
	 * Create a new NGramNode instance and 
	 * provide valid words and invalid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException - if predictions is null or empty or contains at least one empty or null string
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorOne_With_InValidPredictions() throws NGramException{
		String[] words = {"be","or","not","to"};
		String[] predictions = {"", null, "exceed", "say", "the"};
		Double[] probabilities ={0.136059, 0.066563, 0.032759, 0.028824, 0.024524};;
		nGramContainer = new NGramNode(words, predictions, probabilities);
	}
	
	/**
	 * Test Constructor1 with Invalid probabilities
	 * Create a new NGramNode instance and 
	 * provide valid words and valid predictions and invalid probabilities
     * during construction.
     * 
	 * @throws NGramException - if probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorOne_With_InValidProbabilities() throws NGramException{
		String[] words = {"be","or","not","to"};
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {-1.0, 0.0, null, 2.0, 0.024524};
		nGramContainer = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test Constructor1 - the different length between predictions and probabilities
	 * Create a new NGramNode instance and 
	 * provide valid words and the different length of valid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException - if predictions.length is different from probabilities.length
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorOne_DifferentLength_Between_Predictions_And_Probabilities() throws NGramException{
		String[] words = {"be","or","not","to"};
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(words, predictions, probabilities);
	}
	
	/**
	 * Test Constructor2
	 * Create a new NGramNode instance and 
	 * provide a valid context and valid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test
	public void test_ConstructorTwo_With_ValidArguments() throws NGramException{
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test Constructor2 with Invalid context
	 * Create a new NGramNode instance and 
	 * provide a invalid context and valid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException - if context is null or empty
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorTwo_With_InValidWords() throws NGramException{
		String context="";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		
		context = null;
		nGramContainer = new NGramNode(context, predictions, probabilities);
	}
	
	/**
	 * Test Constructor2 with Invalid predictions
	 * Create a new NGramNode instance and 
	 * provide a valid context and invalid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException - if predictions is null or empty or contains at least one empty or null string
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorTwo_With_InValidPredictions() throws NGramException{
		String context = "be or not to";
		String[] predictions = {"", null, "exceed", "say", "the"};
		Double[] probabilities ={0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
	}
	
	/**
	 * Test Constructor2 with Invalid probabilities
	 * Create a new NGramNode instance and 
	 * provide a valid context and valid predictions and invalid probabilities
     * during construction.
     * 
	 * @throws NGramException - if probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorTwo_With_InValidProbabilities() throws NGramException{
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {-1.0, 0.0, null, 2.0, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
	}
	
	/**
	 * Test Constructor2 - the different length between predictions and probabilities
	 * Create a new NGramNode instance and 
	 * provide valid context and the different length of valid predictions and valid probabilities
     * during construction.
     * 
	 * @throws NGramException - if predictions.length is different from probabilities.length
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_ConstructorTwo_DifferentLength_Between_Predictions_And_Probabilities() throws NGramException{
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
	}
	
	/**
	 * Set the valid context during construction,
	 * get the value of context by getCotext(), and check if it is same valid context.
	 * 
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_GetContext() throws NGramException {
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		
		context = nGramContainer.getContext();
		Assert.assertEquals("be or not to", context);
	}

	/**
	 * Follow by the above test
	 * set valid context then compare the context called by getContext()
	 * if they are the same, the test passes  
	 * 
	 * @throws NGramException - if context is null or empty
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_SetContext_With_Valid_context() throws NGramException {
		nGramContainer.getContext();
		String context = "be or not to";
		nGramContainer.setContext(context);
		Assert.assertEquals(context, nGramContainer.getContext());
	}
	
	/**
	 * Follow by the above test
	 * set invalid context then compare the context called by getContext()
	 * if NGramException occurs, the test passes  
	 * 
	 * @throws NGramException - if context is null or empty
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_Method_SetContext_With_InValid_context() throws NGramException {
		nGramContainer.getContext();
		String context = "";
		nGramContainer.setContext(context);
		
		context = null;
		nGramContainer.setContext(context);
	}
	
	/**
	 * set valid words then make up the context by toString()
	 * in order to compare the context called by getContext()
	 * if they are the same, the test passes  
	 * 
	 * @throws NGramException - if words is null or empty or contains at least one empty or null string
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test
	public void test_Method_SetContext_With_Valid_Words() throws NGramException {
		String[] words = {"be","or","not","to"};
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(words, predictions, probabilities);
		
		nGramContainer.setContext(words);
		Assert.assertEquals(words.toString(), nGramContainer.getContext());
	}

	/**
	 * Follow by above test
	 * Get the array of predictions and check 
	 * whether the predictions are the same as it set in the construction
	 * 
	 * @author  Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_GetPredictions() {
		String[] predictions = nGramContainer.getPredictions();
		Assert.assertEquals("be", predictions[0]);
		Assert.assertEquals("mention", predictions[1]);
		Assert.assertEquals("exceed", predictions[2]);
		Assert.assertEquals("say", predictions[3]);
		Assert.assertEquals("the", predictions[4]);
	}

	/**
	 * Follow by above test
	 * set a list of valid predictions by calling the method setPredictions()
	 * compare with the predictions called by getPrediction()
	 * if they are the same, the test passes
	 * 
	 * @throws NGramException if predictions is null or empty or contains at least one empty or null string
	 * @author  Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_SetPredictions_With_Valid_Predictions() throws NGramException {
		String[] predictions = {"is", "the", "same"};
		nGramContainer.setPredictions(predictions);
		
		Assert.assertEquals(predictions, nGramContainer.getPredictions());
	}
	
	/**
	 * Follow by above test
	 * set a list of invalid predictions by calling the method setPredictions()
	 * if NGramException occurs, the test passes
	 * 
	 * @throws NGramException if predictions is null or empty or contains at least one empty or null string
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test(expected = Exception.class) 
	public void test_Method_SetPredictions_With_nValid_Predictions() throws NGramException {
		String[] predictions = {"", "the", "null"};
		nGramContainer.setPredictions(predictions);
	}
	
	/**
	 * Constructs a list of valid probabilities
	 * those probabilities compares with the probabilities called by getProbabilities()
	 * if they are the same, the test passes
	 * 
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_GetProbabilities() throws NGramException {
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		
		Double[] testProbabilities = nGramContainer.getProbabilities();
		Assert.assertEquals(0.136059, testProbabilities[0]);
		Assert.assertEquals(0.066563, testProbabilities[1]);
		Assert.assertEquals(0.032759, testProbabilities[2]);
		Assert.assertEquals(0.028824, testProbabilities[3]);
		Assert.assertEquals(0.024524, testProbabilities[4]);
	}

	/**
	 * Follow by the above test
	 * set a list of valid probabilities by calling setProbabilities()
	 * those probabilities compares with the probabilities called by getProbabilities()
	 * if they are the same, the test passes
	 * 
	 * @throws NGramException if probabilities null or contains at least one  entry which is null , zero, negative or greater than 1.0
	 */
	@Test
	public void test_Method_SetProbabilities_With_Valid_Probabilities() throws NGramException {
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer.setProbabilities(probabilities);
		
		Double[] testProbabilities = nGramContainer.getProbabilities();
		Assert.assertEquals(0.136059, testProbabilities[0]);
		Assert.assertEquals(0.066563, testProbabilities[1]);
		Assert.assertEquals(0.032759, testProbabilities[2]);
		Assert.assertEquals(0.028824, testProbabilities[3]);
		Assert.assertEquals(0.024524, testProbabilities[4]);
	}
	
	/**
	 * Follow by the above test
	 * set a list of invalid probabilities by calling setProbabilities()
	 * those probabilities compares with the probabilities called by getProbabilities()
	 * if NGramException occurs, the test passes
	 * 
	 * @throws NGramException if probabilities null or contains at least one  entry which is null , zero, negative or greater than 1.0
	 * @author Weiwei Nong(n8742600)
	 */
	@Test(expected = Exception.class) 
	public void test_Method_SetProbabilities_With_InValid_Probabilities() throws NGramException {
		Double[] probabilities = {0.0, null, -1.0, 2.0};
		nGramContainer.setProbabilities(probabilities);
	}
	
	/**
	 * test the format of result context
	 * Construct a valid context, a list context of predictions and a list of probabilities 
	 * if the result matches precisely the format, the test passes
	 * 
	 * @throws NGramException
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_ToString() throws NGramException {
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.13605912332,0.066563234345,0.03275912314,0.028823899932,0.0245242343};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		
		String correctFormatResult = "be or not to | be : 0.136059\n"+
				"be or not to | mention : 0.066563\n"+
				"be or not to | exceed : 0.032759\n"+
				"be or not to | say : 0.028824\n"+
				"be or not to | the : 0.024524\n";
		String result = nGramContainer.toString();
		Assert.assertEquals(correctFormatResult, result);
	}
	
	/*
     * Confirm that the API spec has not been violated through the
     * addition of public fields, constructors or methods that were
     * not requested
     */
    @Test
    public void NoExtraPublicMethods() {
                    //Extends Object, implements NGramContainer
                    final int toStringCount = 1;
                    final int NumObjectClassMethods = Array.getLength(Object.class.getMethods());
                    final int NumInterfaceMethods = Array.getLength(NGramContainer.class.getMethods());
                    final int NumNGramNodeClassMethods = Array.getLength(NGramNode.class.getMethods());
                    assertTrue(
                    		"obj:"+NumObjectClassMethods+":inter:"+NumInterfaceMethods+" - 1 (toString()) = class:"+NumNGramNodeClassMethods
                    		,(NumObjectClassMethods+NumInterfaceMethods-toStringCount)==NumNGramNodeClassMethods);
    }
   
    @Test
    public void NoExtraPublicFields() {
    //Extends Object, implements NGramContainer
                    final int NumObjectClassFields = Array.getLength(Object.class.getFields());
                    final int NumInterfaceFields = Array.getLength(NGramContainer.class.getFields());
                    final int NumNGramNodeClassFields = Array.getLength(NGramNode.class.getFields());
                    assertTrue("obj + interface = class",(NumObjectClassFields+NumInterfaceFields)==NumNGramNodeClassFields);
    }
   
    @Test
    public void NoExtraPublicConstructors() {
    //Extends Object, implements NGramContainer
                    final int ExtraConsCount =1;
                    final int NumObjectClassConstructors = Array.getLength(Object.class.getConstructors());
                    final int NumInterfaceConstructors = Array.getLength(NGramContainer.class.getConstructors());
                    final int NumNGramNodeClassConstructors = Array.getLength(NGramNode.class.getConstructors());
                    assertTrue("obj:"+NumObjectClassConstructors+":inter:"+NumInterfaceConstructors+" 1 (extra) = class:"+NumNGramNodeClassConstructors,
                                                    (NumObjectClassConstructors+NumInterfaceConstructors+ExtraConsCount)==NumNGramNodeClassConstructors);
    }
   
	@Test
    public void TOSTRING_ComplexObject() throws NGramException {
          DecimalFormat df = new DecimalFormat(NGramContainer.DecFormat);
          String test = "be or not to | be : 0.136059\n" + "be or not to | mention : 0.066563\n" +
                        "be or not to | exceed : 0.032759\n" + "be or not to | say : 0.028824\n" +
                        "be or not to | the : 0.024524\n";
          toTest.setContext("be or not to");
          toTest.setPredictions(new String[]{"be","mention","exceed","say","the"});
          toTest.setProbabilities(new Double[]{0.13605912332,0.066563234345,0.03275912314,0.028823899932,0.0245242343});
          String str = toTest.toString();
          assertEquals(test,str);
    }
    
}
