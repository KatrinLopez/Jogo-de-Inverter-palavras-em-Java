
package wordinvertergame;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Jogo de Inverter Palavras
 * 
 * <p>Este programa permite ao usuário inverter frases de diversas formas:
 * - Inverter a ordem das palavras mantendo as letras
 * - Inverter as letras de cada palavra mantendo a ordem
 * - Manter histórico de frases invertidas
 * - Desfazer operações
 * - Visualizar frases inseridas em ordem alfabética</p>
 * 
 * @author Katrin López
 * @version 1.0
 * @since 2025
 */
public class WordInverterGame {
    
    /**
     * Pilha para armazenar o histórico de frases invertidas
     * Usando Deque como pilha (LIFO - Last In First Out)
     */
    private Deque<String> historyStack;
    
    /**
     * Lista para armazenar todas as frases originais inseridas
     */
    private List<String> originalPhrases;
    
    /**
     * Tamanho máximo do histórico (opcional)
     */
    private static final int MAX_HISTORY_SIZE = 5;
    
    /**
     * Construtor que inicializa as estruturas de dados
     */
    public WordInverterGame() {
        this.historyStack = new ArrayDeque<>();
        this.originalPhrases = new ArrayList<>();
    }
    
    /**
     * Inverte a ordem das palavras em uma frase
     * 
     * @param phrase A frase a ser invertida
     * @return A frase com a ordem das palavras invertida
     * @throws IllegalArgumentException se a frase for nula ou vazia
     */
    public String invertWordOrder(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            throw new IllegalArgumentException("Frase não pode ser nula ou vazia");
        }
        
        String[] words = phrase.trim().split("\\s+");
        Deque<String> stack = new ArrayDeque<>();
        
        // Empilha cada palavra
        for (String word : words) {
            stack.push(word);
        }
        
        // Desempilha para inverter a ordem
        StringBuilder inverted = new StringBuilder();
        while (!stack.isEmpty()) {
            inverted.append(stack.pop());
            if (!stack.isEmpty()) {
                inverted.append(" ");
            }
        }
        
