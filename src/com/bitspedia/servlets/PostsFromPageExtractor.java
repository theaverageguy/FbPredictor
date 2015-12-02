package com.bitspedia.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Sequence;

public class PostsFromPageExtractor {

/**
 * A simple Facebook4J client which
 * illustrates how to access group feeds / posts / comments.
 * 
 * @param args
 * @throws FacebookException 
 * @throws IOException 
 * @throws InvalidFormatException 
 */
public static void main(String[] args) throws FacebookException, InvalidFormatException, IOException {
	int postcount=0;
	int likeavg=0;
	float recent1 = 0;
	int recentlikeavg=0;
	int likespredicted = 0;
	 HashMap<String,Integer> hm=new HashMap<String,Integer>();  
	 HashMap<String,Integer> hm2=new HashMap<String,Integer>(); 
	 HashMap<String,Integer> hm3=new HashMap<String,Integer>(); 
	 HashMap<String,Integer> hm5=new HashMap<String,Integer>(); 
	 HashMap<String,ArrayList<String>> hm4=new HashMap<String,ArrayList<String>>(); 
    // Generate facebook instance.
    Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
  //  facebook.setOAuthAppId("185968415080824", "8bd71a83c66a0eec5e5c8982d77de59b");
    facebook.setOAuthAppId("1641244949462734", "29782aa73ce872efaec066be4893f9df");
    // Get an access token from: 
    // https://developers.facebook.com/tools/explorer
    // Copy and paste it below.
  
    String accessTokenString = "CAACEdEose0cBAGYVrs9rt0QPpLME0RSfyn12jTBM5ZC3MzLtbpnIYii0iQ0JsZBdG2POsL36PkENHxVduLJgLLbpDCZCyt42mIDbIdWtDAX7WrEWG7ZCMgqF7sUJSeeqyHM4rQ8uWflLZBO8V5MTHZACs94O4JJu0STppV4bdjS8Aqr8LKPSZALwm2ZC6Xr0k5xjZAJ0UQyow3qmI1OZA75pNf";
    AccessToken at = new AccessToken(accessTokenString);
    // Set access token.
    facebook.setOAuthAccessToken(at); 
    
    // We're done.
    // Access group feeds.
    // You can get the group ID from:
    // https://developers.facebook.com/tools/explorer

    // Set limit to 25 feeds.
  //  facebook.postStatusMessage("Testing_fb_like_predictor_3");
 /*    ResponseList<Post> feeds = facebook.getFeed("5550296508",
            new Reading().limit(100));
              
        // For all 25 feeds...
        for (int i = 0; i < feeds.size(); i++) {
            // Get post.
            Post post = feeds.get(i);
            // Get (string) message.
            String message = post.getMessage();
       if(message!=null){
    	   String[] words = message.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
           
           // Print out the message.
System.out.println(message);
for(String m : words){
	m = m.toLowerCase();
	if(hm.containsKey(m)){
		int a = hm.get(m);
		a = a+1;
		hm.put(m,a);
	}
	else{
		hm.put(m, 1);
	}
System.out.println(m);
}
       }     
          
            	
            PagableList<Like> likes = post.getLikes();
            
            String s = post.getId();
            ResponseList<Like> l = facebook.getPostLikes(s, new Reading().limit(5000));
            System.out.println(l.size());
            likeavg = likeavg + l.size();
            postcount=postcount+1;
            System.out.println("\n\n\n\n");
            // Get more stuff...
            PagableList<Comment> comments = post.getComments();
            String date = post.getCreatedTime().toString();
            String name = post.getFrom().getName();
            String id = post.getId();
        }
        
        
        
        
         feeds = facebook.getFeed("228735667216",
                new Reading().limit(100));
                  
            // For all 25 feeds...
            for (int i = 0; i < feeds.size(); i++) {
                // Get post.
                Post post = feeds.get(i);
                // Get (string) message.
                String message = post.getMessage();
           if(message!=null){
        	   String[] words = message.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
               
               // Print out the message.
    System.out.println(message);
    for(String m : words){
    	m = m.toLowerCase();
    	if(hm.containsKey(m)){
    		int a = hm.get(m);
    		a = a+1;
    		hm.put(m,a);
    	}
    	else{
    		hm.put(m, 1);
    	}
    System.out.println(m);
    }
           }     
              
                	
                PagableList<Like> likes = post.getLikes();
                
                String s = post.getId();
                ResponseList<Like> l = facebook.getPostLikes(s, new Reading().limit(5000));
                System.out.println(l.size());
                likeavg = likeavg + l.size();
                postcount=postcount+1;
                System.out.println("\n\n\n\n");
                // Get more stuff...
                PagableList<Comment> comments = post.getComments();
                String date = post.getCreatedTime().toString();
                String name = post.getFrom().getName();
                String id = post.getId();
            }
        
        
            
            feeds = facebook.getFeed("15704546335",
                    new Reading().limit(100));
                      
                // For all 25 feeds...
                for (int i = 0; i < feeds.size(); i++) {
                    // Get post.
                    Post post = feeds.get(i);
                    // Get (string) message.
                    String message = post.getMessage();
               if(message!=null){
            	   String[] words = message.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                   
                   // Print out the message.
        System.out.println(message);
        for(String m : words){
        	m = m.toLowerCase();
        	if(hm.containsKey(m)){
        		int a = hm.get(m);
        		a = a+1;
        		hm.put(m,a);
        	}
        	else{
        		hm.put(m, 1);
        	}
        System.out.println(m);
        }
               }     
                  
                    	
                    PagableList<Like> likes = post.getLikes();
                    
                    String s = post.getId();
                    ResponseList<Like> l = facebook.getPostLikes(s, new Reading().limit(5000));
                    System.out.println(l.size());
                    likeavg = likeavg + l.size();
                    postcount=postcount+1;
                    System.out.println("\n\n\n\n");
                    // Get more stuff...
                    PagableList<Comment> comments = post.getComments();
                    String date = post.getCreatedTime().toString();
                    String name = post.getFrom().getName();
                    String id = post.getId();
                }
            
            
        
        
        
        System.out.println("Hello");
        float avg = likeavg/postcount;
        System.out.println(avg);
        
        for(String p : hm.keySet()){
        	System.out.println(p+" : "+hm.get(p));
        }
        
        
        
   */     
        
        ResponseList<Post> feed = facebook.getFeed(new Reading().limit(1000));
        
   

// For all 25 feeds...
for (int i = 0; i < feed.size(); i++) {
    // Get post.
    Post post = feed.get(i);
    String s = post.getId();
    
    String date = post.getCreatedTime().toString();
    
   PagableList<Like> likes = post.getLikes();
   ResponseList<Like> l = facebook.getPostLikes(s,new Reading().limit(1000));
   
 // int a = likes.getCount();
    // Get (string) message.
    String message = post.getMessage();
    ArrayList<String> k;

    
    
    
    
  
   
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    
    
    postcount=postcount+1;
                    // Print out the message.
    System.out.println(message);
    System.out.println(date);
   // System.out.println(likes);
 
    k = new ArrayList();
    for (Like j : l){
    	//System.out.println(j);
    	//System.out.println(j.getClass());
    	String m = j.toString();
    	m = m.substring(m.indexOf("a") + 4);
    	m = m.substring(0, m.indexOf(","));
    	k.add(m);
   if(hm.containsKey(m)){
	   int f = hm.get(m);
	   f = f+1;
	   hm.put(m,f);
	if(postcount<=10){
		hm2.put(m,f);
	}   
   }
   else{
	   hm.put(m,1);
	   if(postcount<=10){
			hm2.put(m,1);
		}
   }
    	System.out.println(m);
    	
    }
    
    
    
    
    
    
    
    if(message!=null){
 	   String[] words = message.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        
        // Print out the message.
System.out.println(message);
for(String m : words){
	m = m.toLowerCase();
	if(hm4.containsKey(m)){
		ArrayList<String> temp = hm4.get(m);
		for (String pp : k){
			if (!temp.contains(pp)){
				temp.add(pp);
			}
		}
		hm4.put(m, temp);
	}
	else{
		hm4.put(m,k);
	}
	if(hm3.containsKey(m)){
		int a = hm3.get(m);
		a = a+1;
		hm3.put(m,a);
	}
	else{
		hm3.put(m, 1);
	}
}
    }
    
    
    
    
  
    
    System.out.println("Number of Likes: "+l.size());
    likeavg=likeavg+l.size();
 //   System.out.println(a);
if(postcount==10){
	recent1 = likeavg/10;

	
}
    // Get more stuff...
    PagableList<Comment> comments = post.getComments();
   // String date = post.getCreatedTime().toString();
    String name = post.getFrom().getName();
    String id = post.getId();
}
int predictions = 0;

System.out.println("Recent Like Average : "+recent1);
ArrayList<String> semi= new ArrayList();
for(String p : hm2.keySet()){
	System.out.println(p+" : "+hm2.get(p));
	if(hm2.get(p)>=3){
		semi.add(p);
		hm5.put(p,3);
	}
}



System.out.println("Number of Posts : 10");
System.out.println("\n\n\n\n");


float total2 = likeavg/274;
System.out.println("Total Like Average : "+total2);
for(String p : hm.keySet()){
	System.out.println(p+" : "+hm.get(p));
}

System.out.println("Number of Posts : " + postcount);
System.out.println("\n\n\n\n");

LinkedHashMap<String,Integer> m = new LinkedHashMap();
 m = sortHashMapByValuesD(hm3);
for(String p : m.keySet()){
	System.out.println(p+" : "+m.get(p));
}

System.out.println("\n\n\n\n");

System.out.println(hm4.size());
hm4.remove("a");
hm4.remove("an");
hm4.remove("the");
hm4.remove("this");
hm4.remove("that");
hm4.remove("and");
hm4.remove("u");
hm4.remove("it");
hm4.remove("was");
hm4.remove("p");
hm4.remove("just");
hm4.remove("coz");
hm4.remove("have");
hm4.remove("so");
hm4.remove("or");
hm4.remove("d");
hm4.remove("i");
hm4.remove("o");
hm4.remove("r");
hm4.remove("is");


System.out.println(hm4.size());
System.out.println("\n\n\n\n");

//String sentence[] = {"SMCS","was","awesome","I","love","my","school","."};
//String sentence[] = {"hi"};

String sentence[] = {"OMG","Yash","Sherry","got","selected","congratulations"};
for(String f : sentence){
	f = f.toLowerCase();
	ArrayList<String> temp = hm4.get(f);
	if(temp!=null){
	for (String f2 : temp){
		if(hm5.containsKey(f2)){
			int i = hm5.get(f2);
			i = i+1;
			hm5.put(f2,i);
		}
		else{
			hm5.put(f2,1);
		}
	}
	}
}
int finalcount=0;
for(String s : hm5.keySet()){
	
	System.out.println(s+" : "+hm5.get(s));
	if(hm5.get(s)>=4)
		finalcount=finalcount+1;
}

finalcount = finalcount/2;
if(finalcount<8)finalcount=finalcount+2;
if(finalcount>=45)finalcount=finalcount-3;
System.out.println("Predicted Likes : "+finalcount);
    }



public static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
	   List mapKeys = new ArrayList(passedMap.keySet());
	   List mapValues = new ArrayList(passedMap.values());
	   Collections.sort(mapValues);
	   Collections.sort(mapKeys);

	   LinkedHashMap sortedMap = new LinkedHashMap();

	   Iterator valueIt = mapValues.iterator();
	   while (valueIt.hasNext()) {
	       Object val = valueIt.next();
	       Iterator keyIt = mapKeys.iterator();

	       while (keyIt.hasNext()) {
	           Object key = keyIt.next();
	           String comp1 = passedMap.get(key).toString();
	           String comp2 = val.toString();

	           if (comp1.equals(comp2)){
	               passedMap.remove(key);
	               mapKeys.remove(key);
	               sortedMap.put((String)key, (Integer)val);
	               break;
	           }

	       }

	   }
	   return sortedMap;
	}











}