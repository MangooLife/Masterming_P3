package main.java;

import main.java.resources.PropertyManager;

import java.util.*;

public abstract class Game {


    private PropertyManager propertyManager = new PropertyManager();
    protected int nbLife = propertyManager.getNbLife();
    protected int isDeveloperMode = propertyManager.getIsDeveloperMode();
    protected int nbCase = propertyManager.getNbCase();
    protected int secretCombinaison[] = new int[nbCase];
    protected int guessCombinaison[] = new int[nbCase];
    protected int computerSecret[] = generateASecret();
    protected int rightNumber;


    /**
     * <b>Method void startAGameByIsMethodChoice :</b> start a game by the player method choice
     * @param methodChoice chosen by the player
     */
    public void startAGameByIsMethodChoice(int methodChoice){
        switch (methodChoice){
            case 1:
                System.out.println("*** Mode Challenger ***");
                playerGuessTheSecret();
                break;
            case 2:
                System.out.println("*** Mode défenseur ***");
                computerGuessTheSecret();
                break;
            case 3:
                System.out.println("*** Mode duel ***");
                computerVsPlayer();
                break;
            default:
                System.out.println("*** Jeu par défaut: ***");
                playerGuessTheSecret();
        }
    }

    /**
     * <b>Method generateASecret :</b> return a secret code
     * @return (list[Integer]) secretCombinaison
     */
    public int[] generateASecret(){
        Random random = new Random();
        for(int i=0; i<nbCase; i++){
            Integer computerSecret = random.nextInt(9);
            secretCombinaison[i] = computerSecret;
        }
        return secretCombinaison;
    }

    /**
     * <b>Method propositionOfThePlayer :</b> Player guess the secret code
     * @return guessCombinaison : code guessed by the player
     */
    public int[] propositionOfThePlayer(){
        System.out.print("Veuillez taper une suite de "+nbCase+" chiffres entre 0 et 9: ");
        String number = "";
        do{
            try{
                Scanner scProposition = new Scanner(System.in);
                number = scProposition.nextLine();
                if(number.length() != nbCase){
                    System.out.println("Veuillez fournir un code à "+nbCase+" chiffres");
                }
            } catch(Exception e){
                System.out.println("Veuillez fournir un code à "+nbCase+" chiffres");
            }
        }while(number.length()!=nbCase);

        for(int i=0; i<number.length(); i++){
            guessCombinaison[i] = Integer.valueOf(String.valueOf(number.charAt(i)));
        }
        System.out.print("Proposition : ");
        showValueOfTab(guessCombinaison);
        return guessCombinaison;
    }

    /**
     * <b>Method haveYouWin :</b> return success message if rightNumber is equal
     * to 4 else game over for the player
     * @param rightNumber found by the player
     */
    public void haveYouWin(int rightNumber){
        if(rightNumber == nbCase){
            System.out.println("Vous avez gagné contre l'ordinateur. Bravo !");
        } else {
            System.out.print("Vous avez perdu contre l'ordinateur.");
            System.out.println("Le code secret est ");
            showValueOfTab(secretCombinaison);
            System.out.println();
        }
    }

    /**
     * <b>Method isDeveloperMode :</b> Give the secret Code if the game run in developer mode
     */
    public void isDeveloperMode(){
        if(isDeveloperMode==1){
            System.out.print("(Combinaison secrète : ");
            showValueOfTab(secretCombinaison);
            System.out.println(")");
        }
    }

    /**
     * <b>Method showValueOfTab :</b> Show a tab combinaison
     * @param tab
     */
    public void showValueOfTab(int[] tab){
        for(int i=0; i<tab.length; i++) {
            System.out.print(tab[i]);
        }
    }

    /**
     * <b>Method playerGuessTheSecret :</b> Mode challenger
     */
    public abstract void playerGuessTheSecret();

    /**
     * <b>Method computerGuessTheSecret :</b> Mode defenseur
     */
    public abstract void computerGuessTheSecret();

    /**
     * <b>Method computerVsPlayer :</b> Mode Player vs Computer
     */
    public abstract void computerVsPlayer();
}
