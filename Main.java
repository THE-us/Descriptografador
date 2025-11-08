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
	private static int iteracoes = 0;

	public static String [] decifrar (String str)
	{
		return decifrarRecursivo (str, new ArrayList<String> ());
	}

	// Tenta decifrar recursivamente, removendo palavras longas quando necessário
	private static String [] decifrarRecursivo (String str, List<String> removidas)
	{
		String palavras [] = str.toLowerCase ().replaceAll(",", "").split (" ");

		if (palavras.length < 3)
		{
			System.out.println ("\nNenhuma solução encontrada. Restaram poucas palavras: ");
			for (String r : removidas) System.out.print (r + " ");
			System.out.println ();
			return null;
		}

		System.out.println ("Frase: '" + str + "' Tam:" + palavras.length);

		String resposta [][] = new String [palavras.length][];

		for (int x = 0; x < palavras.length; x++)
		{
			resposta [x] = estipular (palavras [x]);

			/*for (int y = 0; y < resposta [x].length; y++)
				System.out.print ("-"  + resposta [x][y]);*/
		}

		Alfabeto alfa = new Alfabeto ();

		System.out.print ("\nPensando");
		List<String []> achados = iterar (palavras, resposta, alfa, 0);
		System.out.print ("\n\nPensou por " + iteracoes + " iteracoes\n\n");

		if (!achados.isEmpty ())
		{
			System.out.println ("\n\n\nRespostas encontradas:");
			for (String [] solucao : achados)
			{
				for (String s : solucao) System.out.print (s + " ");
				System.out.println ();
			}

			if (!removidas.isEmpty())
			{
				System.out.println ("\nPalavras removidas até encontrar:");
				for (String r : removidas) System.out.print (r + " ");
				System.out.println ();
			}

			// Gerando uma sugestão
			List<String> frases = new ArrayList<>();
				int tamanho_max = 0;

				for (String[] solucao : achados)
				{
					String frase = String.join (" ", solucao);
					frases.add (frase);
					if (frase.length () > tamanho_max) tamanho_max = frase.length ();
				}

				StringBuilder sugestao = new StringBuilder ();

				for (int pos = 0; pos < tamanho_max; pos++)
				{
					Map<Character, Integer> contagem = new HashMap<>();
					int total = 0;

					for (String frase : frases)
					{
						if (pos < frase.length ())
						{
							char c = frase.charAt (pos);
							contagem.put (c, contagem.getOrDefault (c, 0) + 1);
							total++;
						}
					}

					if (total == 0)
					{
						sugestao.append ('?');
						continue;
					}

					List<Map.Entry<Character, Integer>> ordenado = new ArrayList<>(contagem.entrySet());
					ordenado.sort ((a, b) -> b.getValue().compareTo (a.getValue()));

					char mais_usado = ordenado.get (0).getKey ();
					int freq1 = ordenado.get (0).getValue ();
					int freq2 = ordenado.size () > 1 ? ordenado.get (1).getValue () : 0;

					double p1 = (double) freq1 / total;
					double p2 = (double) freq2 / total;

					if (p1 > 0.5)
					{
						sugestao.append (Character.toUpperCase (mais_usado));
					}
					else if (p1 > p2 + 0.2) // 20% de ganho para este
					{
						sugestao.append (Character.toLowerCase (mais_usado));
					}
					else
					{
						sugestao.append ('_');
					}
				}

				System.out.println ("\nSugestão:");
				System.out.println (sugestao.toString ());

			return achados.get (0);
		}
		else
		{
			System.out.println ("\n\n\t\tNenhuma solução encontrada nesta tentativa.\n\n");

			int maior = 0, indice_maior = 0;
			for (int i = 0; i < palavras.length; i++)
			{
				if (palavras [i].length () > maior)
				{
					maior = palavras [i].length ();
					indice_maior = i;
				}
			}

			String removida = palavras [indice_maior];
			removidas.add (removida);

			StringBuilder nova_frase = new StringBuilder ();
			for (int i = 0; i < palavras.length; i++)
				if (i != indice_maior) nova_frase.append (palavras [i]).append (" ");

			return decifrarRecursivo (nova_frase.toString ().trim (), removidas);
		}
	}

/** * Ok, aqui a lógica da coisa fica muito confusa. 
 * Para cada linha das possibilidades, queremos adicionar elas no alfa até parar.
 * Se chegarmos ao final sem parar, funcionou. 
 */ 
// Retorna todas as combinações válidas possíveis
	public static List<String []> iterar (String fonte [], String possibilidades [][], Alfabeto alfa, int x)
	{
		List<String []> resultados = new ArrayList<> ();

		iteracoes++;
		if (iteracoes%10000 == 0) 
		{
			System.out.print ("."); 

			if (iteracoes >= (1000000))
			{
				System.out.println ("\n\nO programa pensou de mais: Provavelmente há algo errado nessa frase.\nSe não tiver, considere enviar um pedaço menor dela, só por garantia!\n\n");

				System.exit (0);
			}
		}

		if (x == fonte.length)
		{
			resultados.add (new String [0]);
			return resultados;
		}

		for (int y = 0; y < possibilidades [x].length; y++)
		{
			Alfabeto copia = new Alfabeto ();
			copia.letra = Arrays.copyOf (alfa.letra, alfa.letra.length);

			if (copia.PodeProcessar (fonte [x], possibilidades [x][y]))
			{
				List<String []> subresultados = iterar (fonte, possibilidades, copia, x + 1);

				for (String [] sub : subresultados)
				{
					String [] combinado = new String[sub.length + 1];
					combinado[0] = possibilidades [x][y];
					for (int i = 0; i < sub.length; i++)
						combinado [i + 1] = sub [i];
					resultados.add (combinado);
				}
			}
		}

		return resultados;
	}

	public static String [] estipular (String str)
	{
		System.out.println ("\nTamanho de '" + str + "': " + str.length ());
		String resposta = "";

		String possibilidades [] = LerArquivo.lerLinhas ("words_s" + str.length () + ".txt");

		for (int x = 0; x < possibilidades.length; x++)
		{
			if ((new Alfabeto ()).PodeProcessar (str, possibilidades [x]))
				resposta += possibilidades [x] + ";";
		}

		return resposta.split (";");
	}
}



