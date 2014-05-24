/**
 * 
 */
package assign2.ngram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;

/**
 * supports a collection of ngrams, each held in an object of type NGramContainer
 * 
 * @author Chou,Shu-Hung(n7622236), Weiwei Nong(n8742600)
 */
public class NGramStore implements NGramMap {
	public static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 
	private Map<String,NGramContainer> map= new HashMap<String,NGramContainer>();
	private NGramNode nGramNode;
	private boolean isAnyPredictions = false;
	/**
	 * Add an ngram to the Map. 
	 * If the context does not exist in the Map, the entry is added.
	 * If the context exists in the Map, then the associated ngram is updated (and thus overwritten). 
	 * 
	 * @param ngram - ngram to be added 
	 * @author Weiwei Nong(n8742600)
	 */
	@Override 
	public void addNGram(NGramContainer ngram) {
		String nContext = ngram.getContext();
		if(!map.containsKey(nContext))
			map.put(nContext, ngram); 
		else
			map.put(nContext, ngram);	
	}

	/**
	 * 
	 * (Silently) Remove an ngram from the Map. 
	 * If the context does not exist in the Map, the entry is not removed, but no status is returned. 
	 * We guarantee that the entry no longer exists
	 * If the context exists in the Map, then the associated ngram is removed. 
	 * 
	 * @param context - context string for ngram to be removed
	 * @author Weiwei Nong(n8742600)
	 */
	@Override
	public void removeNGram(String context) {
		if(map.containsKey(context) && map.size() != 0 && context != null){
			map.remove(context);
		}
	}

	/**
	 * 
	 * Find the NGram associated with the context if it exists in the Map. 
	 * Return null if the context is not a key in the Map. 
	 * 
	 * @param context
	 * @return NGramContainer associated with the context or null 
	 * @author Weiwei Nong(n8742600)
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
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@SuppressWarnings("null")
	@Override
	public boolean getNGramsFromService(String context, int maxResults)
			throws NGramException {
		//initialize the NgramService
		NgramServiceFactory factory = NgramServiceFactory.newInstance(NGramStore.Key);
		if (factory==null) 
			throw new NGramException("NGram Service unavailable");
		if(context ==null || context.isEmpty() || context == ""){
			return false;
		}
		GenerationService service = factory.newGenerationService();
		TokenSet tokenSet = service.generate(NGramStore.Key, "bing-body/2013-12/3", context, maxResults, null);
		//converts type list to array
		List<String> listOfWords = tokenSet.getWords();
		String[] words = new String[listOfWords.size()];
		listOfWords.toArray(words);
		if (listOfWords.size() == 0 || listOfWords.isEmpty() || context.isEmpty() || context == ""){
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
	 * Get array of phrase,cut it up based on the commas, return phrase array
	 * 
	 * @param context - the context for the ngram search 
	 * 
	 * @return phrase array
	 * @throws NGramException if there is invalid input
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public String[] parseInput(String context) throws NGramException { 	
		if(context == "" || context == null || context.isEmpty()){
			throw new NGramException("Please enter the context you would like to search");	
		}else{
			String[] phrase=context.split(",");
			String[] words;
			
			String regPattern="^[a-zA-Z0-9'¡¦]*$";
			
			for(int i = 0; i < phrase.length; i++){
				words=phrase[i].split("\\s");
				for(String x:words){
					if(!x.matches(regPattern))
						throw new NGramException("Invalid phrases\n\n"
								+"ONLY ALLOW TO HAVE\n"
								+"@Upper and lower case alphabetic characters\n"
								+"@Numerics:0123456789\n"
								+"@Single quotes: ¡¥ BUT not ¡§ or `\n"
								+"@Commas: , as the phrase separator only.");	
				}
			}	
			return phrase;
		}
	}
	
	/**
	 * formats the all textual result from each NGram 
	 * 
	 * @author Chou,Shu-Hung
	 * @return String returns the specific format of result
	 */
	public String toString(){
		String strResult="";
		Set<String> set = map.keySet();
		  
	    for(Iterator<String> iter = set.iterator(); iter.hasNext();)
		{
		    String phrase = (String)iter.next();
			NGramContainer nGramStore = map.get(phrase);
			strResult+="NGram Results for Query: "+phrase+"\n\n";
			strResult+=nGramStore.toString()+"\n";
		}
//		for(int i=0; i < this.predictions.length;i++){
//			strResult+=this.context+" | "+this.predictions[i]+" : "+ this.probabilities[i]+"\n";
//		}
		return strResult;
	}
}
