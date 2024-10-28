/*
    Groovy greatly simplifies using a pattern to match text using regex.
    Where in Java you must use the java.util.regex.Pattern class,
    create an instance, and then create a Matcher; in Groovy this can all be
    simplified to one line.

    By convention you surround your regex with slashes. This allows you
    to use special regex syntax without using the tedious double backslash.
*/
def email = 'mailto:adam@email.com'
// creating a matcher in Groovy
def mr = email =~ /[\w.]+@[\w.]+/
println mr
// This allows you to find regular expressions inside strings and to get sub-groups from a regex.
if (mr.find()) println mr.group()
// This allows you to simply get is it match.
def email2 = 'adam@email.com'
def isEmail = email2 ==~ /[\w.]+@[\w.]+/
println isEmail
