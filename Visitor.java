package com.company;

import com.company.Enemies.Enemy;
import com.company.Players.Player;

public interface Visitor {
    void visit(Player p);
    void visit(Enemy p);
}
