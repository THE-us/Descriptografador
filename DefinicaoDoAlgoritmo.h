
/* -- Take 2
 * 
 * ### FUNÇÃO DECIFRAR
 * Pegar uma frase criptografada do usuário.
 * Transformar em um vetor de strings separadas por espaços.
 * Criar outros dois vetores, ambos com a mesma quantia de palavras.
 * Vetor 1: Vão ser o tamanho de cada palavra.
 * Vetor 2: Vão ser as posições daquelas palavaras.
 * 
 * Ordenar ambos de acordo com o tamanho das palavras, menor pro maior.
 * De forma que possa se pesquisar elementos pelo seu tamanho, chegando na palavra, apartir do vetor de endereço.
 * 
 * Criar um vetor de translation, 26 de tamanho, para cada letra do alfabeto, o vetor começando vazio.
 * 
 * Joga cada palavra individualmente na função Estipular.
 * A função retorna um vetor de palavras possíveis da localização, 
 * 	assim como o vetor de translation daquela tentativa.
 * 
 * Passa por todos os Vetores de translation, verificando um a um, comparando com todos os outros,
 * 	para ver se eles condizem (ou podem conviver).
 *  Se em algum momento, o código perceba que aquele vetor entra em conflito com todas as palavras de um nível,
 *   ele é apagado, e o próximo vetor passa a ser verificado.
 * O objetivo com isso é filtrar, e conseguir apenas os vetores e apenas as palavras condizentes.
 * Com sorte sendo apenas uma. Ou não, retornando um vetor de todas as possibilidades.
 * 
 * 
 * 
 * ### FUNÇÃO ESTIPULAR
 * Se o tamanho da palavra for um, retornar um vetor resposta com 'i' e 'a' no lugar.
 * 
 * Se o tamanho da palavra for dois, novamente retornar um vetor pequeno de valores que podem ser, feito manualmente.
 * 
 * Se for maior, retornar o vetor resposta de todas as possibilidades.
 * 
*/

