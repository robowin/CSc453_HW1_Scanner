public class Scanner{
  enum TokenType{
      NUM, PLUS, MINUS, MUL, DIV, LT, LTE, GT, GTE;
  }

  class Token{
    TokenType tokenType;
    String tokenVal;
    public Token(TokenType tokenType, String tokenVal){
      this.tokenType = tokenType;
      this.tokenVal = tokenVal;
    }
    public String toString(){
      return "|" + this.tokenType + ": " + this.tokenVal + "|";
    }
  }

  public Token extractToken(StringBuilder stream){
	  TokenType thisTokenType = null;
	  String tokenValue = "";
	  char charInput = stream.charAt(0);
	  
	  if (charInput == '>') {
		  if (stream.length() > 1 && stream.charAt(1) == '=') {
			  thisTokenType = TokenType.GTE;
			  tokenValue = "" + charInput + '=';
			  stream = stream.deleteCharAt(0);
		  } else {
			  thisTokenType = TokenType.GT;
			  tokenValue = charInput + "";
		  }
	  } else if (charInput == '<') {
		  if (stream.length() > 1 && stream.charAt(1) == '=') {
			  thisTokenType = TokenType.LTE;
			  tokenValue = "" + charInput + '=';
			  stream = stream.deleteCharAt(0);
		  } else {
			  thisTokenType = TokenType.LT;
			  tokenValue = charInput + "";
		  }
	  } else if (Character.isDigit(charInput)) {
		  thisTokenType = TokenType.NUM;
		  tokenValue = charInput + "";
		  
		  int index;
		  for (index = 0;index < stream.length() - 1;index++) {
			  if (!Character.isDigit(stream.charAt(index + 1))) {
				  break;
			  }
			  tokenValue += stream.charAt(index + 1);
		  }
		  stream = stream.delete(0, index);
	  } else if (charInput == '+') {
		  thisTokenType = TokenType.PLUS;
		  tokenValue = charInput + "";
	  } else if (charInput == '-') {
		  thisTokenType = TokenType.MINUS;
		  tokenValue = charInput + "";
	  } else if (charInput == '*') {
		  thisTokenType = TokenType.MUL;
		  tokenValue = charInput + "";
	  } else if (charInput == '/') {
		  thisTokenType = TokenType.DIV;
		  tokenValue = charInput + "";
	  } else if (charInput == ' ' || charInput == '\n' || charInput == '\t') {
		  int index;
		  for (index = 0;index < stream.length() - 1;index++) {
			  if (stream.charAt(index + 1) != ' ' && stream.charAt(index + 1) != '\n' && stream.charAt(index + 1) != '\t') {
				  break;
			  }
		  }
		  stream = stream.delete(0, index + 1);
		  return null;
	  } else {
		  System.err.println("Invaild Input " + stream.charAt(0) + " is found");
		  stream = stream.deleteCharAt(0);
		  return null;
	  }
	  stream = stream.deleteCharAt(0);
	  return new Token(thisTokenType, tokenValue);
  }

  public String extractTokens(String arg){
    /* TODO #1: Finish this function to iterate over all tokens in the input string.

       Pseudo code:
       String extractTokens(String arg):
         String result= â€œâ€�;
         while(arg is not empty)
            Token nextToken = extractToken(arg)
            result += nextToken.toString()
         return result
    */
	  String result = "";
	  StringBuilder sbArg = new StringBuilder(arg);
	  while(sbArg.length() != 0) {
		  Token nextToken = extractToken(sbArg);
		  if (nextToken != null) {
			  result += nextToken.toString();
		  }
	  }
	  return result;
  }

}
