package FuzzyLogic;

public class ExpressionParser {

public static double eval(String exp){
    int bracketCounter = 0;
    int operatorIndex = -1;

    for(int i=0; i<exp.length(); i++){
        char c = exp.charAt(i);
        if(c == '(') bracketCounter++;
        else if(c == ')') bracketCounter--;
        else if((c == '+' || c == '-') && bracketCounter == 0){
            operatorIndex = i;
            break;
        }
        else if((c == '*' || c == '/') && bracketCounter == 0 && operatorIndex < 0){
            operatorIndex = i;
        }
    }
    if(operatorIndex < 0){
        exp = exp.trim();
        if(exp.charAt(0) == '(' && exp.charAt(exp.length()-1) == ')')
            return eval(exp.substring(1, exp.length()-1));
        else
            return Double.parseDouble(exp);
    }
    else{
        switch(exp.charAt(operatorIndex)){
            case '+':
                return eval(exp.substring(0, operatorIndex)) + eval(exp.substring(operatorIndex+1));
            case '-':
                return eval(exp.substring(0, operatorIndex)) - eval(exp.substring(operatorIndex+1));
            case '*':
                return eval(exp.substring(0, operatorIndex)) * eval(exp.substring(operatorIndex+1));
            case '/':
                return eval(exp.substring(0, operatorIndex)) / eval(exp.substring(operatorIndex+1));
        }
    }
    return 0;
}
}
