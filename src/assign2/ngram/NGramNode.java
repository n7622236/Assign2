package assign2.ngram;

import java.text.DecimalFormat;


/**
 * The NGramContainer interface specifies a container to hold ngram results
 * holding the context phrase, a list of predicted words, and a corresponding list of probabilities 
 * implement interface assign2.ngram.NGramContainer.java
 * 
 * @author Chou,Shu-Hung(n7622236)
 */
public class NGramNode implements NGramContainer {
	private String[] words;
	private String[] predictions;
	private Double[] probabilities;
	private String context;
	
    /**
     * Constructor 1
     * initialize NGramNode
     * 
     * @param words - array of words in order that make up the context
     * @param predictions - array of next words in the phrase as predicted by the model
     * @param probabilities - corresponding probabilities of context prediction w.r.t. model
     * 
     * @exception NGramException - if words is null or empty or contains at least one empty or null string
     * @exception NGramException - if predictions is null or empty or contains at least one empty or null string
     * @exception NGramException - if probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0
	 * @exception NGramException - if predictions.length is different from probabilities.length
	 * 
	 * @author Chou,Shu-Hung
    */
	public NGramNode(String[] words, String[] predictions, Double[] probabilities) throws NGramException{
		if(isWordEmptyOrNull(words)){
			throw new NGramException("Words is null or empty");
		}else if (isPredictionEmptyOrNull(predictions)){
			throw new NGramException("Predictions is null or empty");
		}else if (isProbabilityEmptyOrNull(probabilities)){
			throw new NGramException("Invalid probabilities");
		}else if(predictions.length != probabilities.length){
			throw new NGramException("Predictions.length is different from probabilities.length");
		}else{
			this.words=words;
			this.predictions=predictions;
			this.probabilities=probabilities;	
		}
	}
	
	/**
	 * 
	 * Constructor 2
	 * Initialize NGramNode
	 * 
	 * 
	 * @param String context - string containing the context phrase
	 * @param String[] predictions - array of next words in the phrase as predicted by the model
	 * @param Double[] probabilities - corresponding probabilities of context>prediction w.r.t. model
	 *
	 * @exception NGramException - if context is null or empty
     * @exception NGramException - if predictions is null or empty or contains at least one empty or null string
     * @exception NGramException - if probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0
	 * @exception NGramException - if predictions.length is different from probabilities.length
	 *
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public NGramNode(String context, String[] predictions, Double[] probabilities) throws NGramException{
		if(isContextEmptyOrNull(context)){
			throw new NGramException("Context is null or empty");
		}else if (isPredictionEmptyOrNull(predictions)){
			throw new NGramException("Predictions is null or empty");
		}else if (isProbabilityEmptyOrNull(probabilities)){
			throw new NGramException("Invalid probabilities");
		}else if(predictions.length != probabilities.length){
			throw new NGramException("Predictions.length is different from probabilities.length");
		}else{	
			DecimalFormat df=new DecimalFormat(NGramContainer.DecFormat);
			Double[] formatProb=new Double[probabilities.length];
			int probIndex=0;
			for(Double dfProb : probabilities){
				formatProb[probIndex]=new Double(df.format(dfProb));
				probIndex++;
			}
			
			this.context = context;
			this.predictions = predictions;
			this.probabilities = formatProb;
		}
	}
	
	/**
	 * 
	 * Simple getter method for the context string
	 * 
	 * @return String containing context phrase for predictions
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public String getContext() {
		return this.context;
	}

	/**
	 * 
	 * Simple setter method for the context string
	 * 
	 * @param context - single String containing context phrase for predictions
	 * @throws NGramException if context is null or empty
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public void setContext(String context) throws NGramException {
		if(isContextEmptyOrNull(context)) 
			throw new NGramException("Invalid context. Cannot be null or empty");
		else
			this.context = context;
	}

	/**
	 * 
	 * Simple setter method for the context string from multiple words
	 * 
	 * @param words - array of words in order that make up the context
	 * @throws NGramException if words is null or empty or contains at least one empty or null string
	 *
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public void setContext(String[] words) throws NGramException {
		if(isWordEmptyOrNull(words))
			throw new NGramException("Invalid words. Cannot be null or empty");
		else
			this.words = words;
	}

	/**
	 * 
	 * Simple getter method for the prediction strings
	 * 
	 * @return array of alternative next words in the phrase as predicted by the model
	 * 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public String[] getPredictions() {
		return this.predictions;
	}

	/**
	 * 
	 * Simple setter method for the predictions string array
	 * 
	 * @param predictions - next word in the phrase as predicted by the model
	 * @throws NGramException if predictions is null or empty or contains at least one empty or null string
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public void setPredictions(String[] predictions) throws NGramException {
		if(isPredictionEmptyOrNull(predictions))
			throw new NGramException("Invalid predictions");
		else
			this.predictions = predictions;
	}

	/**
	 * 
	 * Simple getter method for the probabilities
	 * 
	 * @return array of probabilities of context>prediction w.r.t. model
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public Double[] getProbabilities() {
		return this.probabilities;
	}

	/**
	 * 
	 * Simple setter method for the probabilities 
	 * 
	 * @param probabilities - array of probabilities of context>prediction w.r.t. model
	 * @throws NGramException if probabilities null or contains at least one  entry which is null , zero, negative or greater than 1.0
 	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public void setProbabilities(Double[] probabilities) throws NGramException {
		if(isProbabilityEmptyOrNull(probabilities))
			throw new NGramException("Invalid probabilities");
		else
			this.probabilities = probabilities;
	}
	
	/**
	 * formats the textual result 
	 * 
	 * @author Chou,Shu-Hung
	 * @return String returns the specific format of result
	 */
	@Override
	public String toString(){
		String strResult="";
		for(int i=0; i < this.predictions.length;i++)
			strResult+=this.context+" | "+this.predictions[i]+" : "+ this.probabilities[i]+"\n";
		return strResult;
	}
	
	/**
	 * check whether array of words is empty or null
	 *
	 * @param String[] words
	 * @return true - word is empty or null
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public boolean isWordEmptyOrNull(String[] words){
		boolean isWordEmptyOrNull = false;
		for(String word: words){
			if(word == null || word == "")
				isWordEmptyOrNull = true;
		}
		return isWordEmptyOrNull;
	}
	
	/**
	 * check whether array of predictions is empty or null
	 * 
	 * @param String[] predictions
	 * @return true - prediction is empty or null
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public boolean isPredictionEmptyOrNull(String[] predictions){
		boolean isPredictionEmptyOrNull = false;
		for(String prediction: predictions){
			if(prediction == null || prediction == "")
				isPredictionEmptyOrNull = true;
		}
		return isPredictionEmptyOrNull;
	}
	
	/**
	 * check whether array of probability is empty or null
	 * 
	 * @param Double[] probabilities
	 * @return true - probability is empty or null
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public boolean isProbabilityEmptyOrNull(Double[] probabilities){
		boolean isProbablityEmptyOrNull = false;
		for(Double probability: probabilities){
			if(probability == null || probability <= 0 || probability > 1)
				isProbablityEmptyOrNull = true;
		}
		return isProbablityEmptyOrNull;
	}
	
	/**
	 * check whether context is empty or null
	 *
	 * @param String context
	 * @return true - context is empty or null
	 * @author Chou,Shu-Hung
	 */
	public boolean isContextEmptyOrNull(String context){
		return (context == null || context == "");
	}	
}
