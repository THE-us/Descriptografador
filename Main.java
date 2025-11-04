import java.io.*;
import java.util.*;

public class Main
{
	public static void main (String args [])
	{
		Scanner scan = new Scanner (System.in);

		
		System.out.println 
		(
			"\nDigite o código da frase do dia: " +

			"\n\nE deixe o Descriptografador fazer sua mágica." +
			
			"\n\n"
		);
		
		Decifra.decifrar (scan.nextLine ());
		
		scan.close ();
	}
}

class Decifra 
{
	// A função recebe a string cifrada unicamente em maiúsculo.
	// Em minúsculo escreva apenas o que se tem certeza.
	public static String decifrar (String str)
	{
		String palavras [] = str.split (" ");

		System.out.println (str + " Tam:" + palavras.length + " " + Arrays.toString (ordenarPorTamanho (palavras)));

		return "";
	}

	public static int [] ordenarPorTamanho (String v[])
	{
		int resp [] = new int [v.length];

		for (int x = 0; x < v.length; x++)
		{
			resp [x] = v[x].length ();
		}

		Arrays.sort (resp);

		return resp;
	}
}