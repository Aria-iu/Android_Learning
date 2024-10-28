def os = 'Linux'
def cores = 2
// GString makes it easy to embed a bunch of variables into a string
println("Cores: $cores, OS: $os, Time: ${new Date()}")

/* A closure is a block of code in Groovy, which may or may not take
    parameters and return a value. 
    closures can be used by the findAll, each, and times methods

    Groovy closures have several implicit variables:
        1. it       —If the closure has one argument, it can be referred to implicitly as it.
        2. this     —Refers to the enclosing class.
        3. owner    —The same as this unless it is enclosed in another closure. 
        4. delegate —Usually the same as owner but you can change it.
*/
def print1 = {list -> list.each{println owner}
    println owner
}
print1([1])

def list = ['foo','bar']
def newList = []
list.collect(newList) {
    it.toUpperCase()
}
println newList

def add2 = {x->x+2}
println add2(2)


/*
    Groovy’s switch statement allows many more case expressions.
    And let's see what result is ?? it's dynamic !!! and need no predefine!!!!
*/
def x = 42
switch(x) {
    case "foo":
        result = "found foo"
        break
    case [4, 5, 6]:
        result = "4 5 or 6"
        break
    case 12..30:
        result = "12 to 30"
        break
    case Integer:
        result = "was integer"
        break
    case Number:
        result = "was number"        
        break
    default:
        result = "default"
}
println result

/*
    In Groovy, you can add methods to classes at runtime, even to core Java libraries.
    like ...
*/
// the following code adds the upper method to the String class
String.metaClass.upper = { -> toUpperCase()}
// for a single instance (str)
def str = "test"
str.metaClass.upper = { -> toUpperCase() }
println str.upper() == str.toUpperCase()



