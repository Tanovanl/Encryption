import java.util.*;
public class EncryptionProgram {

    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;
    private char[] secretLetters;

    public EncryptionProgram(){
        scanner = new Scanner(System.in);
        random = new Random();
        list = new ArrayList();
        shuffledList = new ArrayList();
        character = ' ';

        newKey();
        askQuestion();
    }
    private void askQuestion(){
        while(true){
            System.out.println("******************************************************************");
            System.out.println("Wat wil je doen?");
            System.out.println("(N)ieuweKey, (K)rijgKey, (B)estaande key, (E)ncrypt, (D)ecrypt, (V)erlaat programma");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch(response){
                case 'N':
                    newKey();
                    break;
                case 'K':
                    getKey();
                    break;
                case 'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'V':
                    quit();
                    break;
                case 'B':
                    enterKey();
                    break;

                default:
                    System.out.println("Dit is geen optie!");
            }
        }
    }

    private void newKey(){

        character = ' ';
        list.clear();
        shuffledList.clear();

        for(int i=32;i < 127; i++){
            list.add(Character.valueOf(character));
            character++;
        }

        shuffledList = new ArrayList(list);
        Collections.shuffle(shuffledList);
        System.out.println("Een nieuwe key is gegenereert!");
    }

    private void enterKey(){
        shuffledList = new ArrayList();
        System.out.println("Vul een bestaande key in, 1 foutje kan de message helemaal veranderen dus doe het zorgzaam!");
        String key = scanner.nextLine();
        if(key.length() != 95){
            System.out.println("De key is niet lang genoeg (95)");

        } else {
            for (int i = 0; i < key.length(); i++) {
                shuffledList.add(key.charAt(i));
            }
            System.out.println("De key is ingesteld!");
            System.out.println(shuffledList);
        }

    }

    private void getKey(){
        System.out.println("Key: ");
        for(Character x : list){
            System.out.print(x);
        }
        System.out.println();
        for(Character x : shuffledList){
            System.out.print(x);
        }
        System.out.println();
    }

    private void encrypt(){
        System.out.println("Voer hier een bericht in dat je wilt encrypten!");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for(int i = 0; i < letters.length; i++){
            for(int j = 0; j < list.size(); j++){
                if(letters[i] == list.get(j)){
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted: ");
        for(char x : letters){
            System.out.print(x);
        }
        System.out.println();
    }
    private void decrypt(){
        System.out.println("Voer hier een bericht in dat je wilt decrypten!");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for(int i = 0; i < letters.length; i++){
            for(int j = 0; j < shuffledList.size(); j++){
                if(letters[i] == shuffledList.get(j)){
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted: ");
        for(char x : letters){
            System.out.print(x);
        }
        System.out.println();
    }

    private void quit(){
        System.out.println("Bye bye");
        System.exit(0);
    }
}
