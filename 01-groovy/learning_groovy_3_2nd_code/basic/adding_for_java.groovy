/*
    One of the tedious tasks you must often do in Java is create an equals
    and a hashCode method for a class. For this purpose, Groovy added the
    @EqualsAndHashCode annotation. Simply add it to the top of your class
    (right before the word class) and you’re done.

    Likewise, you often want to create a constructor for all of the fields
    of a class. For this, Groovy has @TupleConstructor. It uses the order of
    the definitions of your fields to define a constructor with parameters for
    initializing those fields. Just add it right before your class definition.

    There’s also the @ToString annotation you can add before your class
    definition for automatically creating a toString() method for your class.
    You can also configure it to include or exclude certain fields.

    Finally, if you want to have all of these things on your class, just use the
    @Canonical annotation. You can use Ctrl+T in the **groovyConsole** to see
    how this affects the syntax tree.
*/
import groovy.transform.*
@Canonical 
class Dragon 
{
    def name
}
// toString()
println new Dragon("Smaug")
// equals()
assert new Dragon("").equals(new Dragon(""))


/*
    Groovy syntax sugar for maps allows you use string keys directly, which is
    often very helpful. However, this can cause confusion when attempting to
    get the class-type of a map using Groovy’s property-accessor syntax sugar
    (.class refers to the key-value, not getClass()). So you should use the
    getClass() method directly.

    This can also cause confusion when you’re trying to use variables as
    keys. In this case, you need to surround the variables with parentheses.
*/
def foo = "key"
def bar = 2
/*
    Without the parentheses, foo would resolve to the String "foo". With
    the parentheses, the String "key" will be used as the key mapped to the value 2.
*/
def map = [(foo):bar]
def map2 = [foo:bar]
println map
println map2
