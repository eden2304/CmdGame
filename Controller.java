package com.company;
import java.util.*;

import com.company.Enemies.Enemy;
import com.company.Players.Player;
import com.company.UI.CLI;

public class Controller {
    private GameController levelManager;
    private CLI uiController;
    private TileFactory tf;

    public Controller(){
//        levelManager = new GameController();
        uiController = new CLI();
        tf = new TileFactory();
    }

    public void startGame(){
        //need to get into the levels directory and do everything
        uiController.print("Selec player: ");
        List<Player> players = tf.listPlayers();
        for (Player p: players)
            uiController.print(p.describe() + "\n");

    }

}
