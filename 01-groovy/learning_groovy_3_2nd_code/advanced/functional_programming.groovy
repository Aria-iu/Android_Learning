/*
    Functions and Closures
        First-class feature means that a function can be used anywhere a value could be used.
        Although Groovy doesn’t have first-class functions, it has something very similar: closures. 
*/

/*
    When a closure is the last parameter to a method,
        it can go outside of the parentheses, and if it is the only parameter,
        parentheses can be omitted completely. 
*/
def find(list, tester) {
    for (item in list)
        if(tester(item)) return item
}
// same as find([1,2],{it > 1})
find([1,2]) { it > 1 }

/*
    People invented sequence operations, such as map, filter, reduce, etc.
    We will be using a list of Person objects for all the operations defined as follows. 
*/
class Person {
    String name
    int age
    String toString() { name }
}
def persons = [new Person(name:'Bob',age:20), new Person(name:'Tom',age:15)]
// Map (collect): Translates or changes input elements into something else
def names = persons.collect { person -> person.name }
// Filter (findAll): Gives you a sub-set of elements (what returns true from some predicate function)
def adults = persons.findAll { person -> person.age >= 18 }
// Reduce (inject): Performs a reduction (returning one result, such as a sum) on the elements
def totalAge = persons.inject(0) {total, p -> return total+p.age }
// Limit ([0..n-1]): Gives you only the first N elements
def a = [3,2,1]
def firstTwo = a[0..1]
// Concat (+): Combines two different collections of elements
def b = [4,5]
def c = a + b
println c

/*
    Immutability
        In addition to the final keyword, Groovy includes the @Immutable annotation for declaring a whole class immutable.

*/
import groovy.transform.Immutable
@Immutable
public class Dragon {
    String name
    int scales
}
Dragon smaug = new Dragon('Smaug', 499)
println smaug


/*
    Groovy Fluent GDK
        In Groovy, findAll and other methods are available on every object, but
        they are especially useful for lists, sets, and ranges. In addition to findAll,
        collect, and inject, the following method names are used in Groovy:
            1.each          —Iterates through the values using the given closure
            2.eachWithIndex —Iterates through with two parameters: a value and an index
            3.find          —Finds the first element that matches a closure
            4.findIndexOf   —Finds the first element that matches a closure and returns its index
*/

/*
    Groovy Curry
        The curry method allows you to predefine values for parameters of a
        closure. It takes any number of arguments and replaces parameters from
        left to right as you might expect.
*/
def concat = { x, y -> return x + y }
def burn = concat.curry("burn")
def inate = concat.curry("inate")
println burn(" wood")

/*
    Method Handles
        Method handles allow you to refer to actual methods much like they are closures.
        This is useful when you want to use existing methods instead of closures, or you just want an alternative syntax for closures. 
*/
def breathFire(name) { println "Burninating $name!" }
['the country side', 'all the people'].each(this.&breathFire)

/*
    Tail Recursion

*/
import groovy.transform.*
@TailRecursive
long totalPopulation(list, total = 0){
    if (list.size() == 0)
        total
    else 
        totalPopulation(list.tail(), total + list.first().population)
}

@Canonical class City {int population}
def cities = (10..1000).collect{new City(it)}
println totalPopulation(cities)


