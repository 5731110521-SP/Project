public class ScoreParsingException extends Exception{

	private int errorType;
	
	public ScoreParsingException(int errorType){
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		String excep = "";
		if(errorType == 0) excep = "No record score";
		else if(errorType ==  1) excep = "Wrong record format";
		return excep;
	}
}
