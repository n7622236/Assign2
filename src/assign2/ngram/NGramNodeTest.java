package assign2.ngram;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NGramNodeTest {
	
	private NGramContainer nGramContainer;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	/**
     * @author WEIWEI NONG
     *
     */
     
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
	}

	/**
	 * Set the context of "be",
	 * get the value of context, and check if it is "be".
	 * @throws NGramException
	 */
	@Test
	public void testGetContext() throws NGramException {
		String context = nGramContainer.getContext();
		Assert.assertEquals("be or not to", context);
	}

	/**
	 * Set the context of "be" or null respectively, 
	 * and check if it is null. If yes, throw NGramException.
	 * @throws NGramException
	 */
	@Test
	public void testSetContextString() throws NGramException {
		expectedEx.expect(NGramException.class);
		expectedEx.expectMessage("Invalid context");
		nGramContainer.setContext("");
		nGramContainer.setContext("be");
	}

	/**
	 * 1.Set the words as valid strings.
	 * 2.Set the words as the strings with null.
	 * Throw NGramException if it meet the 2nd condition,
	 * @throws NGramException
	 */
	@Test
	public void testSetContextStringArray() throws NGramException {
		String[] words = {"be"};
		nGramContainer.setContext(words);
		
		expectedEx.expect(NGramException.class);
		expectedEx.expectMessage("Invalid words");
		words = new String[]{"be", "", "or"};
		nGramContainer.setContext(words);
		
		words = new String[]{""};
		nGramContainer.setContext(words);
		
		words = new String[]{"be", "or", ""};
		nGramContainer.setContext(words);
	}

	/**
	 * Get the array of predictions
     * 1.check if the lenth of predictions is valid
	 * 2.check if the value of predictions is valid
	 */
	@Test
	public void testGetPredictions() {
		String[] predictions = nGramContainer.getPredictions();
		Assert.assertEquals(5, predictions.length);
		Assert.assertEquals("be", predictions[0]);
		Assert.assertEquals("mention", predictions[1]);
		Assert.assertEquals("exceed", predictions[2]);
		Assert.assertEquals("say", predictions[3]);
		Assert.assertEquals("the", predictions[4]);
	}

	/**
	 * 1.Set the array of predictions valid value
	 * 2.Set null character to the middle char in the predictions
	 * 3.Set null character to the last char in the predictions
	 * 4.Set null to the array of predictions
	 * Throw NGramException if the array include null
	 * @throws NGramException
	 */
	@Test
	public void testSetPredictions() throws NGramException {
		String[] predictions = {"is", "the", "same"};
		nGramContainer.setPredictions(predictions);
		
		predictions = new String[]{"is", "", "same"};
		expectedEx.expect(NGramException.class);
		expectedEx.expectMessage("Invalid predictions");
		nGramContainer.setPredictions(predictions);
		
		predictions = new String[]{"is", "the", ""};
		nGramContainer.setPredictions(predictions);
		
		predictions = new String[]{"is", "the", null};
		nGramContainer.setPredictions(predictions);
	}

	/**
	 * Use getProbabilities method get the array probabilities,
	 * Check if the length and value of probabilities is correct 
	 */
	@Test
	public void testGetProbabilities() {
		Double[] probabilities = nGramContainer.getProbabilities();
		Assert.assertEquals(5, probabilities.length);
		Assert.assertEquals(0.136059, probabilities[0]);
		Assert.assertEquals(0.066563, probabilities[1]);
		Assert.assertEquals(0.032759, probabilities[2]);
		Assert.assertEquals(0.028824, probabilities[3]);
		Assert.assertEquals(0.024524, probabilities[4]);
	}

	/**
	 * 1.Set probabilities as valid, minus, >1, and null respectively.
	 * Throw NGramException if probabilities is >1, minus and null.
	 * @throws NGramException
	 */
	@Test
	public void testSetProbabilities() throws NGramException {
		Double[] probabilities = {0.22345, 0.994, 0.223, 0.224};
		nGramContainer.setProbabilities(probabilities);
		
		probabilities = new Double[]{0.22345, -0.994, 0.223, 0.224};
		expectedEx.expect(NGramException.class);
		expectedEx.expectMessage("Invalid probabilities");
		nGramContainer.setProbabilities(probabilities);
		
		probabilities = new Double[]{0.22345, 1.994, 0.223, 0.224};
		nGramContainer.setProbabilities(probabilities);
		
		probabilities = new Double[]{0.22345, null, 0.223, 0.224};
		nGramContainer.setProbabilities(probabilities);
	}
	
	/**
	 * 1.Set valid context,predictions,probabilities,judge the ouput of toString.
	 * 2.set valid context,null predictions,null probabilitiesåcheck the ouput of toString.
	 * @throws NGramException
	 */
	@Test
	public void testToString() throws NGramException {
		String context = "be or not to";
		nGramContainer.setContext(context);
		String result = nGramContainer.toString();
		Assert.assertEquals("be or not to | be : 0.136059\n"+
							"be or not to | mention : 0.066563\n"+
							"be or not to | exceed : 0.032759\n"+
							"be or not to | say : 0.028824\n"+
							"be or not to | the : 0.024524\n", result);
		
		String[] predictions = new String[]{};
		Double[] probabilities = new Double[]{};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		result = nGramContainer.toString();
		Assert.assertEquals("NGram Results for Query:be or not to\n", result);
		
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
                    assertTrue("obj:"+NumObjectClassMethods+":inter:"+NumInterfaceMethods+" - 1 (toString()) = class:"+NumNGramNodeClassMethods,
                                                    (NumObjectClassMethods+NumInterfaceMethods-toStringCount)==NumNGramNodeClassMethods);
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
          NGramNode toTest=new NGramNode();
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
