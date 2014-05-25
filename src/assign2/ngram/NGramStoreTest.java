package assign2.ngram;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class NGramStoreTest {
	
	private NGramMap nGramMap;
	private NGramContainer nGramContainer;
	
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
		nGramMap = new NGramStore();
	}

	/**
	 * Add the nGramContainer
	 */
	@Test
	public void testAddNGram() {
		nGramMap.addNGram(nGramContainer);
	}

	/**
	 * 1.Add the context of "be or not to" in nGramContainer,
	 *   and then check if the context of "be or not to" is not null.
	 * 2.Remove the context of "be or not to" in nGramContainer,
	 *   and then check if the context of "be or not to" is null.
	 */
	@Test
	public void testRemoveNGram() {
		nGramMap.addNGram(nGramContainer);
		Assert.assertNotNull(nGramMap.getNGram("be or not to"));
		nGramMap.removeNGram("be or not to");
		Assert.assertNull(nGramMap.getNGram("be or not to"));
	}

	/**
	 * Add the context of "be or not to" in nGramContainer,
	 * Use getNGram to check if the context of "be or not" is null.
	 * Use getNGram to check if the context of "be or not to" is not null.
	 */
	@Test
	public void testGetNGram() {
		nGramMap.addNGram(nGramContainer);
		NGramContainer result = nGramMap.getNGram("be or not");
		Assert.assertNull(result);
		result = nGramMap.getNGram("be or not to");
		Assert.assertNotNull(result);
		
	}

	/**
	 * 1.Use getNGramsFromService to check if context of "be or not" doesn't return results,
	 * 2.Use getNGramsFromService to check if context of "be or not to" return results,
	 * @throws NGramException 
	 * 
	 */
	@Test
	public void testGetNGramsFromService() throws NGramException {
		boolean result = nGramMap.getNGramsFromService("be or not", 4);
		Assert.assertTrue(result);
		result = nGramMap.getNGramsFromService("be or not to", 3);
		Assert.assertTrue(result);
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
                    assertTrue("obj + interface = class",(NumObjectClassFields+NumInterfaceFields)==NumNGramStoreClassFields);
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
