package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private final Scanner userInput = new Scanner(System.in);
    private int screenWidth = 100;
    private Player myPlayer = new Player();
    private String UP = "up";
    private String DOWN = "down";
    private String LEFT = "left";
    private String RIGHT = "right";
    private Map<String, Map<String, String>> zoneMap = new HashMap<>();
    private List<String> moveActions = List.of("move", "go", "look", "turn");
    private List<String> searchActions = List.of("examine", "inspect", "interact", "search");
    private boolean door = false;



    public Game(){

    }

    public void titleScreenSelection(){

        while (true){
            String userInputLower = userInput.nextLine().toLowerCase();
            if (userInputLower.contains("play")){
                gameSetup();
            }
            else if (userInputLower.contains("help")) {
                helpTitleMenu();
            }
            else if (userInputLower.contains("quit")) {
                System.exit(0);
            }
            else {
                System.out.println("Please enter a valid command.");
            }
        }
    }

    public void titleScreen(){
        System.out.println("###############################");
        System.out.println("# Welcome to the Escape Room! #");
        System.out.println("###############################");
        System.out.println("           - Play -            ");
        System.out.println("           - Help -            ");
        System.out.println("           - Quit -            ");
        System.out.println("  Copyright 2021 Cevans Corp   ");
        System.out.print("> ");
        titleScreenSelection();
    }

    public void helpTitleMenu(){
        System.out.println("Type your commands to do them");
        System.out.println("Use \"move\", \"go\", and \"look\" + direction to look/move around room");
        System.out.println("Use above  + \"up\", \"down\", \"left\", \"right\" to look/move around room");
        System.out.println("Use \"examine\", \"inspect\", \"interact\", and \"search\" to inspect things");
        System.out.println("Good luck and have fun!");
        titleScreenSelection();
    }

    public void helpMenu(){
        System.out.println("Type your commands to do them");
        System.out.println("Use \"move\", \"go\", and \"look\" + direction to look/move around room");
        System.out.println("Use above  + \"up\", \"down\", \"left\", \"right\" to look/move around room");
        System.out.println("Use \"examine\", \"inspect\", \"interact\", and \"search\" to inspect things");
        System.out.println("Good luck and have fun!");
    }

    public void setZoneMap(){

        // Room 'w1'
        Map<String, String> wall1Map = new HashMap<>();
        wall1Map.put("UP", "ceiling");
        wall1Map.put("DOWN", "ground");
        wall1Map.put("LEFT", "wall4");
        wall1Map.put("RIGHT", "wall2");
        this.zoneMap.put("wall1", wall1Map);

        // Room 'w2'
        Map<String, String> wall2Map = new HashMap<>();
        wall2Map.put("UP", "ceiling");
        wall2Map.put("DOWN", "ground");
        wall2Map.put("LEFT", "wall1");
        wall2Map.put("RIGHT", "wall3");
        this.zoneMap.put("wall2", wall2Map);

        // Room 'w3'
        Map<String, String> wall3Map = new HashMap<>();
        wall3Map.put("UP", "ceiling");
        wall3Map.put("DOWN", "ground");
        wall3Map.put("LEFT", "wall2");
        wall3Map.put("RIGHT", "wall4");
        this.zoneMap.put("wall3", wall3Map);

        // Room 'w4'
        Map<String, String> wall4Map = new HashMap<>();
        wall4Map.put("UP", "ceiling");
        wall4Map.put("DOWN", "ground");
        wall4Map.put("LEFT", "wall3");
        wall4Map.put("RIGHT", "wall1");
        this.zoneMap.put("wall4", wall4Map);

        // Room 'c1'
        Map<String, String> ceilingMap = new HashMap<>();
        ceilingMap.put("UP", "ceiling");
        ceilingMap.put("DOWN", "ground");
        ceilingMap.put("LEFT", "wall1");
        ceilingMap.put("RIGHT", "wall1");
        this.zoneMap.put("ceiling", ceilingMap);

        // Room 'g1'
        Map<String, String> groundMap = new HashMap<>();
        groundMap.put("UP", "ceiling");
        groundMap.put("DOWN", "ground");
        groundMap.put("LEFT", "wall1");
        groundMap.put("RIGHT", "wall1");
        this.zoneMap.put("ground", groundMap);

    }

    public void itemMenu(){
        myPlayer.printItems();
    }

    public void prompt(){
        setZoneMap();
        while (true){
            walls();
            System.out.println("================================");
            System.out.println("What would you like to do?");
            System.out.print("> ");
            String userInputLower = userInput.nextLine().toLowerCase();
            if(checkAction(userInputLower,  moveActions)){
                movePlayer();
            }
            else if (checkAction(userInputLower,  searchActions)) {
                playerExamine();
            }
            else if (userInputLower.contains("help")) {
                helpMenu();
            }
            else if (userInputLower.contains("item") || userInputLower.contains("inventory")) {
                itemMenu();
            }
            else if (userInputLower.contains("quit")) {
                System.exit(0);
            }
            else{
                System.out.println("Please enter a valid command.");
            }

        }
    }

    private boolean checkAction(String direction, List<String> actions){
        for (String action: actions){
            if (direction.contains(action)){
                return true;
            }
        }
        return false;
    }

    public void movePlayer(){
            System.out.println("================================");
            System.out.println("Where would you like to look?");
            System.out.print("> ");
            String userInputLower = userInput.nextLine().toLowerCase();
            String destination = "";

            if (userInputLower.contains(UP)){
                destination = zoneMap.get(myPlayer.getLocation()).get("UP");
                movementHandler(destination);

            }
            else if (userInputLower.contains(DOWN)) {
                destination = zoneMap.get(myPlayer.getLocation()).get("DOWN");
                movementHandler(destination);

            }
            else if (userInputLower.contains(RIGHT)) {
                destination = zoneMap.get(myPlayer.getLocation()).get("RIGHT");
                movementHandler(destination);

            }
            else if (userInputLower.contains(LEFT)) {
                destination = zoneMap.get(myPlayer.getLocation()).get("LEFT");
                movementHandler(destination);

            }

    }

    public void movementHandler(String destination){
        myPlayer.setLocation(destination);
    }

    public void playerExamine(){
        System.out.println("================================");
        System.out.println("What would you like to search?");
        System.out.print("> ");
        String userInputLower = userInput.nextLine().toLowerCase();
        if (userInputLower.contains("quit")) {
            System.exit(0);
        }

        //Wall1
        if(myPlayer.getLocation().equals("wall1")){


            //works properly
            if(userInputLower.contains("light switch")){
                System.out.println("Light switch is on!");
            }

            else if (userInputLower.contains("tv")) {
                // works properly
                if (myPlayer.checkItems("remote")){
                    System.out.println("================================");
                    System.out.println("Do you want to turn the TV on? (Yes/No)");
                    System.out.print("> ");
                    String turnTvOn = userInput.nextLine().toLowerCase();
                    // works properly
                    if(turnTvOn.contains("yes")){
                        System.out.println("================================");
                        System.out.println("The TV is now on, but theres nothing but static.");

                        while (true){
                            System.out.println("Do you want to change the channel? (Yes/No)");
                            System.out.print("> ");
                            String changeChannel = userInput.nextLine().toLowerCase();
                            // works properly
                            if(changeChannel.contains("yes")){

                                System.out.println("================================");
                                System.out.println("Please enter the a channel number");
                                System.out.print("> ");
                                String channelNumber = userInput.nextLine().toLowerCase();
                                // works properly
                                if(channelNumber.equals("235")){
                                    printChannel235();
                                }
                                // works properly
                                else if (channelNumber.equals("456")) {
                                    printChannel465();
                                }
                                // works properly
                                else if (channelNumber.equals("1234")) {
                                    printChannel1234();
                                }
                                // works properly
                                else if (channelNumber.equals("5786")) {
                                    printChannel5786();
                                }
                                // works properly
                                else if (channelNumber.equals("9379")) {
                                    printChannel9379();
                                }
                                // works properly
                                else {
                                    System.out.println("Invalid Channel");
                                }
                            }
                            // works properly
                            else{
                                System.out.println("Turned Tv off.");
                                break;
                            }
                        }

                    }
                    // works properly
                    else{
                        System.out.println("The Tv is off.");
                    }
                }
                // works properly
                else {
                    System.out.println("The Tv is off.");
                }
            }

            else if (userInputLower.contains("shoe rack")) {
                // works properly
                if(!myPlayer.checkItems("flashlight")){
                    myPlayer.addItems("flashlight");
                    System.out.println("*****You found a flashlight!*****");
                }
                // works properly
                else {
                    System.out.println("There is nothing else here to find");
                }
            }

            else if (userInputLower.contains("door")) {
                // works properly
                System.out.println("The door is locked.");

                // works properly
                if (myPlayer.checkItems("key") && !door){
                    System.out.println("================================");
                    System.out.println("Do you want to use the key on the door? (Yes/No): ");
                    System.out.print("> ");
                    String useKey = userInput.nextLine().toLowerCase();
                    if (useKey.contains("yes")){
                        door = true;
                    }

                }
                if (door){
                    System.out.println("================================");
                    System.out.println("The door is now open. Would you like to leave? (Yes/No): ");
                    System.out.print("> ");
                    String endGame = userInput.nextLine().toLowerCase();

                    if (endGame.contains("yes")){
                        System.out.println("Congrats you beat my game!");
                        System.exit(1);
                    }
                }


            }
        }

        //Wall2
        else if (myPlayer.getLocation().equals("wall2")) {

            if (userInputLower.contains("window")){
                if (myPlayer.checkItems("normal light bulb")){
                    System.out.println("There's a message on the window. It says \"Play it in Reverse\".");
                }
                //works properly
                else {
                    System.out.println("It's too dark to see out there.");
                }
            }

            else if (userInputLower.contains("bed")) {

                //works properly
                if (myPlayer.checkItems("knife")) {
                    System.out.println("There is nothing else here to find");
                }

                // works properly
                if (myPlayer.checkItems("flashlight")){
                    if (!myPlayer.checkItems("knife")){
                        if (!myPlayer.checkItems("remote")){
                            myPlayer.addItems("remote");
                            System.out.println("*****You found a remote!*****");
                        }
                        System.out.println("There is a mini safe under here.");
                        while(true){
                            System.out.println("Would you like to try and open it? (Yes/No)");
                            System.out.print("> ");
                            String trySafe = userInput.nextLine().toLowerCase();
                            // works properly
                            if (trySafe.contains("yes")){
                                System.out.println("Please enter the safe's 4 digit code:");
                                System.out.print("> ");
                                String safeCode = userInput.nextLine().toLowerCase();
                                //works properly
                                if (safeCode.equals("9379")){
                                    myPlayer.addItems("knife");
                                    System.out.println("*****You found a knife!*****");
                                }
                                // works properly
                                else {
                                    System.out.println("Incorrect code please try again");

                                }
                            }
                            if (myPlayer.checkItems("knife")){
                                break;
                            }
                        }
                    }
                }
                //works properly
                else {
                    System.out.println("'It's too dark to see under the bed.");
                }
            }
        }

        //Wall3
        else if (myPlayer.getLocation().equals("wall3")) {
            if (userInputLower.contains("computer")){
                System.out.println("================================");
                System.out.println("Please enter the password: ");
                System.out.print("> ");
                String password = userInput.nextLine().toLowerCase();

                if (password.equals("Thisisareallylongpasswordhuh?")){
                    printChannelList();
                }
                else {
                    System.out.println("Incorrect password please try again");
                }
            }

            else if (userInputLower.contains("trash")){
                if (!myPlayer.checkItems("UV light bulb")){
                    myPlayer.addItems("UV light bulb");
                    System.out.println("*****You found a UV light bulb!*****");
                }
                else {
                    System.out.println("There is nothing else here to find");
                }
            }
            // works properly
            else if (userInputLower.contains("closet")){
                if (!myPlayer.checkItems("step ladder")){
                    myPlayer.addItems("step ladder");
                    System.out.println("*****You found a step ladder!*****");
                }
                else {
                    System.out.println("There is nothing else here to find");
                }
            }
        }

        //Wall4
        else if (myPlayer.getLocation().equals("wall4")) {
            if (userInputLower.contains("painting")){
                if (myPlayer.checkItems("normal light bulb")){
                    if (!myPlayer.checkItems("record")){

                        System.out.println("There's a message on the painting. It says \"Look behind me\".");
                        System.out.println("Would you like to look behind the painting? (Yes/No):");
                        System.out.print("> ");
                        String lookBehindPainting = userInput.nextLine().toLowerCase();
                        if (lookBehindPainting.contains("yes")){
                            myPlayer.addItems("record");
                            System.out.println("*****You found a record!*****");
                        }
                    }
                    else {
                        System.out.println("There is nothing else here to find");
                    }
                }
                else {
                    System.out.println("It's just a regular painting.");
                }
            }

            else if (userInputLower.contains("record player")) {
                System.out.println("Seems to be missing a record");
                if (myPlayer.checkItems("record")){

                    System.out.println("Would you like to play the record in forward or reverse: ");
                    System.out.print("> ");
                    String forwardOrReverse = userInput.nextLine().toLowerCase();
                    if (forwardOrReverse.contains("forward")){
                        System.out.println("?huhdrowssapgonlyllaerasisihT");
                    }
                    else if (forwardOrReverse.contains("reverse")) {
                        System.out.println("Thisisareallylongpasswordhuh?");
                    }
                }
            }

            else if (userInputLower.contains("dresser")) {
                if (!myPlayer.checkItems("gloves")){
                    myPlayer.addItems("gloves");
                    System.out.println("*****You found gloves!*****");
                }
                else {
                    System.out.println("There is nothing else here to find");
                }
            }
        }

        //Ceiling
        else if (myPlayer.getLocation().equals("ceiling")) {
            if (userInputLower.contains("light")) {
                if (myPlayer.checkItems("gloves") && myPlayer.checkItems("UV light bulb") && myPlayer.checkItems("step ladder")){
                    System.out.println("Would you like to remove the normal light bulb and replace it with the UV light bulb (Yes/No):");
                    System.out.print("> ");
                    String swapBulbs = userInput.nextLine().toLowerCase();
                    if (swapBulbs.contains("yes")){
                        System.out.println("You have swapped the light bulbs.");
                        myPlayer.addItems("normal light bulb");
                        System.out.println("You have obtained a normal light bulb!");
                        myPlayer.removeItems("UV light bulb");
                    }
                    else {
                        System.out.println("The normal light bulb remains.");
                    }

                }
                // works properly
                else if (!myPlayer.checkItems("step ladder")) {
                    System.out.println("The light is just out of reach.");
                }
                else if (!myPlayer.checkItems("gloves")){
                    System.out.println("The light bulb is too hot to handle.");
                }
                else if (!myPlayer.checkItems("UV light bulb")) {
                    System.out.println("You don't have any lightbulbs to switch.");
                }
            }
        }


        //Floor
        else if (myPlayer.getLocation().equals("ground")) {
            if (userInputLower.contains("bump")){
                // works properly
                if (myPlayer.checkItems("key")) {
                    System.out.println("There is nothing else here to find");
                }
                // works properly
                else if (myPlayer.checkItems("knife")){
                    System.out.println("Would you like to cut the carpet? (Yes/No):");
                    System.out.print("> ");
                    String cutCarpet = userInput.nextLine().toLowerCase();
                    // works properly
                    if (cutCarpet.contains("yes")){
                        myPlayer.addItems("key");
                        System.out.println("*****You found a key!*****");
                    }
                }
                // works properly
                else {
                    System.out.println("It feels like something is underneath");
                }
            }
        }
    }

    public void mainGameLoop(){
        while (true){
            prompt();
        }
    }

    public void gameSetup(){
        System.out.println("Hello, what's your name?");
        System.out.print("> ");
        String name = userInput.nextLine().toLowerCase();
        myPlayer.setName(name);

        System.out.println("You wake up in a pitch black room with no memory of how you got there");
        System.out.println("You feel the wall in front of you and find a light switch.");
        System.out.println("You turn the light switch on.");


        mainGameLoop();
    }


    public void walls(){

        if(myPlayer.getLocation().equals("wall1")){
            System.out.println("############################################");
            System.out.println("#                         __________       #");
            System.out.println("#      __________         |        |       #");
            System.out.println("#     |          |        |        |       #");
            System.out.println("#     |          |        |        |       #");
            System.out.println("#     |__________|        |      * |  |*|  #");
            System.out.println("#                         |        |       #");
            System.out.println("# _____________________   |        |       #");
            System.out.println("# | |   | |   | |   | |   |        |       #");
            System.out.println("############################################");
            System.out.println("\nThe wall has a 'tv' mounted to it, a 'shoe rack', a 'light switch', and a 'door'.");
        }
        else if (myPlayer.getLocation().equals("wall2")) {
            System.out.println("############################################");
            System.out.println("#                                          #");
            System.out.println("#   _________                              #");
            System.out.println("#  |    |    |                          _  #");
            System.out.println("#  |----|----|                         | | #");
            System.out.println("#  |____|____|                    ____ | | #");
            System.out.println("#                 _______________|    || | #");
            System.out.println("#                |_____________________| | #");
            System.out.println("#                ||         ||         | | #");
            System.out.println("############################################");
            System.out.println("\nThe wall has a 'window' and a 'bed'.");
        }
        else if (myPlayer.getLocation().equals("wall3")) {
            System.out.println("############################################");
            System.out.println("#                            _____________ #");
            System.out.println("#    ___    ________         |  |  |  |  | #");
            System.out.println("#   |   |  |        |        |  |  |  |  | #");
            System.out.println("#   |   |  |________|        |  |  |  |  | #");
            System.out.println("#  _|___|_____/__\\_____      |  | *|* |  | #");
            System.out.println("#  |  ______________  | __   |  |  |  |  | #");
            System.out.println("#  | |              | ||  |  |  |  |  |  | #");
            System.out.println("#  | |              | ||  |  |  |  |  |  | #");
            System.out.println("############################################");
            System.out.println("\nThe wall has a 'computer' on a desk, a 'trash' can, and a 'closet'.");
        }
        else if (myPlayer.getLocation().equals("wall4")) {
            System.out.println("############################################");
            System.out.println("#                ---------   _____________ #");
            System.out.println("#                | *   * |   |-----------| #");
            System.out.println("#                | \\___/ |   |     *     | #");
            System.out.println("#    ________    ---------   |-----------| #");
            System.out.println("# __|________|____________   |     *     | #");
            System.out.println("#  |  ________________  |    |-----------| #");
            System.out.println("#  | |                | |    |     *     | #");
            System.out.println("#  | |                | |    |-----------| #");
            System.out.println("############################################");
            System.out.println("\nThe wall has a 'painting' hung up, a table with a 'record player' on it, and a tall 'dresser'.");
        }
        else if (myPlayer.getLocation().equals("ceiling")) {
            System.out.println("############################################");
            System.out.println("#                                          #");
            System.out.println("#                                          #");
            System.out.println("#                  \\ | /                   #");
            System.out.println("#                 --(O)--                  #");
            System.out.println("#                  / | \\                   #");
            System.out.println("#                                          #");
            System.out.println("#                                          #");
            System.out.println("#                                          #");
            System.out.println("############################################");
            System.out.println("\nThe ceiling has doesn't have anything but a 'light'.");
        }
        else if (myPlayer.getLocation().equals("ground")) {
            System.out.println("############################################");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~  \\  ~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~   ) ~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~  /  ~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
            System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
            System.out.println("############################################");
            System.out.println("\nThe ground is carpeted, but there's a weird 'bump' in one section.");
        }

    }

    public void printChannelList(){
        System.out.println("############################################");
        System.out.println("# Channel 235 - Olympic Winners            #");
        System.out.println("# Channel 456 - Missing Persons            #");
        System.out.println("# Channel 5786 - Space the Infinite        #");
        System.out.println("# Channel 9379 - Codex Gameshow            #");
        System.out.println("# Channel 1234 - ABCs                      #");
        System.out.println("############################################");
    }

    public void printChannel235(){
        System.out.println("############################################");
        System.out.println("#                                          #");
        System.out.println("#                   0                      #");
        System.out.println("#              0    \\/                     #");
        System.out.println("#             \\/     |         0           #");
        System.out.println("#             |   __/\\___     \\/           #");
        System.out.println("#         ___/\\__|   1   |    |            #");
        System.out.println("#        |   2   |       |___/\\__          #");
        System.out.println("#        |       |       |   3   |         #");
        System.out.println("############################################");
    }

    public void printChannel465(){
        System.out.println( "############################################");
        System.out.println("#  A man by the name of {my_player.name}            #");
        System.out.println( "#  has been missing for over a week no one #");
        System.out.println( "#  has seen or heard from him.             #");
        System.out.println( "############################################");
    }

    public void printChannel5786(){
        System.out.println("############################################");
        System.out.println("#  *              *                  *     #");
        System.out.println("#        *                   *             #");
        System.out.println("#                /|\\                       #");
        System.out.println("#         *     / | \\                      #");
        System.out.println("#              /__|__\\            *        #");
        System.out.println("# *           |       |                    #");
        System.out.println("#             |       |       *        *   #");
        System.out.println("#      *      |       |                    #");
        System.out.println("############################################");
    }

    public void printChannel9379(){
        System.out.println("############################################");
        System.out.println("#  Come on down and play the game!         #");
        System.out.println("#  The prize this week is a mini safe!     #");
        System.out.println("#  Perfect for under your bed!             #");
        System.out.println("############################################");
    }

    public void printChannel1234(){
        System.out.println("############################################");
        System.out.println("#       ABCDEFGHIJKLMNOPQRSTUVWXYZ         #");
        System.out.println("############################################");
    }


}