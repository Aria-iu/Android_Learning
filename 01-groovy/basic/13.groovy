class Example {
   static void main(String[] args) {
      Student mst = new Student();
      mst.Name = "Joe";
      mst.ID = 1;
		
      println(mst.Name);
      println(mst.ID);
      mst.AddMarks();
   } 
} 

class Student implements GroovyInterceptable {
   protected dynamicProps = [:]  
    
   void setProperty(String pName, val) {
      dynamicProps[pName] = val
   }
   
   def getProperty(String pName) {
      dynamicProps[pName]
   }
   
   def methodMissing(String name, def args) {         
      println "Missing method"
   }  
}