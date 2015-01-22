@Category(Integer)
class MondayNightBrewery {
    static class Beer {}
    
    def drink(one, beer) { 
        this.downto(one, beer)
    }
    
    def bottlesOfBeerOnTheWall(grab) { 
        println "$this bottles of beer on the wall..."
        this.drink(1, { beer -> grab(beer) }) 
    }
    
    def bottlesOfBeer() { 
        println "$this bottles of beer"  
        def wall = []
        this.drink(1, { wall << new Beer() }) 
        wall 
    }
}

List.metaClass.takeOneDown  = { 
    println "Take One Down"
    delegate.pop()
    delegate 
}

List.metaClass.passItAround = { 
    println "Pass it around\n${delegate.size()} bottles of beer on the wall\n"
    delegate ?: 0 
}

List.metaClass.bottlesOfBeerOnTheWall = {}

use (MondayNightBrewery) {
    99.bottlesOfBeerOnTheWall { ninetyNine ->
        ninetyNine
            .bottlesOfBeer()
            .takeOneDown()
            .passItAround()
            .bottlesOfBeerOnTheWall()
    }
    
    // Crash!
}






