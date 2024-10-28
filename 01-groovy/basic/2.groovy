class Example {
    int x;
    public int getX(){
        return x;
    }
    public void setX(int pX) { 
        x = pX; 
    } 
    static void main(String[] args) { 
        //初始化 2 个变量
        int x = 5; 
        int X = 6; 
      
        //将变量的值打印到控制台
        println("The value of x is " + x + "The value of X is " + X);
        def range = 5..10; 
        println(range); 
        println(range.get(2));

        DisplayName();
        sum(10,5);

        Example ex = new Example(); 
        ex.setX(100); 
        println(ex.getX()); 
   }

    static def DisplayName() {
        println("This is how methods work in groovy");
        println("This is an example of a simple method");
    }

    static void sum(int a,int b) {
        int c = a+b;
        println(c);
    }
}

