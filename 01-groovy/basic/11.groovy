import groovy.json.JsonOutput  
class Example {
   static void main(String[] args) {
      def output = JsonOutput.toJson([ new Student(name: 'John',ID:1),
         new Student(name: 'Mark',ID:2)])
      println(output);  
   } 
}
 
class Student {
   String name
   int ID; 
}