import java.util.*; 
import java.io.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Main
{
	public static void main (String args [])
	{
		Scanner scan = new Scanner (System.in);
		
		System.out.print
		(
			"\nDigite o código da frase do dia: "
		);
		
		Decifra.decifrar (scan.nextLine ());
		
		scan.close ();
	}
}

class LerArquivo
{
    // Lê todas as linhas do arquivo e devolve em um vetor de strings
    public static String[] lerLinhas (String caminho)
    {
        String linhas_array [] = new String[0];

        try
        {
            List<String> linhas = Files.readAllLines (Paths.get (caminho));
            linhas_array = linhas.toArray (new String[0]);
        }
        catch (IOException e)
        {
            System.out.println ("Erro ao ler arquivo: " + e.getMessage ());
        }

        return linhas_array;
    }
}

class Alfabeto
{
	public char letra [] = new char [26];

	public Alfabeto ()
	{
		for (int x = 0; x < 26; x++) letra [x] = ' ';
	}

	// Se a palavra foi processada corretamente, retorna 1
	public boolean PodeProcessar (String modelo, String palavra)
	{
		if (modelo.length () != palavra.length ()) return false;
		else
		{
			char copia [] = Arrays.copyOf (letra, letra.length);

			for (int x = 0; x < modelo.length (); x++)
			{
				char m = modelo.charAt (x);
				char p = palavra.charAt (x);

				int pos = m - 'a';

				if ((!(copia [pos] == ' ')) && (copia [pos] != p))
					return false;

				for (int z = 0; z < 26; z++)
					if ((copia [z] == p) && (z != pos))
						return false;

				copia [pos] = p;
			}

			// Se passou em tudo, grava no alfabeto real
			for (int i = 0; i < 26; i++)
				letra [i] = copia [i];

			return true;
		}
	}
}

class Decifra 
{
	// A função recebe a string cifrada unicamente em maiúsculo.
	// Em minúsculo escreva apenas o que se tem certeza.
	public static String[] decifrar (String str)
	{
		String palavras [] = str.toLowerCase ().replaceAll(",", "").split (" ");

		System.out.println ("Frase: '" + str + "' Tam:" + palavras.length);

		String resposta [][] = new String[palavras.length][];

		for (int x = 0; x < palavras.length; x++)
		{
			resposta [x] = estipular (palavras [x]);

			for (int y = 0; y < resposta [x].length; y++)
				System.out.print ("-"  + resposta [x][y]);
		}

		Alfabeto alfa = new Alfabeto ();

		// Para cada palavra criptografada
		List<String[]> achados = iterar (palavras, resposta, alfa, 0);

		if (!achados.isEmpty())
		{
			System.out.println ("\n\n\nRespostas encontradas:");
			for (String[] solucao : achados)
			{
				for (String s : solucao)
					System.out.print (s + " ");
				System.out.println ();
			}
		}
		else
		{
			System.out.println ("\nNenhuma solução encontrada.");
		}

		return achados.isEmpty() ? null : achados.get (0);
	}

	/**
	 * Ok, aqui a lógica da coisa fica muito confusa.
	 * Para cada linha das possibilidades, queremos adicionar elas no alfa até parar.
	 * Se chegarmos ao final sem parar, funcionou.
	 */
	// Retorna todas as combinações válidas possíveis
	public static List<String[]> iterar (String fonte[], String possibilidades[][], Alfabeto alfa, int x)
	{
		List<String[]> resultados = new ArrayList<>();

		if (x == fonte.length)
		{
			resultados.add (new String[0]); // fim bem-sucedido
			return resultados;
		}

		//for (int y = 0; y < x; y++) System.out.print ("-"); System.out.println (fonte [x] + "\n");

		for (int y = 0; y < possibilidades[x].length; y++)
		{
			Alfabeto copia = new Alfabeto ();
			copia.letra = Arrays.copyOf (alfa.letra, alfa.letra.length);

			if (copia.PodeProcessar (fonte[x], possibilidades[x][y]))
			{
				List<String[]> subresultados = iterar (fonte, possibilidades, copia, x + 1);

				for (String[] sub : subresultados)
				{
					String[] combinado = new String[sub.length + 1];
					combinado[0] = possibilidades[x][y];
					for (int i = 0; i < sub.length; i++)
						combinado[i + 1] = sub[i];

					resultados.add (combinado);
				}
			}
		}

		return resultados;
	}

	public static String[] estipular (String str)
	{
		System.out.println ("\nTamanho de '" + str + "': " + str.length ());
		String resposta = "";

		String possibilidades [] = LerArquivo.lerLinhas ("words_s" + str.length () + ".txt"); // Carrega todas as palavras com aquele tamanho

		for (int x = 0; x < possibilidades.length; x++)
		{
			if ((new Alfabeto ()).PodeProcessar (str, possibilidades [x]))
			{
				resposta += possibilidades [x] + ";";
			}
		}

		return resposta.split (";");
	}
}


