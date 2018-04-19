package matlabjava10;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabExecutionException;
import com.mathworks.engine.MatlabSyntaxException;

public class MatlabJava10_lib {
	Future<MatlabEngine> eng;
	MatlabEngine ml;
	private double data[][];
	
	public MatlabJava10_lib(double[][] data) {
		this.data = data;
		this.eng = MatlabEngine.startMatlabAsync();
		try {
			ml = eng.get();
			ml.putVariableAsync("data", data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//https://jp.mathworks.com/help/matlab/ref/mpower.html
	//https://jp.mathworks.com/help/econ/create-and-modify-markov-chain-model-objects.html
	//https://jp.mathworks.com/help/econ/visualize-markov-chain-structure-and-evolution.html
	public double [][] getTransition(double n, double[] initial) {
		double[][] outputs = null;
		try {
			ml.putVariableAsync("data", data);
			ml.putVariableAsync("n", n);
			ml.putVariableAsync("initial", initial);
			ml.eval("c = data ^ n;");
			//推移確率行列の可視化(有向グラフ)
			ml.eval("mc = dtmc(c);");
			ml.eval("graphplot(mc,'ColorEdges',true);");
			ml.eval("saveas(gcf,'graphplot(dtmc).png');");
			ml.eval("pause(5);");
			//推移確率行列の可視化(Heat Map)
			ml.eval("imagesc(c);");
			ml.eval("colormap(jet);");
			ml.eval("colorbar;");
			ml.eval("axis square");
			ml.eval("h = gca;");
			ml.eval("h.XTick = 1:9;");
			ml.eval("h.YTick = 1:9;");
			ml.eval("title 'Transition Matrix Heatmap';");
			ml.eval("saveas(gcf,'imagesc(c).png');");
			ml.eval("pause(5);");
			//定常分布のシミュレーション
			ml.eval("mc0 = dtmc(data);");
			ml.eval("numSteps = 5;");
			ml.eval("pi0 = initial;");
			//ml.eval("pi0 = [0.25 0.25 0.25 0.25];");
			ml.eval("pi_n = redistribute(mc0,numSteps,'X0',pi0);");
			ml.eval("distplot(mc0,pi_n);");
			ml.eval("saveas(gcf,'distplot(mc0).png');");
			ml.eval("pause(5);");
			//定常分布
			ml.eval("distplot(mc0,pi_n,'Type','histogram','FrameRate',1);");
			ml.eval("saveas(gcf,'framerate.png');");
			ml.eval("pause(5);");
			Future<double[][]> futureEval_power = ml.getVariableAsync("c");
			outputs = futureEval_power.get();
		} catch (MatlabExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CancellationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputs;
	}


}
