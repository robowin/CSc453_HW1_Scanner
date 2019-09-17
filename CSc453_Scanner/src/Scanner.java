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

  public Token extractToken(StringBuilder stream){
	  String arg = stream.toString();
	  char firstChar = arg.charAt(0);
	  String firstStringChar = arg.substring(0, 1);
	  Token firstToken = null;
	  
	  System.out.println("1st = " + firstChar);
	  
	  if (firstChar == '>') { 
		  firstToken = new Token(TokenType.GT, firstStringChar);
		  if (arg.length() > 1) {
			  char secondChar = arg.charAt(1);
			  if (secondChar == '=') {
				  String firstTwoStringChar = arg.substring(0,2);
				  firstToken = new Token(TokenType.GTE, firstTwoStringChar);
			  }
		  }
	  } else if (firstChar == '<') {
		  firstToken = new Token(TokenType.LT, firstStringChar);
		  char secondChar = arg.charAt(1);
		  if (arg.length() > 1) {
			  if (secondChar == '=') {
				  String firstTwoStringChar = arg.substring(0,2);
				  firstToken = new Token(TokenType.LTE, firstTwoStringChar);
			  }
		  }
	  } else if (Character.isDigit(firstChar)) {
		  firstToken = new Token(TokenType.NUM, firstStringChar);
	  } else if (firstChar == '+') {
		  firstToken = new Token(TokenType.PLUS, firstStringChar);
	  } else if (firstChar == '-') {
		  firstToken = new Token(TokenType.MINUS, firstStringChar);
	  } else if (firstChar == '*') {
		  firstToken = new Token(TokenType.MUL, firstStringChar);
	  } else if (firstChar == '/') {
		  firstToken = new Token(TokenType.DIV, firstStringChar);
	  } else if (firstChar == ' ' || firstChar == '\n' || firstChar == '	') {
	  } else {
		  Error tokenError = new Error("this token can't be accept");
		  System.err.println(tokenError);
	  }
	 return firstToken;
  }

public String extractTokens(String arg){
	  String result = "";
	  while (!arg.isEmpty()) {
		  Token nextToken = extractToken(new StringBuilder(arg));
		  if (nextToken != null) {
			  result += nextToken.toString();
		  }
		  arg = arg.substring(1);
	  }
    return result;
  }

}
