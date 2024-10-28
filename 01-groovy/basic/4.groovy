class Example { 
    static void main(String[] args) { 
        String sample = "Hello world"; 
        println(sample[4]); // Print the 5 character in the string
            
        //Print the 1st character in the string starting from the back 
        println(sample[-1]); 
        println(sample[1..2]);//Prints a string starting from Index 1 to 2 
        println(sample[4..2]);//Prints a string starting from Index 4 back to 2 

        Date date = new Date();
      
        // 使用 toString() 显示时间和日期
        System.out.println(date.toString());
      
    }

}