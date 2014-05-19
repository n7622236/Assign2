/**
 * 
 */
package assign2.ngram;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assign2.examples.ngram.SimpleNGramGenerator;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;

/**
 * @author Chou,Shu-Hung
 *
 */
public class NGramStore implements NGramMap {
	public static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 
	private Map<String,NGramContainer> map= new HashMap<String,NGramContainer>();
	private NGramNode nGramNode;
	private boolean isAnyPredictions = false;
	/**
	 * 
	 * Add an ngram to the Map. 
	 * If the context does not exist in the Map, the entry is added.
	 * If the context exists in the Map, then the associated ngram is updated (and thus overwritten). 
	 * 
	 * @param ngram - ngram to be added 
	 */
	@Override 
	public void addNGram(NGramContainer ngram) {
		String nContext = ngram.getContext();
		if(!map.containsKey(nContext))
			map.put(nContext, ngram); 
		else{
			map.put(nContext, ngram);	
		}
	}

	/**
	 * 
	 * (Silently) Remove an ngram from the Map. 
	 * If the context does not exist in the Map, the entry is not removed, but no status is returned. 
	 * We guarantee that the entry no longer exists
	 * If the context exists in the Map, then the associated ngram is removed. 
	 * 
	 * @param context - context string for ngram to be removed
	 */
	@Override
	public void removeNGram(String context) {
		if(map.containsKey(context))
			map.put(context, null);
	}

	/**
	 * 
	 * Find the NGram associated with the context if it exists in the Map. 
	 * Return null if the context is not a key in the Map. 
	 * 
	 * @param context
	 * @return NGramContainer associated with the context or null 
	 * @author Chou,Shu-Hung
	 */
	@Override
	public NGramContainer getNGram(String context) {
		return map.containsKey(context)? map.get(context): null;
	}

	/**
	 * 
	 * Get the top maxResults ngrams returned from the service.  
	 * 
	 * @param context - the context for the ngram search 
	 * @param maxResults - the maximum number of 
	 * @return true and store the NGram in the Map 
	 * 			if the service returns at least one result
	 * @return false and do not store the bare context if the service returns no predictions
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be 
	 * created. 
	 */
	@Override
	public boolean getNGramsFromService(String context, int maxResults)
			throws NGramException {
		//initialize the NgramService
		NgramServiceFactory factory = NgramServiceFactory.newInstance(NGramStore.Key);
		if (factory==null) 
			throw new NGramException("NGram Service unavailable");
		
		GenerationService service = factory.newGenerationService();
		TokenSet tokenSet = service.generate(NGramStore.Key, "bing-body/2013-12/3", context, maxResults, null);
		//converts type list to array
		List<String> listOfWords = tokenSet.getWords();
		String[] words = new String[listOfWords.size()];
		listOfWords.toArray(words);
		if (listOfWords.size() == 0 || context.isEmpty() || context == ""){
			isAnyPredictions = false;
		}else{
			List<Double> logProbs = tokenSet.getProbabilities();
			List<Double> listOfProbs = new ArrayList<Double>();
			for (Double x : logProbs) {
				listOfProbs.add(Math.pow(10.0,x));
			}
			Double[] probs =new Double[listOfProbs.size()];
			listOfProbs.toArray(probs);
			this.nGramNode=new NGramNode(context,words,probs);
			this.addNGram(this.nGramNode);
			isAnyPredictions = true;
		}
		return isAnyPredictions;
		
	}
	/**
	 * 
	 * Get phrase array by exminating the format
	 * 
	 * @param context - the context for the ngram search 
	 * 
	 * @return phrase array
	 * @throws NGramException if there is invalid input
	 */
	public String[] parseInput(String context) throws NGramException { 
		//check the format
		
		//if illegal, throw new NGramException
		//if ok, cut it up based on the commas, return phrase array
		String[] phrase=context.split(",");
		String[] words;
		
		String regPattern="^[a-zA-Z0-9'¡¦]*$";
		
		for(int i = 0; i < phrase.length; i++){
			words=phrase[i].split("\\s");
			for(String x:words){
				if(x.matches(regPattern)){
					
				}else{
					throw new NGramException("invalid phrases");	
				}
			}
		}	
		return phrase;
	}
	 
}
