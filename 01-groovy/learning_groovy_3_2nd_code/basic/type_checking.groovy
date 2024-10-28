/*
    If you add the @TypeChecked annotation to your class, it causes the compiler to enforce compile time type-checking.

*/
import groovy.transform.*
@TypeChecked
class Foo{
    // Compile time error : Cannot assign value of type java.math.BigDecimal to variable of type int
    // int i = 42.0
    int i = 42
}

new Foo()

/*
    If you add the @CompileStatic annotation to your class or method, it
    causes the compiler to compile your Groovy code to Java-style byte-code.

    This would be useful when you have code that needs to be extremely performant or
    you need Java byte-code for some other reason. 

    unlike @TypeChecked which still uses Groovy’s MOP—Meta Object Protocol
*/
@CompileStatic
class bar{
    void getFibs(int count) {
        def list = [0, 1]
        count.times {
            println "${list.last()}"
            int next = (int)list.sum()
            list = [list[1],next]
        }
    }
}

bar b = new bar()
b.getFibs(4)

/*
    Groovy has the safe dereference operator that allows you to easily avoid null-pointer exceptions. 
    It involves simplyadding a question mark. 
*/
// This would simply set name to null if person is null. but ... that looks ugly , kind of ... 
class people{
    void getName(){}
}
people person = null
String name = person?.getName()
println name

