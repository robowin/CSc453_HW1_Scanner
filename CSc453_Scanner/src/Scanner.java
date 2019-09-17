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
      return this.tokenType + ": " + this.tokenVal + " ";
    }
  }
  
  private Token currentToken = null;

  public Token extractToken(StringBuilder stream){
	  TokenType thisTokenType = null;
	  String tokenValue = "";
	  char charInput = stream.charAt(0);
	  
	  if (charInput == '>') {
		  thisTokenType = TokenType.GT;
		  tokenValue = charInput + "";
	  } else if (charInput == '<') {
		  thisTokenType = TokenType.LT; 
		  tokenValue = charInput + "";
	  } else if (currentToken != null && currentToken.tokenType == TokenType.GT && charInput == '=') {
		  thisTokenType = TokenType.GTE;
		  tokenValue = currentToken.tokenVal + charInput;
	  } else if (Character.isDigit(charInput)) {
		  thisTokenType = TokenType.NUM;
		  tokenValue = charInput + "";
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
	  } else {
		  System.err.println("Invaild Input is found");
	  }
	  currentToken = new Token(thisTokenType, tokenValue);
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
	  String result = "";
	  while(!arg.isEmpty()) {
		  Token nextToken = extractToken(new StringBuilder(arg));
		  if (nextToken != null) {
			  result += nextToken.toString();
		  }
		  arg = arg.substring(1);
	  }
	  System.out.println(result);
	  return result;
  }

}
