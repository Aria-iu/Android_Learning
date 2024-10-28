class Example {
   static void main(String[] args) {
      Student st = new Student();
      st.StudentID = 1;
      st.Marks1 = 10; 
      // println(st.DisplayMarks());
      st.DisplayMarks();
   } 
} 


trait Marks {
    void DisplayMarks() {
        println("Display Marks");
    } 
}

trait Total extends Marks {
   void DisplayMarks() {
      println("Total");
   } 
}  

class Student implements Total { 
    int StudentID
    int Marks1;
}

