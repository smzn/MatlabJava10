package matlabjava10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MatlabJava10_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//double data_transition[][] = {{ 0.25, 0.25, 0.5, 0 }, { 0, 0.25, 0.5, 0.25 }, { 0.25, 0.25, 0.25, 0.25 }, { 0.25, 0.25, 0, 0.5}};
		MatlabJava10_main mmain = new MatlabJava10_main();
		double data_transition[][] = mmain.getCSV("csv/transition_all.csv", 9, 9);
		MatlabJava10_lib mlib = new MatlabJava10_lib(data_transition);
		
		//double initial[] = {0.25, 0.25, 0.25, 0.25};
		double initial[][] = mmain.getCSV("csv/initial_all.csv", 2, 9);
		double n = 20.0;
		double [][] transition = mlib.getTransition(n, initial[0]);
		System.out.println("Transition("+ n + ") = "+Arrays.deepToString(transition));
		System.out.println("Difference = "+mlib.getDifference(initial[1], transition[0]));
	}

	public double[][] getCSV(String path, int row, int column) {
		//CSVから取り込み
		double data[][] = new double[row][column];
		try {
			File f = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String[][] tmp = new String[row][column]; 	 
			String line = br.readLine();
			for (int i = 0; line != null; i++) {
				tmp[i] = line.split(",", 0);
				line = br.readLine();
			}
			br.close();

			// CSVから読み込んだ配列の中身を処理
			for(int i = 0; i < tmp.length; i++) {
				for(int j = 0; j < tmp[0].length; j++) {
					data[i][j] = Double.parseDouble(tmp[i][j]);
				}
			} 

		} catch (IOException e) {
			System.out.println(e);
		}
		//CSVから取り込みここまで
		return data;
	}

}
