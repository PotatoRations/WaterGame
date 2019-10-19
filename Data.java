/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author aaron
 */
public class Data {
    
    public int population;
    public int money;
    public int mapSize;
    public Tile[][] tileMap;
    public double waterHeight =0;
    public double temperature =0;
    
    
    /*
    Tile ID legend:
    0 = Grass
    1 = Forest
    2 = ocean
    
    10 = built residential
    11 = building residential
    
    20 = built light industrial
    21 = building light industrial
    22 = built heavy industrial
    23 = building heavy industrial
    
    30 = built farm
    31 = building farm
    
    40 = no seawall -> same texture as ocean
    41 = lvl 1 seawall
    42 = lvl 2 seawall
    43 = lvl 3 seawall
    
    50 = built cleanPower
    51 = building cleanPower
    52 = build coalPower
    53 = building coalPower
    
    */
    
    public Data(int mapSize,int money,int population){
        this.population = population;
        this.money = money;
        this.mapSize = mapSize;
        tileMap = new Tile[mapSize][mapSize];
        //TODO: generate matrix with tile types
    }
    
    
    //runs next turn and generates new data
    public void startLoop(){
        //TODO: add stuff
        /*
        add money
        build completion
        updates window
        */
        this.money += getIncome();
    }
    public void endLoop(){
        /*
        new events
        change pops
        raise water
        */
    }
    
    public void raiseWater(){
        //todo: raise in accordance to temp (1.6cm for every degree)
        waterHeight = temperature*1.6;
        //submerges submerged tiles
        for (int i=0; i<mapSize; i++){
            for (int j=0; j<mapSize; j++){
                if (tileMap[i][j].height<=waterHeight){
                    //change to ocean
                    tileMap[i][j].changeType(2);
                    tileMap[i][j].changeSalt(true);
                    tileMap[i][j].submerged = true;
                }
                //if is ocean, set height to waterHeight
                if (tileMap[i][j].type == 2){
                    tileMap[i][j].height = waterHeight;
                }
            }
        }
    }
    
    
    /*Calculations*/
    
    public int getIncome(){
        //formula: population + 10*min(population, industrial)*min(1, (double)energygenerated/energyneed)
        int income = (int) (population + 10*Math.min(population, industryPower())*Math.min(1.0, (double)energyGenerated()/energyNeed()));
        return income;
    }
    
    public int energyGenerated(){
        return searchTypeNumber(50)*5 + searchTypeNumber(52)*10;
    }
    
    public int industryPower(){
        return searchTypeNumber(20)*5 + searchTypeNumber(22)*15;
    }
    
    public int energyNeed(){
        //TODO: put real values
        return searchTypeNumber(10) + searchTypeNumber(20)*2 + searchTypeNumber(22)*5 + searchTypeNumber(30);
    }
    
    public int maxPop(){
        return Math.min(searchTypeNumber(30)*10, searchTypeNumber(10)*10);
    }
    
    public int calculateCarbon(){
        return population + searchTypeNumber(20)*5 + searchTypeNumber(22)*20 + searchTypeNumber(52)*10 + searchTypeNumber(30);
    }
    
    /*melt*/
   
    //adjusts temperature based on value given
    public void adjustTemp(double value){
        temperature += value;
    }
    
    
    /*tile data*/
    
    //checks to see if tile is underwater
    public boolean isSubmerged(int x, int y){
        return tileMap[x][y].submerged;
    }
    
    //returns map of all tiles and their submerge status
    public boolean[][] submergedMap(){
        boolean[][] map = new boolean[mapSize][mapSize];
        for (int i=0; i<mapSize; i++){
            for (int j=0; j<mapSize; j++){
                map[i][j] = isSubmerged(i,j);
            }
        }
        return map;
    }
    
    //returns matrix of all tiles that fulfill search
    public boolean[][] searchStatus(int type, boolean salted, boolean submerged){
        boolean[][] map = new boolean[mapSize][mapSize];
        for (int i=0; i<mapSize; i++){
            for (int j=0; j<mapSize; j++){
                Tile tile = tileMap[i][j];
                map[i][j] = (tile.salted==salted && tile.submerged==submerged && tile.type == type);
            }
        }
        return map;
    }
    
    public int searchStatusNumber(int type, boolean salted, boolean submerged){
        boolean[][] map = searchStatus(type, salted, submerged);
        int value=0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length;j++){
                if(map[i][j]){
                    value++;
                }
            }
        }
        return value;
    }
    
    public int searchTypeNumber(int type){
        return searchStatusNumber(type, true, false) + searchStatusNumber(type, false, false);
    }
    
}