/** -- Exemplo de Execução
 * 
 * ### I WILL WIN
 * ### A BACC BAD
 * 
 * Decifrar: 
 * Vetor Tamanho: 1 4 3
 * Vetor Posição: 1 2 3
 * 
 * Após ordenar...
 * Vetor Tamanho: 1 3 4
 * Vetor Posição: 1 3 2
 * 
 * Vetor Translation (Vazio):
 * 
 * 
 * 
 * ### Estipular (Tam: 1, Pos: 1, Pal: A, Translation: (Vazio))
 * 
 * Retorna Translation 1:
 * {
 * 		A: i
 * }
 * 
 * Retorna Translation 2:
 * {
 * 		A: a
 * }
 * 
 * 
 * 
 * ### Estipular (Tam: 3, Pos: 3, Pal: BAD, Translation: {A:i})
 * Palavra: BiD
 * 
 * Lista De possibilidades:
A: aid, ail, aim, air
B: bib, bid, big, bin, bio, bit
C: cis, cig
D: did, die, dig, dim, din, dip, dis, dit
E: NONE
F: fib, fid, fie, fig, fin, fir, fit, fix
G: gib, gig, gin, gip, git
H: hid, hie, him, hip, his, hit
I: NONE
J: jib, jig, jin
K: kid, kin, kip, kit
L: lib, lid, lie, lip, lit
M: mid, mix
N: nib, nil, nim, nip, nit, nix
O: oil, oik
P: pia, pic, pie, pig, pin, pip, pit, pix
Q: qis
R: rib, rid, rig, rim, rip
S: sib, sic, sin, sip, sir, sis, sit, six
T: tic, tie, tin, tip, tis, tit
U: NONE
V: via, vid, vie, vim, vis
W: wig, win, wit, wiz
X: xis
Y: yin, yip
Z: zig, zip, zit
 *
 * Sabemos que a primeira e terceira letra é diferente, então:
 *
A: aid, ail, aim, air
B: bid, big, bin, bio, bit
C: cis, cig
D: die, dig, dim, din, dip, dis, dit
F: fib, fid, fie, fig, fin, fir, fit, fix
G: gib, gin, gip, git
H: hid, hie, him, hip, his, hit
J: jib, jig, jin
K: kid, kin, kip, kit
L: lib, lid, lie, lip, lit
M: mid, mix
N: nib, nil, nim, nip, nit, nix
O: oil, oik
P: pia, pic, pie, pig, pin, pit, pix
Q: qis
R: rib, rid, rig, rim, rip
S: sib, sic, sin, sip, sir, sit, six
T: tic, tie, tin, tip, tis
V: via, vid, vie, vim, vis
W: wig, win, wit, wiz
X: xis
Y: yin, yip
Z: zig, zip, zit
 * 
 *
 * 
 * ### Estipular (Tam: 3, Pos: 3, Pal: BAD, Translation: {A:a})
 * Palavra: BaD
 * 
 * Lista de Possibilidades:
A: NONE
B: bad, bag, ban, bar, bat, bay
C: cab, cad, cam, can, cap, car, cat, caw, cay
D: dab, dam, day, dap
E: ear, eat
F: fad, fan, far, fat, fax, fay
G: gab, gal, gam, gap, gas, gat, gay
H: had, hag, ham, hap, has, hat, hay, haw
I: NONE
J: jab, jag, jam, jar, jaw, jay
K: NONE
L: lab, lad, lag, lam, lap, law, lay, lax
M: mad, mag, man, map, mat, maw, may
N: nab, nag, nap, nay, nan
O: oaf, oak, oar, oat
P: pad, pal, pan, pap, par, pat, paw, pay
Q: qat
R: rag, ram, ran, rap, rat, raw, ray
S: sad, sag, sap, sat, saw, say
T: tab, tad, tag, tan, tap, tar, taw, tax, tay
U: NONE
V: van, vat, vax
W: wad, wag, wan, war, was, wax, way, waw
X: NONE
Y: yak, yam, yap, yaw
Z: zag, zap, zax
 *
 *
 * 
 * Toda essa enorme lista de palavras são todas as possibilidades de serem a palavra de três letras. 
 * Outras não podem ir no lugar.
 * Cada uma das duas listas de possibilidades dependem do fato de qual é a letra da palavra de uma letra ('i' ou 'a')
 * Todas as palavras de cada lista de possibilidades então é transformada em um vetor de translation e passado para o próximo nível.
 * 
 * #### Estipular (Tam: 4, Pos: 2, Pal: BACC, Translation: {A:i, B:a, D: d}) // Para BAD: aid
 * Palavra: aiCC
 * 
 * Lista de Possibilidades: Não existe. Não existe nenhuma palavra que se enquadre a este Translation.
 * Logo, BAD: aid é desclassificado.
 * Assim como todas as palavras que começam em 'aiX'
 * 
 * Lista de Todas as Possibilidades: 
 * bill, bitt, birr, dill, fill, fizz, 
 * gill, hiss, hill, jizz, kiss, kill, 
 * mitt, mill, miss, piss, rill, sill, 
 * tizz, till, will, diss
 * 
 * Para toda palavra, qual seria sua equivalente de três letras?
 * 
 * Lista de Todas as Possibilidades: 
 * bill, bitt, birr: (bid, big, bin, bio, bit)
 * diss, dill: (die, dig, dim, din, dip, dis, dit)
 * fill, fizz: (fib, fid, fie, fig, fin, fir, fit, fix)
 * gill: (gab, gal, gam, gap, gas, gat, gay)
 * hiss, hill: (had, hag, ham, hap, has, hat, hay, haw)
 * jizz: (jib, jig, jin) 
 * kiss, kill: (kid, kin, kip, kit)
 * mitt, mill, miss: (mid, mix)
 * piss: (pia, pic, pie, pig, pin, pit, pix)
 * rill: (rib, rid, rig, rim, rip)
 * sill: (sib, sic, sin, sip, sir, sit, six)
 * tizz, till: (tic, tie, tin, tip, tis)
 * will: (wig, win, wit, wiz)
 * 
 * 
 * 
 * ### Estipular o mesmo só que para A:a
 * 
 * Lista de Palavras: 
 * ball, bass, call, carr, fall, faff, 
 * gall, gaff, hall, jazz, lass, mall, 
 * mass, matt, pall, pass, razz, sass, 
 * tall, wall, watt
 * 
 * Lista de Todas as Possibilidades:
 * ball, bass: (bad, bag, ban, bar, bat, bay)
 * call, carr: (cab, cad, cam, can, cap, car, cat, caw, cay)
 * fall, faff: (fad, fan, far, fat, fax, fay)
 * gall, gaff: (gab, gal, gam, gap, gas, gat, gay)
 * hall: (had, hag, ham, hap, has, hat, hay, haw)
 * jazz: (jab, jag, jam, jar, jaw, jay)
 * lass: (lab, lad, lag, lam, lap, law, lay, lax)
 * mall, mass, matt: (mad, mag, man, map, mat, maw, may)
 * pall, pass: (pad, pal, pan, pap, par, pat, paw, pay)
 * razz: (rag, ram, ran, rap, rat, raw, ray)
 * sass: (sad, sag, sap, sat, saw, say)
 * tall: (tab, tad, tag, tan, tap, tar, taw, tax, tay)
 * wall, watt: (wad, wag, wan, war, was, wax, way, waw)
 * 
 * O programa então iria imprimir todas as possibilidades de frases para o usuário, sendo elas:
 * 
 * ### Para A:I
i bill bid
i bill big
i bill bin
i bill bio
i bill bit
i bitt bid
i bitt big
i bitt bin
i bitt bio
i bitt bit
i birr bid
i birr big
i birr bin
i birr bio
i birr bit
i diss die
i diss dig
i diss dim
i diss din
i diss dip
i diss dis
i diss dit
i dill die
i dill dig
i dill dim
i dill din
i dill dip
i dill dis
i dill dit
i fill fib
i fill fid
i fill fie
i fill fig
i fill fin
i fill fir
i fill fit
i fill fix
i fizz fib
i fizz fid
i fizz fie
i fizz fig
i fizz fin
i fizz fir
i fizz fit
i fizz fix
i gill gab
i gill gal
i gill gam
i gill gap
i gill gas
i gill gat
i gill gay
i hiss had
i hiss hag
i hiss ham
i hiss hap
i hiss has
i hiss hat
i hiss hay
i hiss haw
i hill had
i hill hag
i hill ham
i hill hap
i hill has
i hill hat
i hill hay
i hill haw
i jizz jib
i jizz jig
i jizz jin
i kiss kid
i kiss kin
i kiss kip
i kiss kit
i kill kid
i kill kin
i kill kip
i kill kit
i mitt mid
i mitt mix
i mill mid
i mill mix
i miss mid
i miss mix
i piss pia
i piss pic
i piss pie
i piss pig
i piss pin
i piss pit
i piss pix
i rill rib
i rill rid
i rill rig
i rill rim
i rill rip
i sill sib
i sill sic
i sill sin
i sill sip
i sill sir
i sill sit
i sill six
i tizz tic
i tizz tie
i tizz tin
i tizz tip
i tizz tis
i till tic
i till tie
i till tin
i till tip
i till tis
i will wig
i will win <<<
i will wit
i will wiz
 * 
 *
 * ### Para A:a
a ball bad
a ball bag
a ball ban
a ball bar
a ball bat
a ball bay
a bass bad
a bass bag
a bass ban
a bass bar
a bass bat
a bass bay
a call cab
a call cad
a call cam
a call can
a call cap
a call car
a call cat
a call caw
a call cay
a carr cab
a carr cad
a carr cam
a carr can
a carr cap
a carr car
a carr cat
a carr caw
a carr cay
a fall fad
a fall fan
a fall far
a fall fat
a fall fax
a fall fay
a faff fad
a faff fan
a faff far
a faff fat
a faff fax
a faff fay
a gall gab
a gall gal
a gall gam
a gall gap
a gall gas
a gall gat
a gall gay
a gaff gab
a gaff gal
a gaff gam
a gaff gap
a gaff gas
a gaff gat
a gaff gay
a hall had
a hall hag
a hall ham
a hall hap
a hall has
a hall hat
a hall hay
a hall haw
a jazz jab
a jazz jag
a jazz jam
a jazz jar
a jazz jaw
a jazz jay
a lass lab
a lass lad
a lass lag
a lass lam
a lass lap
a lass law
a lass lay
a lass lax
a mall mad
a mall mag
a mall man
a mall map
a mall mat
a mall maw
a mall may
a mass mad
a mass mag
a mass man
a mass map
a mass mat
a mass maw
a mass may
a matt mad
a matt mag
a matt man
a matt map
a matt mat
a matt maw
a matt may
a pall pad
a pall pal
a pall pan
a pall pap
a pall par
a pall pat
a pall paw
a pall pay
a pass pad
a pass pal
a pass pan
a pass pap
a pass par
a pass pat
a pass paw
a pass pay
a razz rag
a razz ram
a razz ran
a razz rap
a razz rat
a razz raw
a razz ray
a sass sad
a sass sag
a sass sap
a sass sat
a sass saw
a sass say
a tall tab
a tall tad
a tall tag
a tall tan
a tall tap
a tall tar
a tall taw
a tall tax
a tall tay
a wall wad
a wall wag
a wall wan
a wall war
a wall was
a wall wax
a wall way
a wall waw
a watt wad
a watt wag
a watt wan
a watt war
a watt was
a watt wax
a watt way
a watt waw
 * 
 * 
 * 
 * Conclusão: São muitas contas, e muitas respostas para jogar no usuário.
 * Podemos presumir que a quantia de respostas possíveis irão diminuir com mais dados.
 * Mas a quantia de cálculos ainda é colossal para esta aproximação, mesmo que sim, chegou ao resultado.
 *  */







