package matlabjava10;

import java.util.Arrays;

public class MatlabJava10_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double data_transition[][] = {{ 0.25, 0.25, 0.5, 0 }, { 0, 0.25, 0.5, 0.25 }, { 0.25, 0.25, 0.25, 0.25 }, { 0.25, 0.25, 0, 0.5}};
		MatlabJava10_lib mlib = new MatlabJava10_lib(data_transition);
		
		double n = 2.0;
		double [][] transition = mlib.getTransition(n);
		System.out.println("Transition("+ n + ") = "+Arrays.deepToString(transition));
	}

}
