package ilion.util.json;

public class JsonString {

	  String message;
	  String attributeOne;
	  String attributeTwo;


	  public JsonString() {
	  }

	  public JsonString(String message) {
	    this.message = message;
	  }

	  public JsonString(String message, String attributeOne) {
	    this.message = message;
	    this.attributeOne = attributeOne;
	  }

	  public JsonString(String message, String attributeOne, String attributeTwo) {
	    this.message = message;
	    this.attributeOne = attributeOne;
	    this.attributeTwo = attributeTwo;
	  }


	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	  public String getAttributeOne() {
	    return attributeOne;
	  }

	  public void setAttributeOne(String attributeOne) {
	    this.attributeOne = attributeOne;
	  }

	  public String getAttributeTwo() {
	    return attributeTwo;
	  }

	  public void setAttributeTwo(String attributeTwo) {
	    this.attributeTwo = attributeTwo;
	  }
	}
