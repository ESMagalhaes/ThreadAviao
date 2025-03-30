package view;
import controller.ThreadAvioes;

public class Aeroporto {

	public static void main(String[] args) {
		ThreadAvioes[] ta = new ThreadAvioes[13];
		
		for(int i = 1; i <= 12; i ++) {
			ta[i] = new ThreadAvioes(i);
			ta[i].start();
		}

	}

}
