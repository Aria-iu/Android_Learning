/*
    Groovy has many features that make it great for writing DSLs (domain-­specific languages):
        1. Closures with delegates.
        2. Parentheses and dots (.) are optional (command chains).
        3. Ability to add methods to standard classes using Categories and Extension modules.
        4. The ability to override many operators (plus, minus, etc.).
        5. The methodMissing and propertyMissing methods.
*/


/*
    Closure with Delegate
        you can take a block of code (a closure) as a parameter and then call it using a local variable as a delegate.
*/
class SMS{
    String from, to, body;
    def from(String fromNumber) {
        from = fromNumber
        return this
    }
    def to(String toNumber) {
        to = toNumber
        return this
    }
    def body(String body) {
        this.body = body
        return this
    }
    def send() {
        // send the text.
        println "Send from $from to $to : $body"
    }

    // In Groovy you can add the following static method to the SMS class for
    // DSL-like usage (block is expected to be a closure)
    def static send(@DelegatesTo(SMS) Closure block) {
        // send the text.
        SMS m = new SMS()
        block.delegate = m
        block()
        m.send()
    }
}

SMS.send (
    {
        from '555-432-1234'
        to '555-678-4321'
        body 'Hey there!'
    }
)

/*
    Command Chains
        Groovy has support for command chains which allow you
        to completely omit parenthesis and dots when making method calls with one or more parameters.
    
    look previous, we add ** return this ** to method func return val
*/


/*
    Overriding Operators
        In Groovy you can override operators simply by naming your methods
        using the English word for the operator. For example, plus for + and minus for -. 
    
*/
class Logic{
    boolean value;
    Logic(v) {this.value = v}
    def and(Logic other) {
        this.value && other.value
    }
    def or(Logic other){
        this.value || other.value
    }
}
def pale = new Logic(true)
def old = new Logic(false)
println "groovy truth: ${pale && old}"
println "using and: ${pale & old}"
println "using or: ${pale | old}"

class Wizards{
    def list = []
    def leftShift(person) { list.add person }
    def minus(person) { list.remove person }
    String toString() { "Wizards: $list" }
    def getAt(index) { return list[index] }
    def putAt(index,val) { list[index]=val }
}
def wiz = new Wizards()
wiz << 'Gandolf'
println wiz
wiz << 'Harry'
println wiz
wiz - 'Gandolf'
println wiz
// You can also implement the putAt and getAt methods that allow you to use the bracket syntax.
def value = wiz[0]
println value
wiz[0] = 'zyc'
println wiz


/*
    Missing Methods and Properties
        Groovy provides a way to implement functionality at runtime via the methodMissing method
*/
class Chemistry{
    public static void exec(Closure block){
        block.delegate = new Chemistry()
        block()
    }
    // for Missing Properties
    def propertyMissing(String name){
        def comp = new Compound(name)
        (comp.elements.size() == 1 && comp.elements.values()[0]==1) ? comp.elements.keySet()[0] : comp
    }
}

// Represents a chemical Element
class Element {
    String symbol
    Element(s) { symbol = s }
    double getWeight() {symbol=='H' ? 1.00794 : 15.9994}
    String toString() { symbol }
}
// Represents a chemical Compound
class Compound {
    final Map elements = [:]

    Compound(String str) {
        def matcher = str =~ /([A-Z][a-z]*)([0-9]+)?/
        while (matcher.find()) add(
            new Element(matcher.group(1)), (matcher.group(2) ?: 1) as Integer
        )
    }

    void add(Element e, int num) {
        if (elements[e]) elements[e] += num
        else elements[e] = num
    }

    double getWeight() {
        elements.keySet().inject(0d) { sum, key -> sum + (key.weight * elements[key])}
    }

    String toString() { "$elements" }
}
def c = new Chemistry()
def water = c.H2O
println water
println water.weight

Chemistry.exec {
        def water2 = H2O
        println water2
        println water2.weight
}



