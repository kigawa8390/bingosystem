package net.kigawa.plugin.kyosaba.system.bingosystem.game;

import net.kigawa.plugin.kyosaba.system.bingosystem.BingoSystem;
import net.kigawa.plugin.kyosaba.system.bingosystem.data.GameEquals;
import org.bukkit.Location;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;


public class GameList {
    BingoSystem plugin;
    List<GameData> gameDataList;
    public GameList(BingoSystem bingoSystem){
        plugin=bingoSystem;
    }
    public GameData getGameData(){
        Iterator<GameData> it=gameDataList.iterator();
        GameData gameData = null;
        while (it.hasNext()&&!it.next().isFree()){
            gameData= it.next();
        }
        assert gameData != null;
        if(gameData.isFree()){
            return gameData;
        }else {
            return null;
        }

    }
    public GameData getGameData(String name){
        GameEquals gameEquals=new GameEquals("isMachName");
        gameEquals.name=name;
        return gameDataList.get(gameDataList.indexOf(gameEquals));
    }
    public GameData getGameData(Location location){
        int index=gameDataList.indexOf(location);
        if(index>=0) {
            return gameDataList.get(index);
        }else {
            return null;
        }
    }
    public GameData getGameData(UUID uuid){
        int index= gameDataList.indexOf(uuid);
        if(!(0>index)) {
            return gameDataList.get(index);
        }else {
            return null;
        }
    }
    public GameSlot getSlot(UUID uuid){
        if (gameDataList.contains(uuid)){
            GameData gameData=gameDataList.get(gameDataList.indexOf(uuid));
            GameBord gameBord=gameData.gameBords.get(gameData.gameBords.indexOf(uuid));
            return gameBord.gameSlotList.get(gameBord.gameSlotList.indexOf(uuid));
        }else {
            return null;
        }
    }
    public boolean hasSlotSet(){
        return gameDataList.contains(new GameEquals("hasSlotSet"));
    }
}
