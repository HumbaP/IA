package FuzzyLogic;

public class Women {
	public static String createProblem(double Score) {
		String problem="";
		int n,n2;		
		String operator;
		if(Score>=0 && Score<=70) {
			n = (int) (Math.random() * 2);
			if(n==1) {
				operator="+";
			}else operator="-";
			n = (int) (Math.random() * 10)+1;
			n2 = (int) (Math.random() * 10)+1;
			problem=n+operator+n2;
		}
		if(Score>=70 && Score<=90) {
			operator="*";
			n = (int) (Math.random() * 10)+1;
			n2 = (int) (Math.random() * 10)+1;
			problem=n+operator+n2;
		}
		if(Score>90) {
			n = (int) (Math.random() * 2);
			if(n==0) {
				operator="+";
			}else operator="-";
			n = (int) (Math.random() * 100)+1;
			n2 = (int) (Math.random() * 100)+1;
			problem=n+operator+n2;
		}
		return problem;
	}
}
