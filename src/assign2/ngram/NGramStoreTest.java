package assign2.ngram;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
/**
 * Junit test for NGramNode
 * 
 * @author Chou,Shu-Hung(n7622236), Weiwei Nong(n8742600)
 */
public class NGramStoreTest {
	private NGramMap nGramMap;
	private NGramContainer nGramContainer;
	
	/**
	 * Initiation
	 * @throws NGramException if construct NGramNode fail
	 */
	@Before
	public void setUp() throws NGramException {
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		nGramMap = new NGramStore();
	}

	/**
	 * Add the nGramContainer
	 * 
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_AddNGram() {
		nGramMap.addNGram(nGramContainer);
	}

	/**
	 * Add twice nGramContainer into NGramStore
	 * The second time to add nGramContainer,
	 * it sets same context but different predictions and probabilities
	 * if the updated predictions and probabilities is the same as the second time 
	 * the test passes.
	 * 
	 * @author Weiwei Nong(n8742600)
	 * @throws NGramException 
	 */
	@Test
	public void test_Method_AddNGram_By_UpdateData() throws NGramException {
		String context = "be or not to";
		String[] predictions = {"be", "mention", "exceed", "say", "the"};
		Double[] probabilities = {0.136059, 0.066563, 0.032759, 0.028824, 0.024524};
		nGramContainer = new NGramNode(context, predictions, probabilities);
		nGramMap.addNGram(nGramContainer);
		
		String[] updatePreds = {"be", "be", "be", "be", "be"};
		Double[] updatePros = {0.1, 0.1, 0.1, 0.1, 0.1};
		nGramContainer = new NGramNode(context, updatePreds, updatePros);
		
		nGramMap.addNGram(nGramContainer);
		String[] updatedPreds=nGramMap.getNGram(context).getPredictions();
		for(int numPreds=0; numPreds<updatePreds.length; numPreds++){
			Assert.assertEquals(updatePreds[numPreds],updatedPreds[numPreds]);
		}
		
		Double[] updatedPros=nGramMap.getNGram(context).getProbabilities();
		for(int numPros=0; numPros < updatedPros.length; numPros++){
			Assert.assertEquals(updatedPros[numPros],updatedPros[numPros]);
		}
	}
	
	/**
	 * Test RemoveNGram() by the steps below
	 * 1.Add the context of "be or not to" in nGramContainer,
	 *   and then check if the context of "be or not to" is not null.
	 * 2.Remove the context of "be or not to" in nGramContainer,
	 *   and then check if the context of "be or not to" is null.
	 *   
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_RemoveNGram() {
		nGramMap.addNGram(nGramContainer);
		Assert.assertNotNull(nGramMap.getNGram("be or not to"));
		nGramMap.removeNGram("be or not to");
		Assert.assertNull(nGramMap.getNGram("be or not to"));
	}

	/**
	 * Add the context of "be or not to" in nGramContainer,
	 * Use getNGram to check if the context of "be or not" is null.
	 * Use getNGram to check if the context of "be or not to" is not null.
	 * 
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_GetNGram() {
		nGramMap.addNGram(nGramContainer);
		NGramContainer result = nGramMap.getNGram("be or not");
		Assert.assertNull(result);
		result = nGramMap.getNGram("be or not to");
		Assert.assertNotNull(result);
	}

	/**
	 * 1.Use getNGramsFromService to check if context of "be or not" doesn't return results,
	 * 2.Use getNGramsFromService to check if context of "be or not to" return results,
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be created 
	 * @author Weiwei Nong(n8742600)
	 */
	@Test
	public void test_Method_GetNGramsFromService() throws NGramException {
		String context = "be or not";
		int MaxResults = 4;
		boolean result = nGramMap.getNGramsFromService(context, MaxResults);
		Assert.assertTrue(result);
		
		context = "be or not to";
		MaxResults = 3;
		result = nGramMap.getNGramsFromService(context, MaxResults) ;
		Assert.assertTrue(result);
	}
	
