import java.io.*;

public class Calculator {
	StreamTokenizer expr = new StreamTokenizer(System.in);
	/*StreamTokenizer expr=new StreamTokenizer(new BufferedReader(new InputStreamReader                    
            (System.in)));*/

	
	public void count() throws IOException{
		expr.eolIsSignificant(true);
		expr.ordinaryChar('-');
		expr.ordinaryChar('/');
		expr.nextToken();
		System.out.println(expression());
	}
	
	public double expression() throws IOException{
		double s = 0.0;
		int oper = expr.ttype;
		if (oper!='+' && oper!='-'){
			s = comp();
			oper = expr.ttype;
		}
		while (oper=='+' || oper=='-'){
			expr.nextToken();
			switch (oper){
			case '+': s+=comp(); break;
			case '-': s-=comp(); break;
			}
			oper=expr.ttype;
		}
		return s; 
	}
	
	public double comp()throws IOException{
		double il = factor();
		int oper = expr.ttype;
		while (oper=='*' || oper=='/'){
			expr.nextToken();
			switch(oper){
			case '*': il*=factor(); break;
			case '/': il/=factor(); break;
			}
			oper = expr.ttype;
		}
		return il;
	}
	
	public double factor() throws IOException{
		double w = 0;
		if (expr.ttype==StreamTokenizer.TT_NUMBER){
			w=expr.nval;
		}else{
			expr.nextToken();
			w = expression();
		}
		expr.nextToken();
		return w;
	}
}
