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
  
  private int curPos;
  private Token currentToken;

  public Token extractToken(StringBuilder stream){
	  TokenType thisTokenType = null;
	  String tokenValue = "";
	  char charInput = stream.charAt(curPos);
	  
	  if (charInput == '>') {
		  if (curPos < stream.length() - 1 && stream.charAt(curPos + 1) == '=') {
			  thisTokenType = TokenType.GTE;
			  tokenValue = "" + charInput + '=';
			  curPos++;
		  } else {
			  thisTokenType = TokenType.GT;
			  tokenValue = charInput + "";
		  }
	  } else if (charInput == '<') {
		  if (curPos < stream.length() - 1 && stream.charAt(curPos + 1) == '=') {
			  thisTokenType = TokenType.LTE;
			  tokenValue = "" + charInput + '=';
			  curPos++;
		  } else {
			  thisTokenType = TokenType.LT;
			  tokenValue = charInput + "";
		  }
	  } else if (Character.isDigit(charInput)) {
		  thisTokenType = TokenType.NUM;
		  tokenValue = charInput + "";
		  
		  int index;
		  for (index = curPos;index < stream.length() - 1;index++) {
			  if (!Character.isDigit(stream.charAt(index + 1))) {
				  break;
			  }
			  tokenValue += stream.charAt(index + 1);
		  }
		  curPos = index;
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
		  for (index = curPos;index < stream.length() - 1;index++) {
			  if (stream.charAt(index + 1) != ' ' && stream.charAt(index + 1) != '\n' && stream.charAt(index + 1) != '\t') {
				  break;
			  }
		  }
		  curPos = index;
		  curPos++;
		  currentToken = null;
		  return null;
	  } else {
		  System.err.println("Invaild Input " + stream.charAt(curPos) + " is found");
		  curPos++;
		  return null;
	  }
	  currentToken = new Token(thisTokenType, tokenValue);
	  curPos++;
	 return currentToken;
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
	  curPos = 0;
	  currentToken = null;
	  String result = "";
	  while(!arg.isEmpty()) {
		  Token nextToken = extractToken(new StringBuilder(arg));
		  if (nextToken != null) {
			  result += nextToken.toString();
		  }
		  arg = arg.substring(curPos);
		  curPos = 0;
	  }
	  return result;
  }

}
