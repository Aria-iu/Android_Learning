/*
    Traits are like interfaces with default implementations and state.
*/

/*
    Defining Traits
        Traits can have properties, methods, and abstract methods.
        If a class implements a Trait, it must implement its abstract methods.
*/
trait Animal {
    int hunger = 100
    def eat() { println "eating"; hunger -= 1 }
    abstract int getNumberOfLegs()
}

/*
    Using Traits
        To use a Trait, you use the implements keyword.
        Unlike super-classes, you can use multiple traits in one class.
*/
class Rocket {
    String name
    def launch() { println(name + " Take off!") }
}
trait MoonLander {
    def land() { println("${getName()} Landing!") }
    abstract String getName()
}
class Apollo extends Rocket implements MoonLander {}
def apollo = new Apollo(name: "Apollo 12")
apollo.launch()
apollo.land()

trait Shuttle {
    boolean canFly() { true }
    abstract int getCargoBaySize()
}
class MoonShuttle extends Rocket implements MoonLander, Shuttle {
    int getCargoBaySize() { 100 }
}
MoonShuttle m = new MoonShuttle(name: 'Taxi')
println "${m.name} can fly? ${m.canFly()}"
println "cargo bay: ${m.getCargoBaySize()}"
m.launch()
m.land()



