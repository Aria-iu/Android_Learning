class Example {
   static void main(String[] args) {
      try {
         def arr = new int[3];
         arr[5] = 5;
      }catch(ArrayIndexOutOfBoundsException ex) {
         println(ex.toString());
         println(ex.getMessage());
         println(ex.getStackTrace());  
      } catch(Exception ex) {
         println("Catching the exception");
      }finally {
         println("The final block");
      }
		
      println("Let's move on after the exception");
   } 
}
 