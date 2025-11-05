def separarPorTamanho (arquivo_entrada):
    with open (arquivo_entrada, "r", encoding="utf-8") as f:
        palavras = f.read ().splitlines ()

    palavras_por_tamanho = {}

    for palavra in palavras:
        tamanho = len (palavra.strip ())
        if tamanho > 0:
            if tamanho not in palavras_por_tamanho:
                palavras_por_tamanho[tamanho] = []
            palavras_por_tamanho[tamanho].append (palavra)

    for tamanho in sorted (palavras_por_tamanho.keys ()):
        nome_arquivo = f"words_s{tamanho}.txt"
        with open (nome_arquivo, "w", encoding="utf-8") as f:
            f.write ("\n".join (palavras_por_tamanho[tamanho]))

    return None

# Exemplo de uso
separarPorTamanho ("google-10000-english-no-swears.txt")
