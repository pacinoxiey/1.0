package langrensha.game.data;

public enum Actor {
	/** 狼人*/
    werwolf,
    /** 村民*/
    villager,
    /** 猎人*/ 
    huntsman,
    /** 女巫*/
    witch,
    /** 预言家*/
    prophet,
    /** 守卫*/
    guard,
    /** all*/
    all
    
    /**
     * werwolf("狼人",1),
    
    villager("村民",2),
    
    huntsman("猎人",3),
    
    witch("女巫",4),
    
    prophet("预言家",5),
    
    guard("守卫",6);
	
	private String name;
	private int index;
	
    Actor(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }
     */
}