	/**
	 * test GetNGramFromService() with invalid context
	 * return false if context is null or empty
	 *
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be created 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test
	public void test_Method_GetNGramsFromService_With_InValid_Context() throws NGramException {
		String context = "";
		int MaxResults = 4;
		boolean result = nGramMap.getNGramsFromService(context, MaxResults);
		Assert.assertFalse(result);
		
		context = null;
		MaxResults = 3;
		result = nGramMap.getNGramsFromService(context, MaxResults) ;
		Assert.assertFalse(result);
	}
	
	/**
	 * test GetNGramFromService() with invalid maxResults
	 * return false if maxResults is null or empty
	 *
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be created 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test
	public void test_Method_GetNGramsFromService_With_InValid_MaxResults() throws NGramException {
		String context = "be or not to";
		int MaxResults = -1;
		boolean result = nGramMap.getNGramsFromService(context, MaxResults);
		Assert.assertFalse(result);
		
		context = null;
		MaxResults = 0;
		result = nGramMap.getNGramsFromService(context, MaxResults) ;
		Assert.assertFalse(result);
	}
	
	/**
	 * test GetNGramFromService() with invalid maxResults
	 * return false if maxResults is null or empty
	 *
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be created 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Test
	public void test_Method_ToString() throws NGramException {
		String firstContext = "ABC";
		String[] firstPreds = {"news"};
		Double[] firstProbs = {0.3};
		nGramContainer = new NGramNode(firstContext, firstPreds, firstProbs);
		nGramMap.addNGram(nGramContainer);
		
		String secContext = "DEF";
		String[] secPreds = {"HIJK"};
		Double[] secProbs = {0.1};
		nGramContainer = new NGramNode(secContext, secPreds, secProbs);
		nGramMap.addNGram(nGramContainer);
		
		String correctFormatResult = "ABC | news : 0.3\n\n"+"DEF | HIJK : 0.1\n\n";
		String result = nGramMap.toString();
		Assert.assertEquals(correctFormatResult, result);
	}
	
    /*
     * Confirm that the API spec has not been violated through the
     * addition of public fields, constructors or methods that were
     * not requested
     */
    @Test
    public void NoExtraPublicMethods() {
                    //Extends Object, implements NGramMap
                    final int toStringCount = 1;
                    final int NumObjectClassMethods = Array.getLength(Object.class.getMethods());
                    final int NumInterfaceMethods = Array.getLength(NGramMap.class.getMethods());
                    final int NumNGramStoreClassMethods = Array.getLength(NGramStore.class.getMethods());
                    assertTrue("obj:"+NumObjectClassMethods+":inter:"+NumInterfaceMethods+" - 1 (toString()) = class:"+NumNGramStoreClassMethods,
                                                    (NumObjectClassMethods+NumInterfaceMethods-toStringCount)==NumNGramStoreClassMethods);
    }
   
    @Test
    public void NoExtraPublicFields() {
    //Extends Object, implements NGramMap
                    final int NumObjectClassFields = Array.getLength(Object.class.getFields());
                    final int NumInterfaceFields = Array.getLength(NGramMap.class.getFields());
                    final int NumNGramStoreClassFields = Array.getLength(NGramStore.class.getFields());
                    assertTrue("obj "+NumObjectClassFields+ "interface"+NumInterfaceFields+" = class"+NumNGramStoreClassFields,(NumObjectClassFields+NumInterfaceFields)==NumNGramStoreClassFields);
    }
   
    @Test
    public void NoExtraPublicConstructors() {
    //Extends Object, implements NGramMap
                    final int NumObjectClassConstructors = Array.getLength(Object.class.getConstructors());
                    final int NumInterfaceConstructors = Array.getLength(NGramMap.class.getConstructors());
                    final int NumNGramStoreClassConstructors = Array.getLength(NGramStore.class.getConstructors());
                    assertTrue("obj:"+NumObjectClassConstructors+":inter:"+NumInterfaceConstructors+" = class:"+NumNGramStoreClassConstructors,
                                                    (NumObjectClassConstructors+NumInterfaceConstructors)==NumNGramStoreClassConstructors);
    }
}
