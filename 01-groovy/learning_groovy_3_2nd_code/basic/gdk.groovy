/*
    The GDK (Groovy Development Kit) provides a number of helper
    methods, operators, utilities, and additional classes.
*/

/*
    Groovy adds tons of helpful methods that allow easier manipulation of
    collections, arrays, or any Iterable:
        1. sort     —Sorts the collection (if it is sortable).
        2. findAll  —Finds all elements that match a closure.
        3. collect  —An iterator that builds a new collection.
        4. inject   —Loops through the values and returns a single value (similar to the concept of “reduce”).
        5. each     —Iterates through the values using the given closure.
        6. eachWithIndex    —Iterates through with two parameters: a value and an index.
        7. find     —Finds the first element that returns true when passed to a given a closure.
        8. findIndexOf      —Finds the first element that matches a closure and returns its index.
        9. any      —True if any element returns true for the closure(like OR).
        10. every  —True if all elements return true for the closure(like AND).
        11. reverse —Reverses the ordering of elements in a list.
        12. first   —Gets the first element of a list.
        13. last    —Returns the last element of a list.
        14. tail    —Returns all elements except the first element of a list (useful for, e.g., recursive strategies).
*/

/*
    The Range is a built-in type in Groovy. It can be used to perform loops, in
    switch cases, extracting substrings, and other places. Ranges are generally
    defined using the syntax start..end.

    You can use ranges to extract substrings from a string using the getAt syntax.
*/
def text = 'learning groovy'
println text[0..4]
println text[0..4, 8..-1]

/*
    The GDK adds several utility classes, such as ConfigSlurper, JsonBuilder,
    JsonSlurper, Expando, and ObservableList/Map/Set.
*/


/*
    Groovy comes with observable lists, maps, and sets. 
    Each of these collections triggers PropertyChangeEvent (from the java.beans package) when elements are added, removed, or changed. 
    Here’s an example using ObservableList and printing out the class of each event:
        This can be useful for using the Observer pattern on collections.
*/
def list = new ObservableList()
def printer = {e -> println e.class}
list.addPropertyChangeListener(printer)
list.add 'Harry Potter'
list.add 'Hermione Granger'
list.remove(0)
println list


