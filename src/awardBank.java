import java.io.Serializable;

/**
 * Desc: Class Used for Award Object and its attributes. Each of these objects gets further 
 * 		 stored in an ArrayList(Fucntion used for that: allFuncrtions.java/updateAwardBank()).
 * 		 Attributes include:
 * 		 1) Award Name
 * 		 2) Award Image URL 
 *
 */
public class awardBank implements Serializable {

	private String awardName;
	private String ImageLink;
	
	//Constructor of Award Creation
	public awardBank(String name, String link){
		awardName = name;
		ImageLink = link;
	}
	
	/*The following functions are used for access due to security. The variable members
	 * are private in this class and therefore we will use Getters and Setters. */
	
	//GETTER FUNCTION
	public String getName(){
		return this.awardName; 
	}
	
	//GETTER FUNCTION
	public String getLink(){
		return this.ImageLink; 
	}
	
	//SETTER FUNCTIOn
	public void setName(String name){
		this.awardName = name;
	}
	
	//SETTER FUNCTIOn
	public void setLink(String link){
		this.ImageLink = link;
	}

}
