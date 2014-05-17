/**
 * 
 * This file is part of the SearchSuggestion Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * SimpleNGramGenerator.java
 * assign2.examples.ngram
 * 29/04/2014
 * 
 * Original copyright notice from the ngram java wrapper follows. 
 * The supplied code is a simplified version of the Generator service 
 * example program
 * Copyright 2011 Nabeel Mukhtar 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package assign2.examples.ngram;

import java.util.ArrayList;
import java.util.List;

import assign2.ngram.NGramException;
import assign2.ngram.NGramNode;
import assign2.ngram.NGramStore;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;


public class SimpleNGramGenerator {
	
	public static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 
	public static final String DefaultContext = "My hovercraft is";

    /**
     * The main method.
     * 
     * @param args - a list of words; the whole list is included as a single context call
     * @throws NGramException 
     */
	public static void main(String[] args) throws NGramException {
		
		String context = " ";
		context = SimpleNGramGenerator.DefaultContext.trim();
		
		NGramStore ngn=new NGramStore();
		if(ngn.getNGramsFromService(context, 5)){
			System.out.println(ngn.getNGram(context).toString());
		}else{
			System.out.println("NGram Results for Query: be or not to \n"
					+ "No ngram predictions were returned.\n"
					+ "Please try another query");
		}
	}
}
