# Jogo de Inverter palavras emJava
## Descrição
Este e um jogo de inverter palavras em Java em modo texto. O jogador digita uma frase e o programa a inverte, exibindo a frase invertida na tela, tendo tambem alteraçoes de frases, eliminar e outras funçoes.

## Funcionalidades
- ✅ Inverter a ordem das palavras em uma frase
- ✅ Armazenar histórico de frases invertidas usando Deque
- ✅ Desfazer a última inversão realizada
- ✅ Inverter as letras de cada palavra individualmente
- ✅ Visualizar frases inseridas em ordem alfabética
- ✅ Limite de tamanho no histórico (máximo 5 frases)

## Estruturas de Dados Utilizadas
- **Deque**: Para implementar a pilha de histórico (LIFO)
- **List**: Para armazenar frases originais
- **ArrayDeque**: Para operações de inversão eficientes

## Como Executar
```bash
javac WordInverterGame.java
java WordInverterGame