        return inverted.toString();
    }
    
    /**
     * Inverte as letras de cada palavra individualmente
     * 
     * @param phrase A frase cujas palavras terão as letras invertidas
     * @return A frase com as letras de cada palavra invertidas
     * @throws IllegalArgumentException se a frase for nula ou vazia
     */
    public String invertLettersInWords(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            throw new IllegalArgumentException("Frase não pode ser nula ou vazia");
        }
        
        String[] words = phrase.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            Deque<Character> stack = new ArrayDeque<>();
            
            // Empilha cada caractere
            for (char c : word.toCharArray()) {
                stack.push(c);
            }
            
            // Desempilha para inverter a palavra
            while (!stack.isEmpty()) {
                result.append(stack.pop());
            }
            result.append(" ");
        }
        
        return result.toString().trim();
    }
    
    /**
     * Adiciona uma frase ao histórico, mantendo o tamanho máximo
     * 
     * @param phrase A frase a ser adicionada ao histórico
     */
    public void addToHistory(String phrase) {
        historyStack.push(phrase);
        
        // Mantém o tamanho máximo da pilha
        if (historyStack.size() > MAX_HISTORY_SIZE) {
            // Remove o elemento mais antigo (base da pilha)
            Deque<String> temp = new ArrayDeque<>();
            while (historyStack.size() > MAX_HISTORY_SIZE) {
                historyStack.removeLast();
            }
        }
    }
    
    /**
     * Desfaz a última inversão realizada
     * 
     * @return A frase anterior ou mensagem de erro se não houver histórico
     */
    public String undoLastInversion() {
        if (historyStack.isEmpty()) {
            return "Nenhuma inversão para desfazer";
        }
        
        return historyStack.pop();
    }
    
    /**
     * Adiciona uma frase à lista de frases originais
     * 
     * @param phrase A frase original a ser armazenada
     */
    public void addOriginalPhrase(String phrase) {
        if (phrase != null && !phrase.trim().isEmpty()) {
            originalPhrases.add(phrase.trim());
        }
    }
    
    /**
     * Retorna as frases originais em ordem alfabética
     * 
     * @return Lista de frases originais ordenadas alfabeticamente
     */
    public List<String> getSortedPhrases() {
        return originalPhrases.stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
    }
    
    /**
     * Exibe o menu principal do jogo
     */
    private void displayMenu() {
        System.out.println("\n=== JOGO DE INVERTER PALAVRAS ===");
        System.out.println("1. Inverter uma nova frase");
        System.out.println("2. Ver frases já inseridas (ordem alfabética)");
        System.out.println("3. Desfazer última inversão");
        System.out.println("4. Inverter letras de cada palavra");
        System.out.println("5. Sair do programa");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Processa a opção de inverter uma nova frase
     * 
     * @param scanner Scanner para entrada do usuário
     */
    private void processNewPhrase(Scanner scanner) {
        System.out.print("\nDigite uma frase: ");
        scanner.nextLine(); // Limpa o buffer
        String phrase = scanner.nextLine();
        
        if (phrase.trim().isEmpty()) {
            System.out.println("Frase vazia! Tente novamente.");
            return;
        }
        
        // Armazena a frase original
        addOriginalPhrase(phrase);
        
        // Inverte a frase
        String inverted = invertWordOrder(phrase);
        
        // Adiciona ao histórico
        addToHistory(inverted);
        
        System.out.println("\nFrase original: " + phrase);
        System.out.println("Frase invertida: " + inverted);
    }
    
    /**
     * Processa a opção de inverter letras de cada palavra
     * 
     * @param scanner Scanner para entrada do usuário
     */
    private void processInvertLetters(Scanner scanner) {
        System.out.print("\nDigite uma frase para inverter as letras: ");
        scanner.nextLine(); // Limpa o buffer
        String phrase = scanner.nextLine();
        
        if (phrase.trim().isEmpty()) {
            System.out.println("Frase vazia! Tente novamente.");
            return;
        }
        
        // Armazena a frase original
        addOriginalPhrase(phrase);
        
        // Inverte as letras
        String inverted = invertLettersInWords(phrase);
        
        // Adiciona ao histórico
        addToHistory(inverted);
        
        System.out.println("\nFrase original: " + phrase);
        System.out.println("Letras invertidas: " + inverted);
    }
    
    /**
     * Método principal que executa o jogo
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        WordInverterGame game = new WordInverterGame();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("Bem-vindo ao Jogo de Inverter Palavras!");
        
        while (running) {
            game.displayMenu();
            
            try {
                int option = scanner.nextInt();
                
                switch (option) {
                    case 1:
                        game.processNewPhrase(scanner);
                        break;
                        
                    case 2:
                        List<String> sortedPhrases = game.getSortedPhrases();
                        if (sortedPhrases.isEmpty()) {
                            System.out.println("\nNenhuma frase inserida ainda.");
                        } else {
                            System.out.println("\nFrases inseridas (ordem alfabética):");
                            for (int i = 0; i < sortedPhrases.size(); i++) {
                                System.out.println((i + 1) + ". " + sortedPhrases.get(i));
                            }
                        }
                        break;
                        
                    case 3:
                        String undone = game.undoLastInversion();
                        System.out.println("\nDesfazendo última inversão: " + undone);
                        break;
                        
                    case 4:
                        game.processInvertLetters(scanner);
                        break;
                        
                    case 5:
                        running = false;
                        System.out.println("\nObrigado por jogar! Até mais!");
                        break;
                        
                    default:
                        System.out.println("\nOpção inválida! Tente novamente.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("\nErro: Por favor, digite um número válido.");
                scanner.nextLine(); // Limpa o buffer
            } catch (Exception e) {
                System.out.println("\nErro: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
